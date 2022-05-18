package pl.mftau.mftau.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.transition.MaterialElevationScale
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.FragmentMeetingBinding
import pl.mftau.mftau.view.adapters.MeetingsRecyclerAdapter
import pl.mftau.mftau.viewmodel.MeetingsViewModel

class MeetingsListFragment : BindingFragment<FragmentMeetingBinding>() {

    companion object {
        fun newInstance(meetingType: Int): MeetingsListFragment {
            return MeetingsListFragment().apply {
                arguments = Bundle().apply {
                    putInt("meetingType", meetingType)
                }
            }
        }
    }

    private lateinit var mViewModel: MeetingsViewModel
    private lateinit var mAdapter: MeetingsRecyclerAdapter
    private var mMeetingType: Int = 0

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMeetingBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        activity?.let { mViewModel = ViewModelProvider(it)[MeetingsViewModel::class.java] }
        arguments?.let { mMeetingType = it.getInt("meetingType") }
        mAdapter = MeetingsRecyclerAdapter()

        binding.meetingsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
            adapter = mAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
        }

        mViewModel.getAllMeetings(mMeetingType).observe(viewLifecycleOwner) { meetings ->
            binding.meetingsRecyclerView.layoutAnimation =
                AnimationUtils.loadLayoutAnimation(
                    binding.meetingsRecyclerView.context,
                    R.anim.layout_animation_fall_down
                )
            mAdapter.setMeetingList(meetings)
            binding.loadingIndicator.hide()
            binding.emptyView.visibility =
                if (meetings.isEmpty()) View.VISIBLE else View.INVISIBLE
        }
    }
}
