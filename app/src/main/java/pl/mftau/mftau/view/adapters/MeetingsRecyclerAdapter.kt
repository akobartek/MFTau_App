package pl.mftau.mftau.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.ItemMeetingBinding
import pl.mftau.mftau.model.Meeting
import pl.mftau.mftau.view.fragments.MeetingsFragmentDirections

class MeetingsRecyclerAdapter : RecyclerView.Adapter<MeetingsRecyclerAdapter.MeetingsViewHolder>() {

    private var mMeetingList = listOf<Meeting>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeetingsViewHolder =
        MeetingsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_meeting, parent, false
            )
        )

    override fun onBindViewHolder(holder: MeetingsViewHolder, position: Int) {
        holder.binding.meeting = mMeetingList[position]
        holder.binding.attendanceListBtn.setOnClickListener {
            it.findNavController()
                .navigate(
                    MeetingsFragmentDirections.showPresenceCheckFragment(mMeetingList[position], 0)
                )
        }
        holder.binding.root.setOnClickListener {
            it.findNavController()
                .navigate(MeetingsFragmentDirections.showMeetingEditorFragment(mMeetingList[position]))
        }
    }

    override fun getItemCount(): Int = mMeetingList.size

    fun setMeetingList(list: List<Meeting>) {
        mMeetingList = list
        notifyDataSetChanged()
    }

    inner class MeetingsViewHolder(val binding: ItemMeetingBinding) :
        RecyclerView.ViewHolder(binding.root)
}