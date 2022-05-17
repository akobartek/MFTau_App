package pl.mftau.mftau.view.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.Timestamp
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.FragmentMeetingEditorBinding
import pl.mftau.mftau.model.local_db.Meeting
import pl.mftau.mftau.utils.FirestoreUtils
import pl.mftau.mftau.utils.getDateFormatted
import pl.mftau.mftau.utils.hideKeyboard
import pl.mftau.mftau.view.ui.ClearErrorTextWatcher
import pl.mftau.mftau.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class MeetingEditorFragment : BindingFragment<FragmentMeetingEditorBinding>() {

    companion object {
        var meetingHasChanged = false
    }

    private lateinit var mViewModel: MainViewModel
    private var mMeeting: Meeting? = null
    private var mMeetingDate = Date()

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMeetingEditorBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        meetingHasChanged = false
        inflateToolbarMenu(binding.meetingEditorToolbar)

        activity?.let {
            mViewModel = ViewModelProvider(it)[MainViewModel::class.java]
        }

        arguments?.let { bundle ->
            mMeeting = MeetingEditorFragmentArgs.fromBundle(bundle).meeting
            binding.meetingEditorToolbarTitle.text =
                if (mMeeting == null) getString(R.string.add_meeting) else getString(R.string.edit_meeting)
            with(binding.contentMeetingEditor) {
                mMeeting?.let {
                    meetingNameET.setText(it.name)
                    meetingTypeTV.setText(resources.getStringArray(R.array.meeting_types)[it.meetingType])
                    meetingNotesET.setText(it.notes)
                    mMeetingDate = it.date.toDate()
                }
            }
            setupToolbarMenuIcons(binding.meetingEditorToolbar.menu)
        }
        binding.contentMeetingEditor.dateText.text = mMeetingDate.getDateFormatted()

        setViewsListeners()
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

    @SuppressLint("ClickableViewAccessibility")
    private fun setViewsListeners() {
        with(binding.contentMeetingEditor) {
            binding.saveMeetingBtn.setOnClickListener {
                val meetingValues = HashMap<String, Any>()
                meetingValues[FirestoreUtils.firestoreKeyName] =
                    meetingNameET.text.toString().trim()
                meetingValues[FirestoreUtils.firestoreKeyNotes] =
                    meetingNotesET.text.toString().trim()
                meetingValues[FirestoreUtils.firestoreKeyDate] = Timestamp(mMeetingDate)
                meetingValues[FirestoreUtils.firestoreKeyMeetingType] =
                    resources.getStringArray(R.array.meeting_types)
                        .indexOf(meetingTypeTV.text.toString())
                meetingValues[FirestoreUtils.firestoreKeyAttendanceList] =
                    mMeeting?.attendanceList ?: arrayListOf<String>()
                meetingValues[FirestoreUtils.firestoreKeyAbsenceList] =
                    mMeeting?.absenceList ?: HashMap<String, String>()

                if (meetingNameET.text.isNullOrBlank()) {
                    nameInputLayout.error = getString(R.string.empty_meeting_name_error)
                    return@setOnClickListener
                }
                if (meetingTypeTV.text.isNullOrBlank()) {
                    meetingTypeInputLayout.error = getString(R.string.empty_meeting_type_error)
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
                            requireActivity(),
                            mMeeting!!.id,
                            meetingValues[FirestoreUtils.firestoreKeyMeetingType] as Int,
                            meetingValues
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

            setDateIcon.setOnClickListener(mDateClickListener)
            dateText.setOnClickListener(mDateClickListener)

            addMeetingLayout.setOnClickListener { requireActivity().hideKeyboard() }

            meetingNameET.setOnTouchListener(mTouchListener)
            meetingNotesET.setOnTouchListener(mTouchListener)
            meetingTypeInputLayout.setOnTouchListener(mTouchListener)
            setDateIcon.setOnTouchListener(mTouchListener)
            dateText.setOnTouchListener(mTouchListener)

            meetingNameET.addTextChangedListener(ClearErrorTextWatcher(nameInputLayout))
            meetingTypeTV.addTextChangedListener(ClearErrorTextWatcher(meetingTypeInputLayout))
        }
    }

    private fun showCheckPresenceDialog(meetingValues: HashMap<String, Any>) =
        MaterialAlertDialogBuilder(requireContext())
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
        MaterialAlertDialogBuilder(requireContext())
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
        binding.contentMeetingEditor.dateText.text = dateString
    }
}
