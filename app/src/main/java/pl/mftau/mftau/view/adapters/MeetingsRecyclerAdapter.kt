package pl.mftau.mftau.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.ItemMeetingBinding
import pl.mftau.mftau.model.local_db.Meeting
import pl.mftau.mftau.view.fragments.MeetingsFragmentDirections

class MeetingsRecyclerAdapter : RecyclerView.Adapter<MeetingsRecyclerAdapter.MeetingsViewHolder>() {

    inner class MeetingsViewHolder(val binding: ItemMeetingBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var mMeetingList = listOf<Meeting>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MeetingsViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_meeting, parent, false
        )
    )

    override fun onBindViewHolder(holder: MeetingsViewHolder, position: Int) {
        with(holder.binding) {
            meeting = mMeetingList[position]
            attendanceListBtn.setOnClickListener {
                it.findNavController().navigate(
                    MeetingsFragmentDirections.showPresenceCheckFragment(mMeetingList[position], 0)
                )
            }
            root.setOnClickListener {
                meetingName.transitionName = "shared_name"
                meetingDate.transitionName = "shared_date"
                val extras = FragmentNavigatorExtras(
                    meetingName to "shared_name",
                    meetingDate to "shared_date"
                )
                it.findNavController().navigate(
                    MeetingsFragmentDirections.showMeetingEditorFragment(mMeetingList[position]),
                    extras
                )
            }
        }
    }

    override fun getItemCount(): Int = mMeetingList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setMeetingList(list: List<Meeting>) {
        mMeetingList = list
        notifyDataSetChanged()
    }
}