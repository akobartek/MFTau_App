package pl.mftau.mftau.view.activities

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_meeting_editor.*
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.ActivityMeetingEditorBinding
import pl.mftau.mftau.view.adapters.AttendanceListRecyclerAdapter
import pl.mftau.mftau.viewmodel.MeetingEditorViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class MeetingEditorActivity : AppCompatActivity() {

    companion object {
        const val DATE_PATTERN = "dd.MM.yyyy"
    }

    private lateinit var mMeetingEditorViewModel: MeetingEditorViewModel
    private lateinit var mAdapter: AttendanceListRecyclerAdapter

    private var mMeetingHasChanged = false
    private var mIsPresenceChecking = false

    override fun onCreate(savedInstanceState: Bundle?) {
        if (PreferenceManager.getDefaultSharedPreferences(this@MeetingEditorActivity)
                        .getBoolean(getString(R.string.night_mode_key), false)) {
            setTheme(R.style.AppTheme_Dark)
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                window.statusBarColor = Color.WHITE
            }
        }

        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMeetingEditorBinding>(
                this@MeetingEditorActivity, R.layout.activity_meeting_editor)
        setSupportActionBar(addMeetingToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        mMeetingEditorViewModel = ViewModelProviders.of(this@MeetingEditorActivity).get(MeetingEditorViewModel::class.java)
        binding.viewModel = mMeetingEditorViewModel
        mAdapter = AttendanceListRecyclerAdapter()

        mMeetingEditorViewModel.meeting = intent.getParcelableExtra("meeting")
        if (mMeetingEditorViewModel.meeting == null) {
            invalidateOptionsMenu()
            title = getString(R.string.add_meeting)
        } else {
            mMeetingEditorViewModel.date = mMeetingEditorViewModel.meeting!!.date.toDate()
            mMeetingEditorViewModel.attendanceList = mMeetingEditorViewModel.meeting!!.attendanceList
            title = getString(R.string.edit_meeting)
        }

        meetingTypeSpinner.adapter = object : ArrayAdapter<String>(this@MeetingEditorActivity,
                R.layout.item_spinner, resources.getStringArray(R.array.meeting_types)) {
            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getDropDownView(position, convertView, parent)
                (view as TextView).setTextColor(Color.BLACK)

                return view
            }
        }

        attendanceRecyclerView.layoutManager = LinearLayoutManager(this@MeetingEditorActivity)
        attendanceRecyclerView.itemAnimator = DefaultItemAnimator()
        attendanceRecyclerView.adapter = mAdapter
        attendanceRecyclerView.animate()
                .alpha(0f)
                .withEndAction { attendanceRecyclerView.visibility = View.INVISIBLE }
                .duration = 1

        mIsPresenceChecking = intent.getBooleanExtra("checking", false)
        if (mIsPresenceChecking)
            checkPresence()

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        saveMeetingBtn.setOnClickListener {

            val meetingValues = mMeetingEditorViewModel.createMeetingValues(
                    meetingNameET.text.toString().trim(), meetingTypeSpinner.selectedItemPosition
            )

            if (!mIsPresenceChecking) {
                if (meetingNameET.text.isNullOrBlank()) {
                    meetingNameET.error = getString(R.string.empty_meeting_name_error)
                    return@setOnClickListener
                }

                if (mMeetingEditorViewModel.meeting == null) {
                    showCheckPresenceDialog(meetingValues)
                } else {
                    if (mMeetingEditorViewModel.date == mMeetingEditorViewModel.meeting!!.date.toDate()
                            && meetingNameET.text.toString().trim() == mMeetingEditorViewModel.meeting!!.name
                            && meetingTypeSpinner.selectedItemPosition == mMeetingEditorViewModel.meeting!!.meetingType
                            && mMeetingEditorViewModel.attendanceList == mMeetingEditorViewModel.meeting!!.attendanceList) {
                        finish()
                    } else {
                        mMeetingEditorViewModel.updateMeeting(this@MeetingEditorActivity,
                                meetingTypeSpinner.selectedItemPosition, meetingValues)
                    }
                }
            } else {
                mIsPresenceChecking = false
                mMeetingHasChanged = true
                mMeetingEditorViewModel.attendanceList = mAdapter.mAttendanceList

                saveMeetingBtn.setImageDrawable(getDrawable(R.drawable.anim_done_to_save))
                (saveMeetingBtn.drawable as AnimatedVectorDrawable).start()
                attendanceRecyclerView.animate()
                        .alpha(0f)
                        .withEndAction { attendanceRecyclerView.visibility = View.INVISIBLE }
                        .duration = 400

                if (mMeetingEditorViewModel.meeting == null) {
                    mMeetingEditorViewModel.addMeeting(this@MeetingEditorActivity,
                            meetingTypeSpinner.selectedItemPosition, meetingValues, mMeetingEditorViewModel.attendanceList)
                } else {
                    mMeetingEditorViewModel.updateAttendanceList(meetingTypeSpinner.selectedItemPosition)
                    if (intent.getBooleanExtra("checking", false))
                        finish()
                }

            }
        }

        setDateIcon.setOnClickListener(mDateClickListener)
        dateText.setOnClickListener(mDateClickListener)

        addMeetingLayout.setOnClickListener(mHideKeyboardClickListener)

        meetingNameET.setOnTouchListener(mTouchListener)
        meetingTypeSpinner.setOnTouchListener(mTouchListener)
        setDateIcon.setOnTouchListener(mTouchListener)
        dateText.setOnTouchListener(mTouchListener)
    }

    override fun onBackPressed() {
        if (intent.getBooleanExtra("checking", false))
            finish()
        else if (mMeetingHasChanged || mIsPresenceChecking)
            showUnsavedChangesDialog()
        else {
            startActivity(Intent(this@MeetingEditorActivity, MeetingsActivity::class.java))
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_edit_meeting, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        if (mMeetingEditorViewModel.meeting == null) {
            menu.findItem(R.id.action_delete_meeting).isVisible = false
            menu.findItem(R.id.action_check_presence).isVisible = false
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_check_presence -> {
                checkPresence()
                true
            }
            R.id.action_delete_meeting -> {
                showDeleteConfirmationDialog()
                true
            }
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun showCheckPresenceDialog(meetingValues: HashMap<String, Any>) =
            AlertDialog.Builder(this@MeetingEditorActivity)
                    .setMessage(R.string.check_presence_dialog_msg)
                    .setCancelable(false)
                    .setPositiveButton(R.string.yes) { dialog, _ ->
                        dialog.dismiss()
                        checkPresence()
                    }
                    .setNegativeButton(R.string.no) { dialog, _ ->
                        dialog.dismiss()
                        mMeetingEditorViewModel.addMeeting(this@MeetingEditorActivity,
                                meetingTypeSpinner.selectedItemPosition, meetingValues, null)
                    }
                    .create()
                    .show()

    private fun checkPresence() {
        mIsPresenceChecking = true
        saveMeetingBtn.setImageDrawable(getDrawable(R.drawable.anim_save_to_done))
        (saveMeetingBtn.drawable as AnimatedVectorDrawable).start()
        attendanceRecyclerView.animate()
                .alpha(1f)
                .withEndAction { attendanceRecyclerView.visibility = View.VISIBLE }
                .duration = 400
        attendanceRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy < 0 && !saveMeetingBtn.isShown)
                    saveMeetingBtn.show()
                else if (dy > 0 && saveMeetingBtn.isShown)
                    saveMeetingBtn.hide()
            }
        })

        if (mAdapter.mMembersList.isEmpty()) {
            mMeetingEditorViewModel.getAllMembers().observe(this@MeetingEditorActivity, androidx.lifecycle.Observer { members ->
                loadingIndicator.show()
                mAdapter.setLists(members, mMeetingEditorViewModel.attendanceList)

                loadingIndicator.hide()
                if (members.isEmpty()) {
                    emptyView.visibility = View.VISIBLE
                } else {
                    emptyView.visibility = View.INVISIBLE
                }
            })
        }

    }

    private fun showUnsavedChangesDialog() =
            AlertDialog.Builder(this@MeetingEditorActivity)
                    .setMessage(R.string.unsaved_changes_dialog_msg)
                    .setCancelable(false)
                    .setPositiveButton(R.string.discard) { dialog, _ ->
                        dialog.dismiss()
                        finish()
                    }
                    .setNegativeButton(R.string.keep_editing) { dialog, _ -> dialog.dismiss() }
                    .create()
                    .show()


    private fun showDeleteConfirmationDialog() =
            AlertDialog.Builder(this@MeetingEditorActivity)
                    .setMessage(R.string.meeting_delete_dialog_msg)
                    .setPositiveButton(R.string.delete) { dialog, _ ->
                        dialog.dismiss()
                        mMeetingEditorViewModel.deleteMeeting(this@MeetingEditorActivity)
                        finish()
                    }
                    .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
                    .create()
                    .show()


    private val mTouchListener = View.OnTouchListener { _, _ ->
        mMeetingHasChanged = true
        false
    }
    private val mHideKeyboardClickListener = View.OnClickListener {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }
    private val mDateClickListener = View.OnClickListener {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(currentFocus!!.windowToken, 0)

        val calendar = Calendar.getInstance()
        calendar.time = mMeetingEditorViewModel.date

        DatePickerDialog(this@MeetingEditorActivity, myDateListener,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
    }
    private val myDateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
        val dateString = StringBuilder().append(day).append(".").append(month + 1).append(".").append(year).toString()
        mMeetingEditorViewModel.date = SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).parse(dateString)
    }
}
