package pl.mftau.mftau.view.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_meeting.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.model.Meeting
import pl.mftau.mftau.view.activities.MeetingEditorActivity
import java.text.SimpleDateFormat
import java.util.*

class MeetingsRecyclerAdapter : RecyclerView.Adapter<MeetingsRecyclerAdapter.MeetingsViewHolder>() {

    private var mMeetingList = listOf<Meeting>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeetingsViewHolder =
            MeetingsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_meeting, parent, false))

    override fun onBindViewHolder(holder: MeetingsViewHolder, position: Int) =
            holder.bindView(mMeetingList[position])

    override fun getItemCount(): Int = mMeetingList.size

    fun setMeetingList(list: List<Meeting>) {
        mMeetingList = list
        notifyDataSetChanged()
    }


    inner class MeetingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(meeting: Meeting) {
            itemView.tag = meeting.id
            itemView.meetingName.text = meeting.name
            itemView.meetingDate.text = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                    .format(meeting.date.toDate())

            itemView.attendanceListBtn.setOnClickListener {
                val intent = Intent(itemView.context, MeetingEditorActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.putExtra("meeting", meeting)
                        .putExtra("checking", true)
                itemView.context.startActivity(intent)
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, MeetingEditorActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.putExtra("meeting", meeting)
                        .putExtra("checking", false)
                itemView.context.startActivity(intent)
            }
        }
    }
}