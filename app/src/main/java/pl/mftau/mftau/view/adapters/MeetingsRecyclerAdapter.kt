package pl.mftau.mftau.view.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.ItemMeetingBinding
import pl.mftau.mftau.model.Meeting
import pl.mftau.mftau.view.activities.MeetingEditorActivity

class MeetingsRecyclerAdapter : RecyclerView.Adapter<MeetingsRecyclerAdapter.MeetingsViewHolder>() {

    private var mMeetingList = listOf<Meeting>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeetingsViewHolder =
            MeetingsViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_meeting, parent, false))

    override fun onBindViewHolder(holder: MeetingsViewHolder, position: Int) {
        holder.binding.meeting = mMeetingList[position]
        holder.binding.attendanceListBtn.setOnClickListener {
            holder.startEditorActivity(mMeetingList[position], true)
        }
        holder.binding.root.setOnClickListener {
            holder.startEditorActivity(mMeetingList[position], false)
        }
    }

    override fun getItemCount(): Int = mMeetingList.size

    fun setMeetingList(list: List<Meeting>) {
        mMeetingList = list
        notifyDataSetChanged()
    }

    inner class MeetingsViewHolder(val binding: ItemMeetingBinding) : RecyclerView.ViewHolder(binding.root) {

        fun startEditorActivity(meeting: Meeting, isChecking: Boolean) {
            val intent = Intent(binding.root.context, MeetingEditorActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra("meeting", meeting)
                    .putExtra("checking", isChecking)
            itemView.context.startActivity(intent)
        }
    }
}