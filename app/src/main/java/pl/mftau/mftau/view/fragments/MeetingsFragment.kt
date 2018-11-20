package pl.mftau.mftau.view.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_meeting.*
import pl.mftau.mftau.R
import pl.mftau.mftau.view.adapters.MeetingsRecyclerAdapter
import pl.mftau.mftau.viewmodel.MeetingsViewModel


class MeetingsFragment : Fragment() {

    companion object {
        fun newInstance(meetingType: Int): MeetingsFragment {
            return MeetingsFragment().apply {
                arguments = Bundle().apply {
                    putInt("meetingType", meetingType)
                }
            }
        }
    }

    private lateinit var mMeetingsViewModel: MeetingsViewModel
    private lateinit var mAdapter: MeetingsRecyclerAdapter
    private var meetingType: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_meeting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mMeetingsViewModel = ViewModelProviders.of(activity!!).get(MeetingsViewModel::class.java)
        meetingType = arguments!!.getInt("meetingType")
        mAdapter = MeetingsRecyclerAdapter()

        meetingsRecyclerView.layoutManager = LinearLayoutManager(view.context)
        meetingsRecyclerView.itemAnimator = DefaultItemAnimator()
        meetingsRecyclerView.adapter = mAdapter
        meetingsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val addMeetingBtn = activity!!.findViewById<FloatingActionButton>(R.id.addMeetingBtn)
                if (dy < 0 && !addMeetingBtn.isShown)
                    addMeetingBtn.show()
                else if (dy > 0 && addMeetingBtn.isShown)
                    addMeetingBtn.hide()
            }
        })

        mMeetingsViewModel.getAllMeetings(meetingType).observe(this@MeetingsFragment, Observer { meetings ->
            mAdapter.setMeetingList(meetings)

            loadingIndicator.hide()
            if (meetings.isEmpty()) {
                emptyView.visibility = View.VISIBLE
            } else {
                emptyView.visibility = View.INVISIBLE
            }
        })
    }
}
