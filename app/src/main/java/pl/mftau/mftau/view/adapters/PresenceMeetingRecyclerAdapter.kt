package pl.mftau.mftau.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_presence_meeting.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.model.Meeting

class PresenceMeetingRecyclerAdapter :
    RecyclerView.Adapter<PresenceMeetingRecyclerAdapter.PresenceMeetingViewHolder>() {

    private var mMeetingsList = listOf<Meeting>()
    private var mPresenceList = arrayListOf<String>()
    private var mAbsenceList = HashMap<String, String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PresenceMeetingViewHolder =
        PresenceMeetingViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_presence_meeting, parent, false)
        )

    override fun onBindViewHolder(holder: PresenceMeetingViewHolder, position: Int) =
        holder.bindView(mMeetingsList[position])

    override fun getItemCount(): Int = mMeetingsList.size

    fun setLists(meetingsList: List<Meeting>, presenceList: ArrayList<String>, absenceList: HashMap<String, String>) {
        mMeetingsList = meetingsList
        mPresenceList = presenceList
        mAbsenceList = absenceList
        notifyDataSetChanged()
    }

    inner class PresenceMeetingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(meeting: Meeting) {
            itemView.tag = meeting.id
            itemView.meetingName.text = meeting.name
            itemView.meetingDate.text = meeting.getDateFormatted()
            when {
                mPresenceList.contains(meeting.id) -> {
                    itemView.absenceReason.visibility = View.GONE
                    when (meeting.meetingType) {
                        0 -> itemView.setBackgroundColor(
                            ContextCompat.getColor(itemView.context, R.color.meetingType1_color1)
                        )
                        1 -> itemView.setBackgroundColor(
                            ContextCompat.getColor(itemView.context, R.color.meetingType2_color1)
                        )
                        2 -> itemView.setBackgroundColor(
                            ContextCompat.getColor(itemView.context, R.color.meetingType3_color1)
                        )
                    }
                }
                mAbsenceList.containsKey(meeting.id) -> {
                    itemView.absenceReason.visibility = View.VISIBLE
                    itemView.absenceReason.text =
                        itemView.context.getString(R.string.reason_for_absence, mAbsenceList[meeting.id])
                    when (meeting.meetingType) {
                        0 -> itemView.setBackgroundColor(
                            ContextCompat.getColor(itemView.context, R.color.meetingType1_color2)
                        )
                        1 -> itemView.setBackgroundColor(
                            ContextCompat.getColor(itemView.context, R.color.meetingType2_color2)
                        )
                        2 -> itemView.setBackgroundColor(
                            ContextCompat.getColor(itemView.context, R.color.meetingType3_color2)
                        )
                    }
                }
                else -> {
                    itemView.absenceReason.visibility = View.GONE
                    when (meeting.meetingType) {
                        0 -> itemView.setBackgroundColor(
                            ContextCompat.getColor(itemView.context, R.color.meetingType1_color3)
                        )
                        1 -> itemView.setBackgroundColor(
                            ContextCompat.getColor(itemView.context, R.color.meetingType2_color3)
                        )
                        2 -> itemView.setBackgroundColor(
                            ContextCompat.getColor(itemView.context, R.color.meetingType3_color3)
                        )
                    }
                }
            }
        }
    }
}