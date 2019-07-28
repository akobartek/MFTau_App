package pl.mftau.mftau.view.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_presence_check.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.model.Meeting
import pl.mftau.mftau.utils.FirestoreUtils
import pl.mftau.mftau.view.adapters.PresenceCheckRecyclerAdapter
import pl.mftau.mftau.viewmodel.MainViewModel

class PresenceCheckFragment : Fragment() {

    companion object {
        var listHasChanged = false
    }

    private lateinit var mViewModel: MainViewModel
    private lateinit var mAdapter: PresenceCheckRecyclerAdapter
    private lateinit var mMeeting: Meeting
    private var mIsMeetingNew: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        listHasChanged = false
        return inflater.inflate(R.layout.fragment_presence_check, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            mViewModel = ViewModelProviders.of(it).get(MainViewModel::class.java)
        }
        arguments?.let { bundle ->
            mMeeting = PresenceCheckFragmentArgs.fromBundle(bundle).meeting
            mIsMeetingNew = PresenceCheckFragmentArgs.fromBundle(bundle).isMeetingNew
        }
        mAdapter = PresenceCheckRecyclerAdapter()
        view.loadingIndicator.show()

        view.attendanceRecyclerView.layoutManager = LinearLayoutManager(view.context)
        view.attendanceRecyclerView.itemAnimator = DefaultItemAnimator()
        view.attendanceRecyclerView.adapter = mAdapter

        view.attendanceRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy < 0 && !view.saveMeetingBtn.isShown)
                    view.saveMeetingBtn.show()
                else if (dy > 0 && view.saveMeetingBtn.isShown)
                    view.saveMeetingBtn.hide()
            }
        })

        mViewModel.getAllMembers().observe(this@PresenceCheckFragment, Observer { members ->
            mAdapter.setLists(members, mMeeting.attendanceList, mMeeting.absenceList)
            view.loadingIndicator.hide()
            if (members.isEmpty()) {
                view.emptyView.visibility = View.VISIBLE
            } else {
                view.emptyView.visibility = View.INVISIBLE
            }
        })

        view.saveMeetingBtn.setOnClickListener {
            when (mIsMeetingNew) {
                0 -> {
                    mViewModel.updateAttendanceList(
                        activity!!, mMeeting.id, mMeeting.meetingType,
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
                    meetingValues[FirestoreUtils.firestoreKeyAttendanceList] = mAdapter.attendanceList
                    meetingValues[FirestoreUtils.firestoreKeyAbsenceList] = mAdapter.absenceList
                    mViewModel.addMeetingWithAttendanceList(activity!!, mMeeting.meetingType, meetingValues)
                    findNavController().navigate(PresenceCheckFragmentDirections.moveBackToMeetingsList())
                }
            }
        }
    }
}
