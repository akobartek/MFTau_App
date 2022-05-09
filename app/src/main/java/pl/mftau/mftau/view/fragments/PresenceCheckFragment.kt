package pl.mftau.mftau.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.mftau.mftau.databinding.FragmentPresenceCheckBinding
import pl.mftau.mftau.model.local_db.Meeting
import pl.mftau.mftau.utils.FirestoreUtils
import pl.mftau.mftau.view.adapters.PresenceCheckRecyclerAdapter
import pl.mftau.mftau.viewmodel.MainViewModel

class PresenceCheckFragment : BindingFragment<FragmentPresenceCheckBinding>() {

    companion object {
        var listHasChanged = false
    }

    private lateinit var mViewModel: MainViewModel
    private lateinit var mAdapter: PresenceCheckRecyclerAdapter
    private lateinit var mMeeting: Meeting
    private var mIsMeetingNew: Int = 0

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentPresenceCheckBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        binding.presenceCheckToolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        activity?.let {
            mViewModel = ViewModelProvider(it)[MainViewModel::class.java]
        }
        arguments?.let { bundle ->
            mMeeting = PresenceCheckFragmentArgs.fromBundle(bundle).meeting
            mIsMeetingNew = PresenceCheckFragmentArgs.fromBundle(bundle).isMeetingNew
        }
        mAdapter = PresenceCheckRecyclerAdapter()
        binding.contentPresenceCheck.loadingIndicator.show()

        binding.contentPresenceCheck.attendanceRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
            adapter = mAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy < 0 && !binding.saveMeetingBtn.isShown)
                        binding.saveMeetingBtn.show()
                    else if (dy > 0 && binding.saveMeetingBtn.isShown)
                        binding.saveMeetingBtn.hide()
                }
            })
        }

        mViewModel.getAllMembers().observe(viewLifecycleOwner) { members ->
            mAdapter.setLists(members, mMeeting.attendanceList, mMeeting.absenceList)
            binding.contentPresenceCheck.loadingIndicator.hide()
            binding.contentPresenceCheck.emptyView.visibility =
                if (members.isEmpty()) View.VISIBLE else View.INVISIBLE
        }

        binding.saveMeetingBtn.setOnClickListener {
            when (mIsMeetingNew) {
                0 -> {
                    mViewModel.updateAttendanceList(
                        requireActivity(), mMeeting.id, mMeeting.meetingType,
                        mAdapter.attendanceList, mAdapter.absenceList
                    )
                    findNavController().navigateUp()
                }
                1 -> {
                    val meetingValues = HashMap<String, Any>()
                    meetingValues[FirestoreUtils.firestoreKeyName] = mMeeting.name
                    meetingValues[FirestoreUtils.firestoreKeyNotes] = mMeeting.notes
                    meetingValues[FirestoreUtils.firestoreKeyDate] = mMeeting.date
                    meetingValues[FirestoreUtils.firestoreKeyMeetingType] = mMeeting.meetingType
                    meetingValues[FirestoreUtils.firestoreKeyAttendanceList] =
                        mAdapter.attendanceList
                    meetingValues[FirestoreUtils.firestoreKeyAbsenceList] = mAdapter.absenceList
                    mViewModel.addMeetingWithAttendanceList(
                        requireActivity(),
                        mMeeting.meetingType,
                        meetingValues
                    )
                    findNavController().navigate(PresenceCheckFragmentDirections.moveBackToMeetingsList())
                }
            }
        }
    }
}
