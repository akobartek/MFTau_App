package pl.mftau.mftau.view.fragments


import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.firebase.Timestamp
import kotlinx.android.synthetic.main.fragment_meeting_editor.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.model.Meeting
import pl.mftau.mftau.utils.FirestoreUtils
import pl.mftau.mftau.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class MeetingEditorFragment : Fragment() {

    companion object {
        var meetingHasChanged = false
    }

    private lateinit var mViewModel: MainViewModel
    private var mMeeting: Meeting? = null
    private var mMeetingDate = Date()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        meetingHasChanged = false
        return inflater.inflate(R.layout.fragment_meeting_editor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            mViewModel = ViewModelProviders.of(it).get(MainViewModel::class.java)
        }

        view.meetingTypeSpinner.adapter = object : ArrayAdapter<String>(view.context,
                R.layout.item_spinner, resources.getStringArray(R.array.meeting_types)) {
            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val dropDownView = super.getDropDownView(position, convertView, parent)
                (dropDownView as TextView).setTextColor(Color.BLACK)

                return dropDownView
            }
        }

        arguments?.let { bundle ->
            mMeeting = MeetingEditorFragmentArgs.fromBundle(bundle).meeting
            mMeeting?.let {
                view.meetingNameET.setText(it.name)
                view.meetingTypeSpinner.setSelection(it.meetingType)
                mMeetingDate = it.date.toDate()
            }
            activity?.invalidateOptionsMenu()
        }
        view.dateText.text = mViewModel.getDateFormatted(mMeetingDate)

        setOnClickListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_meeting_edit, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        if (mMeeting == null) {
            menu.findItem(R.id.action_delete_meeting)?.isVisible = false
            menu.findItem(R.id.action_check_presence)?.isVisible = false
        }
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_check_presence -> {
                findNavController().navigate(MeetingEditorFragmentDirections
                        .showPresenceCheckFragment(mMeeting!!, 0))
                true
            }
            R.id.action_delete_meeting -> {
                showDeleteConfirmationDialog()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun setOnClickListeners() {
        view?.saveMeetingBtn?.setOnClickListener {
            val meetingValues = HashMap<String, Any>()
            meetingValues[FirestoreUtils.firestoreKeyName] = view!!.meetingNameET.text.toString().trim()
            meetingValues[FirestoreUtils.firestoreKeyDate] = Timestamp(mMeetingDate)
            meetingValues[FirestoreUtils.firestoreKeyMeetingType] = view!!.meetingTypeSpinner.selectedItemPosition
            meetingValues[FirestoreUtils.firestoreKeyAttendanceList] = mMeeting?.attendanceList ?: arrayListOf<String>()
            meetingValues[FirestoreUtils.firestoreKeyAbsenceList] = mMeeting?.absenceList ?: HashMap<String, String>()

            if (view!!.meetingNameET.text.isNullOrBlank()) {
                view!!.meetingNameET.error = getString(R.string.empty_meeting_name_error)
                return@setOnClickListener
            }

            if (mMeeting == null) {
                showCheckPresenceDialog(meetingValues)
            } else {
                if (!(meetingValues[FirestoreUtils.firestoreKeyDate] == mMeeting!!.date
                                && meetingValues[FirestoreUtils.firestoreKeyName] == mMeeting!!.name
                                && meetingValues[FirestoreUtils.firestoreKeyMeetingType] == mMeeting!!.meetingType)) {
                    mViewModel.updateMeeting(activity!!, mMeeting!!.id,
                            meetingValues[FirestoreUtils.firestoreKeyMeetingType] as Int, meetingValues)
                }
                findNavController().navigateUp()
            }
        }

        view?.setDateIcon?.setOnClickListener(mDateClickListener)
        view?.dateText?.setOnClickListener(mDateClickListener)

        view?.addMeetingLayout?.setOnClickListener(mHideKeyboardClickListener)

        view?.meetingNameET?.setOnTouchListener(mTouchListener)
        view?.meetingTypeSpinner?.setOnTouchListener(mTouchListener)
        view?.setDateIcon?.setOnTouchListener(mTouchListener)
        view?.dateText?.setOnTouchListener(mTouchListener)
    }

    private fun showCheckPresenceDialog(meetingValues: HashMap<String, Any>) =
            AlertDialog.Builder(context!!)
                    .setMessage(R.string.check_presence_dialog_msg)
                    .setCancelable(false)
                    .setPositiveButton(R.string.yes) { dialog, _ ->
                        dialog.dismiss()
                        mMeeting = Meeting(
                                name = meetingValues[FirestoreUtils.firestoreKeyName] as String,
                                meetingType = meetingValues[FirestoreUtils.firestoreKeyMeetingType] as Int,
                                date = meetingValues[FirestoreUtils.firestoreKeyDate] as Timestamp
                        )
                        findNavController().navigate(MeetingEditorFragmentDirections
                                .showPresenceCheckFragment(mMeeting!!, 1))
                    }
                    .setNegativeButton(R.string.no) { dialog, _ ->
                        dialog.dismiss()
                        mViewModel.addMeeting(activity!!,
                                meetingValues[FirestoreUtils.firestoreKeyMeetingType] as Int, meetingValues)
                        findNavController().navigateUp()
                    }
                    .create()
                    .show()

    private fun showDeleteConfirmationDialog() =
            AlertDialog.Builder(context!!)
                    .setMessage(R.string.meeting_delete_dialog_msg)
                    .setCancelable(false)
                    .setPositiveButton(R.string.delete) { dialog, _ ->
                        dialog.dismiss()
                        mViewModel.deleteMeeting(activity!!, mMeeting!!.id, mMeeting!!.meetingType)
                        findNavController().navigateUp()
                    }
                    .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
                    .create()
                    .show()


    private val mTouchListener = View.OnTouchListener { _, _ ->
        meetingHasChanged = true
        false
    }
    private val mHideKeyboardClickListener = View.OnClickListener {
        (it.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
    }
    private val mDateClickListener = View.OnClickListener {
        (it.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)

        val calendar = Calendar.getInstance()
        calendar.time = mMeetingDate

        DatePickerDialog(context!!, myDateListener,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
    }
    private val myDateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
        val dateString = StringBuilder().append(day).append(".").append(month + 1).append(".").append(year).toString()
        mMeetingDate = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(dateString)
        view?.dateText?.text = dateString
    }
}
