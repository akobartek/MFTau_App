package pl.mftau.mftau.view.activities

import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_add_meeting.*
import pl.mftau.mftau.R
import pl.mftau.mftau.model.Meeting
import pl.mftau.mftau.model.Member
import pl.mftau.mftau.utils.FirestoreUtils
import pl.mftau.mftau.utils.FirestoreUtils.firestoreCollectionCities
import pl.mftau.mftau.utils.FirestoreUtils.firestoreCollectionMeetings
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyName
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyDate
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyMeetingType
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyAttendanceList
import pl.mftau.mftau.utils.FirestoreUtils.meetingTypes
import pl.mftau.mftau.view.adapters.AttendanceListRecyclerAdapter
import pl.mftau.mftau.view.fragments.MeetingsFragment
import java.text.SimpleDateFormat
import java.util.*


class MeetingEditorActivity : AppCompatActivity() {

    companion object {
        const val DATE_PATTERN = "dd.MM.yyyy"
    }

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mFirestore: FirebaseFirestore
    private lateinit var mStorageRef: StorageReference
    private lateinit var mAdapter: AttendanceListRecyclerAdapter

    private var mMeeting: Meeting? = null
    private var mDate: Date? = null
    private var mMeetingHasChanged = false
    private var mIsPresenceChecking = false
    private var mAttendanceList = arrayListOf<String>()
    private var mNewMeetingId = ""

    private val mTouchListener = View.OnTouchListener { _, _ ->
        mMeetingHasChanged = true
        false
    }

