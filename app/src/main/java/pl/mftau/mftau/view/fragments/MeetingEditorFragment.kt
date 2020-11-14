package pl.mftau.mftau.view.fragments


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.Timestamp
import kotlinx.android.synthetic.main.content_meeting_editor.view.*
import kotlinx.android.synthetic.main.fragment_meeting_editor.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.model.Meeting
import pl.mftau.mftau.utils.FirestoreUtils
import pl.mftau.mftau.utils.getDateFormatted
import pl.mftau.mftau.utils.hideKeyboard
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        meetingHasChanged = false
        return inflater.inflate(R.layout.fragment_meeting_editor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inflateToolbarMenu(view.meetingEditorToolbar)

        activity?.let {
            mViewModel = ViewModelProvider(it).get(MainViewModel::class.java)
        }
        view.meetingTypeSpinner.adapter = object :
            ArrayAdapter<String>(
                view.context, R.layout.item_spinner, resources.getStringArray(R.array.meeting_types)
            ) {
            override fun getDropDownView(
                position: Int, convertView: View?, parent: ViewGroup
            ): View {
                val dropDownView = super.getDropDownView(position, convertView, parent)
                (dropDownView as TextView).setTextColor(Color.BLACK)

                return dropDownView
            }
        }

        arguments?.let { bundle ->
            mMeeting = MeetingEditorFragmentArgs.fromBundle(bundle).meeting
            view.meetingEditorToolbarTitle.text =
                if (mMeeting == null) getString(R.string.add_meeting) else getString(R.string.edit_meeting)
            mMeeting?.let {
                view.meetingNameET.setText(it.name)
                view.meetingTypeSpinner.setSelection(it.meetingType)
                view.meetingNotesET.setText(it.notes)
                mMeetingDate = it.date.toDate()
            }
            setupToolbarMenuIcons(view.meetingEditorToolbar.menu)
        }
        view.dateText.text = mMeetingDate.getDateFormatted()

        setOnClickListeners()
    }

    private fun inflateToolbarMenu(toolbar: Toolbar) {
        toolbar.apply {
            setNavigationOnClickListener { findNavController().navigateUp() }
            inflateMenu(R.menu.menu_meeting_edit)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_check_presence -> {
                        findNavController().navigate(
                            MeetingEditorFragmentDirections.showPresenceCheckFragment(mMeeting!!, 0)
                        )
                        true
                    }
                    R.id.action_delete_meeting -> {
                        showDeleteConfirmationDialog()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun setupToolbarMenuIcons(menu: Menu) {
        if (mMeeting == null) {
            menu.findItem(R.id.action_delete_meeting)?.isVisible = false
            menu.findItem(R.id.action_check_presence)?.isVisible = false
        }
    }

    private fun setOnClickListeners() {
        view?.saveMeetingBtn?.setOnClickListener {
            val meetingValues = HashMap<String, Any>()
            meetingValues[FirestoreUtils.firestoreKeyName] =
                requireView().meetingNameET.text.toString().trim()
            meetingValues[FirestoreUtils.firestoreKeyNotes] =
                requireView().meetingNotesET.text.toString().trim()
            meetingValues[FirestoreUtils.firestoreKeyDate] = Timestamp(mMeetingDate)
            meetingValues[FirestoreUtils.firestoreKeyMeetingType] =
                requireView().meetingTypeSpinner.selectedItemPosition
            meetingValues[FirestoreUtils.firestoreKeyAttendanceList] =
                mMeeting?.attendanceList ?: arrayListOf<String>()
            meetingValues[FirestoreUtils.firestoreKeyAbsenceList] =
                mMeeting?.absenceList ?: HashMap<String, String>()

            if (requireView().meetingNameET.text.isNullOrBlank()) {
                requireView().meetingNameET.error = getString(R.string.empty_meeting_name_error)
                return@setOnClickListener
            }

            if (mMeeting == null) {
                showCheckPresenceDialog(meetingValues)
            } else {
                if (!(meetingValues[FirestoreUtils.firestoreKeyDate] == mMeeting!!.date
                            && meetingValues[FirestoreUtils.firestoreKeyName] == mMeeting!!.name
                            && meetingValues[FirestoreUtils.firestoreKeyNotes] == mMeeting!!.notes)
                ) {
                    mViewModel.updateMeeting(
                        requireActivity(), mMeeting!!.id,
                        meetingValues[FirestoreUtils.firestoreKeyMeetingType] as Int, meetingValues
                    )
                } else if (meetingValues[FirestoreUtils.firestoreKeyMeetingType] != mMeeting!!.meetingType) {
                    mViewModel.deleteMeeting(
                        requireActivity(), mMeeting!!.id, mMeeting!!.meetingType
                    )
                    mViewModel.addMeeting(
                        requireActivity(),
                        meetingValues[FirestoreUtils.firestoreKeyMeetingType] as Int,
                        meetingValues
                    )
                }
                findNavController().navigateUp()
            }
        }

        view?.setDateIcon?.setOnClickListener(mDateClickListener)
        view?.dateText?.setOnClickListener(mDateClickListener)

        view?.addMeetingLayout?.setOnClickListener { requireActivity().hideKeyboard() }

        view?.meetingNameET?.setOnTouchListener(mTouchListener)
        view?.meetingNotesET?.setOnTouchListener(mTouchListener)
        view?.meetingTypeSpinner?.setOnTouchListener(mTouchListener)
        view?.setDateIcon?.setOnTouchListener(mTouchListener)
        view?.dateText?.setOnTouchListener(mTouchListener)
    }

    private fun showCheckPresenceDialog(meetingValues: HashMap<String, Any>) =
        AlertDialog.Builder(requireContext())
            .setMessage(R.string.check_presence_dialog_msg)
            .setCancelable(false)
            .setPositiveButton(R.string.yes) { dialog, _ ->
                dialog.dismiss()
                mMeeting = Meeting(
                    name = meetingValues[FirestoreUtils.firestoreKeyName] as String,
                    notes = meetingValues[FirestoreUtils.firestoreKeyNotes] as String,
                    meetingType = meetingValues[FirestoreUtils.firestoreKeyMeetingType] as Int,
                    date = meetingValues[FirestoreUtils.firestoreKeyDate] as Timestamp
                )
                findNavController().navigate(
                    MeetingEditorFragmentDirections.showPresenceCheckFragment(mMeeting!!, 1)
                )
            }
            .setNegativeButton(R.string.no) { dialog, _ ->
                dialog.dismiss()
                mViewModel.addMeeting(
                    requireActivity(),
                    meetingValues[FirestoreUtils.firestoreKeyMeetingType] as Int,
                    meetingValues
                )
                findNavController().navigateUp()
            }
            .create()
            .show()

    private fun showDeleteConfirmationDialog() =
        AlertDialog.Builder(requireContext())
            .setMessage(R.string.meeting_delete_dialog_msg)
            .setCancelable(false)
            .setPositiveButton(R.string.delete) { dialog, _ ->
                dialog.dismiss()
                mViewModel.deleteMeeting(requireActivity(), mMeeting!!.id, mMeeting!!.meetingType)
                findNavController().navigateUp()
            }
            .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
            .create()
            .show()


    @SuppressLint("ClickableViewAccessibility")
    private val mTouchListener = View.OnTouchListener { _, _ ->
        meetingHasChanged = true
        false
    }
    private val mDateClickListener = View.OnClickListener {
        requireActivity().hideKeyboard()

        val calendar = Calendar.getInstance()
        calendar.time = mMeetingDate

        DatePickerDialog(
            requireContext(),
            myDateListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }
    private val myDateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
        val dateString =
            StringBuilder().append(day).append(".").append(month + 1).append(".").append(year)
                .toString()
        mMeetingDate = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(dateString)!!
        view?.dateText?.text = dateString
    }
}
