package pl.mftau.mftau.view.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_meeting.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.view.adapters.MeetingsRecyclerAdapter
import pl.mftau.mftau.viewmodel.MainViewModel

class MeetingsListFragment : Fragment() {

    companion object {
        fun newInstance(meetingType: Int): MeetingsListFragment {
            return MeetingsListFragment().apply {
                arguments = Bundle().apply {
                    putInt("meetingType", meetingType)
                }
            }
        }
    }

    private lateinit var mViewModel: MainViewModel
    private lateinit var mAdapter: MeetingsRecyclerAdapter
    private var mMeetingType: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_meeting, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let { mViewModel = ViewModelProvider(it).get(MainViewModel::class.java) }
        arguments?.let { mMeetingType = it.getInt("meetingType") }
        mAdapter = MeetingsRecyclerAdapter()

        view.meetingsRecyclerView.layoutManager = LinearLayoutManager(view.context)
        view.meetingsRecyclerView.itemAnimator = DefaultItemAnimator()
        view.meetingsRecyclerView.adapter = mAdapter
        view.meetingsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val addMeetingBtn =
                    activity!!.findViewById<FloatingActionButton>(R.id.addMeetingBtn)
                if (dy < 0 && !addMeetingBtn.isShown)
                    addMeetingBtn.show()
                else if (dy > 0 && addMeetingBtn.isShown)
                    addMeetingBtn.hide()
            }
        })

        mViewModel.getAllMeetings(mMeetingType).observe(viewLifecycleOwner, { meetings ->
            mAdapter.setMeetingList(meetings)
            view.loadingIndicator.hide()
            if (meetings.isEmpty()) {
                view.emptyView.visibility = View.VISIBLE
            } else {
                view.emptyView.visibility = View.INVISIBLE
            }
        })
    }
}