    private val myDateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
        val dateString = StringBuilder().append(day).append(".").append(month + 1).append(".").append(year).toString()
        dateText.text = dateString
        mDate = SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).parse(dateString)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_meeting)
        setSupportActionBar(addMeetingToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.WHITE
        }

        mAuth = FirebaseAuth.getInstance()
        mFirestore = FirebaseFirestore.getInstance()
        mStorageRef = FirebaseStorage.getInstance().reference
        mAdapter = AttendanceListRecyclerAdapter()

        mMeeting = intent.getParcelableExtra("meeting")
        if (mMeeting == null) {
            invalidateOptionsMenu()
            title = getString(R.string.add_meeting)
        } else {
            title = getString(R.string.edit_meeting)
            meetingNameET.setText(mMeeting!!.name)
            meetingTypeSpinner.setSelection(mMeeting!!.meetingType)
            dateText.text = SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).format(mMeeting!!.date.toDate())
            mDate = mMeeting!!.date.toDate()
            mAttendanceList = mMeeting!!.attendanceList
        }

        attendanceList.layoutManager = LinearLayoutManager(this@MeetingEditorActivity)
        attendanceList.itemAnimator = DefaultItemAnimator()
        attendanceList.adapter = mAdapter
        attendanceList.animate()
                .alpha(0f)
                .withEndAction { attendanceList.visibility = View.INVISIBLE }
                .duration = 1

        mIsPresenceChecking = intent.getBooleanExtra("checking", false)
        if (mIsPresenceChecking)
            checkPresence()

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        saveMeetingBtn.setOnClickListener {
            if (!mIsPresenceChecking) {
                if (meetingNameET.text.isNullOrEmpty()) {
                    meetingNameET.error = getString(R.string.empty_meeting_name_error)
                    return@setOnClickListener
                } else if (mDate == null) {
                    setDateBtn.error = ""
                    Snackbar.make(addMeetingLayout, getString(R.string.empty_meeting_date_error), Snackbar.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val meetingValues = HashMap<String, Any>()
                meetingValues[firestoreKeyName] = meetingNameET.text.toString().trim()
                meetingValues[firestoreKeyDate] = Timestamp(mDate)
                meetingValues[firestoreKeyMeetingType] = meetingTypeSpinner.selectedItemPosition
                meetingValues[firestoreKeyAttendanceList] = mAttendanceList

                if (mMeeting == null) {
                    mFirestore.collection(firestoreCollectionCities)
                            .document(mAuth.currentUser!!.email!!.substring(0, mAuth.currentUser!!.email!!.indexOf("@")))
                            .collection(firestoreCollectionMeetings)
                            .document(meetingTypes[meetingTypeSpinner.selectedItemPosition])
                            .collection(firestoreCollectionMeetings)
                            .add(meetingValues)
                            .addOnSuccessListener { documentReference ->
                                Toast.makeText(this@MeetingEditorActivity,
                                        getString(R.string.meeting_saved), Toast.LENGTH_SHORT).show()
                                mNewMeetingId = documentReference.id
                                showCheckPresenceDialog()
                            }
                            .addOnFailureListener {
                                Toast.makeText(this@MeetingEditorActivity,
                                        getString(R.string.meeting_add_error), Toast.LENGTH_SHORT).show()
                            }
                } else {
                    if (mDate == mMeeting!!.date.toDate() && meetingNameET.text.toString().trim() == mMeeting!!.name
                            && meetingTypeSpinner.selectedItemPosition == mMeeting!!.meetingType
                            && mAttendanceList == mMeeting!!.attendanceList) {
                        finish()
                    } else {
                        mFirestore.collection(firestoreCollectionCities)
                                .document(mAuth.currentUser!!.email!!.substring(0, mAuth.currentUser!!.email!!.indexOf("@")))
                                .collection(firestoreCollectionMeetings)
                                .document(meetingTypes[meetingTypeSpinner.selectedItemPosition])
                                .collection(firestoreCollectionMeetings)
                                .document(mMeeting!!.id)
                                .set(meetingValues)
                                .addOnSuccessListener {
                                    Toast.makeText(this@MeetingEditorActivity,
                                            getString(R.string.meeting_updated), Toast.LENGTH_SHORT).show()
                                    finish()
                                }
                                .addOnFailureListener {
                                    Toast.makeText(this@MeetingEditorActivity,
                                            getString(R.string.meeting_update_error), Toast.LENGTH_SHORT).show()
                                }
                    }
                }
            } else {
                mIsPresenceChecking = false
                mMeetingHasChanged = true
                mAttendanceList = mAdapter.mAttendanceList

                saveMeetingBtn.setImageDrawable(getDrawable(R.drawable.anim_done_to_save))
                (saveMeetingBtn.drawable as AnimatedVectorDrawable).start()
                attendanceList.animate()
                        .alpha(0f)
                        .withEndAction { attendanceList.visibility = View.INVISIBLE }
                        .duration = 400

                if (mMeeting == null) {
                    mFirestore.collection(firestoreCollectionCities)
                            .document(mAuth.currentUser!!.email!!.substring(0, mAuth.currentUser!!.email!!.indexOf("@")))
                            .collection(firestoreCollectionMeetings)
                            .document(meetingTypes[meetingTypeSpinner.selectedItemPosition])
                            .collection(firestoreCollectionMeetings)
                            .document(mNewMeetingId)
                            .update(firestoreKeyAttendanceList, mAttendanceList)
                    finish()
                } else {
                    mFirestore.collection(firestoreCollectionCities)
                            .document(mAuth.currentUser!!.email!!.substring(0, mAuth.currentUser!!.email!!.indexOf("@")))
                            .collection(firestoreCollectionMeetings)
                            .document(meetingTypes[meetingTypeSpinner.selectedItemPosition])
                            .collection(firestoreCollectionMeetings)
                            .document(mMeeting!!.id)
                            .update(firestoreKeyAttendanceList, mAttendanceList)
                    if (intent.getBooleanExtra("checking", false))
                        finish()
                    else
                        setDateBtn.visibility = View.VISIBLE
                }

            }
        }

        setDateBtn.setOnClickListener {
            val calendar = Calendar.getInstance()
            if (mDate != null) {
                calendar.time = mDate
            }
            DatePickerDialog(this@MeetingEditorActivity, myDateListener,
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        meetingNameET.setOnTouchListener(mTouchListener)
        meetingTypeSpinner.setOnTouchListener(mTouchListener)
        setDateBtn.setOnTouchListener(mTouchListener)
    }

    override fun onBackPressed() {
        if (mMeetingHasChanged || mIsPresenceChecking)
            showUnsavedChangesDialog()
        else
            super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_edit_meeting, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        if (mMeeting == null) {
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

    private fun showCheckPresenceDialog() =
            AlertDialog.Builder(this@MeetingEditorActivity)
                    .setMessage(R.string.check_presence_dialog_msg)
                    .setCancelable(false)
                    .setPositiveButton(R.string.yes) { dialog, _ ->
                        dialog.dismiss()
                        checkPresence()
                    }
                    .setNegativeButton(R.string.no) { dialog, _ ->
                        dialog.dismiss()
                        finish()
                    }
                    .create()
                    .show()

    private fun checkPresence() {
        mIsPresenceChecking = true
        saveMeetingBtn.setImageDrawable(getDrawable(R.drawable.anim_save_to_done))
        (saveMeetingBtn.drawable as AnimatedVectorDrawable).start()
        attendanceList.animate()
                .alpha(1f)
                .withEndAction { attendanceList.visibility = View.VISIBLE }
                .duration = 400
        attendanceList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy < 0 && !saveMeetingBtn.isShown)
                    saveMeetingBtn.show()
                else if (dy > 0 && saveMeetingBtn.isShown)
                    saveMeetingBtn.hide()
            }
        })
        setDateBtn.visibility = View.INVISIBLE

        if (mAdapter.mMembersList.isEmpty()) {
            loadingIndicator.visibility = View.VISIBLE

            val city = mAuth.currentUser!!.email!!.substring(0, mAuth.currentUser!!.email!!.indexOf("@"))
            val membersQuery = mFirestore.collection(firestoreCollectionCities)
                    .document(city)
                    .collection(FirestoreUtils.firestoreCollectionMembers)
                    .orderBy(firestoreKeyName, Query.Direction.ASCENDING)
            membersQuery.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (firebaseFirestoreException != null) {
                    Log.e("MembersActivity", firebaseFirestoreException.toString())
                    return@addSnapshotListener
                }

                val memberList = querySnapshot!!.toObjects(Member::class.java)
                querySnapshot.forEachIndexed { index, queryDocumentSnapshot ->
                    memberList[index].id = queryDocumentSnapshot.id
                }
                mAdapter.setLists(memberList, mAttendanceList)

                loadingIndicator.animate().alpha(0f).duration = 100
                if (querySnapshot.isEmpty) {
                    emptyView.visibility = View.VISIBLE
                } else {
                    emptyView.visibility = View.INVISIBLE
                }
            }
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
                    .setPositiveButton(R.string.delete) { _, _ -> deleteMeeting() }
                    .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
                    .create()
                    .show()

    private fun deleteMeeting() {
        if (mMeeting != null) {
            mFirestore.collection(firestoreCollectionCities)
                    .document(mAuth.currentUser!!.email!!.substring(0, mAuth.currentUser!!.email!!.indexOf("@")))
                    .collection(firestoreCollectionMeetings)
                    .document(meetingTypes[mMeeting!!.meetingType])
                    .collection(firestoreCollectionMeetings)
                    .document(mMeeting!!.id)
                    .delete()
                    .addOnSuccessListener {
                        Toast.makeText(this@MeetingEditorActivity, getString(R.string.meeting_delete_successfully),
                                Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this@MeetingEditorActivity, getString(R.string.delete_error), Toast.LENGTH_SHORT).show()
                    }
        }
        finish()
    }
}
