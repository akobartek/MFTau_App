package pl.mftau.mftau.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.ItemPresenceMeetingBinding
import pl.mftau.mftau.model.local_db.Meeting

class PresenceMeetingRecyclerAdapter :
    RecyclerView.Adapter<PresenceMeetingRecyclerAdapter.PresenceMeetingViewHolder>() {

    inner class PresenceMeetingViewHolder(val binding: ItemPresenceMeetingBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var mMeetingsList = listOf<Meeting>()
    private var mPresenceList = arrayListOf<String>()
    private var mAbsences = HashMap<String, String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PresenceMeetingViewHolder(
        ItemPresenceMeetingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: PresenceMeetingViewHolder, position: Int) {
        with(holder.binding) {
            val meeting = mMeetingsList[position]
            root.tag = meeting.id
            meetingName.text = meeting.name
            meetingDate.text = meeting.getDateFormatted()
            when {
                mPresenceList.contains(meeting.id) -> {
                    absenceReason.visibility = View.GONE
                    root.setBackgroundColor(
                        ContextCompat.getColor(
                            root.context, when (meeting.meetingType) {
                                0 -> R.color.meetingType1_color1
                                1 -> R.color.meetingType2_color1
                                else -> R.color.meetingType3_color1
                            }
                        )
                    )
                }
                mAbsences.containsKey(meeting.id) -> {
                    absenceReason.visibility = View.VISIBLE
                    absenceReason.text =
                        root.context.getString(R.string.reason_for_absence, mAbsences[meeting.id])
                    root.setBackgroundColor(
                        ContextCompat.getColor(
                            root.context, when (meeting.meetingType) {
                                0 -> R.color.meetingType1_color2
                                1 -> R.color.meetingType2_color2
                                else -> R.color.meetingType3_color2
                            }
                        )
                    )
                }
                else -> {
                    absenceReason.visibility = View.GONE
                    root.setBackgroundColor(
                        ContextCompat.getColor(
                            root.context, when (meeting.meetingType) {
                                0 -> R.color.meetingType1_color3
                                1 -> R.color.meetingType2_color3
                                else -> R.color.meetingType3_color3
                            }
                        )
                    )
                }
            }
        }
    }

    override fun getItemCount(): Int = mMeetingsList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setLists(
        meetings: List<Meeting>, presences: ArrayList<String>, absences: HashMap<String, String>
    ) {
        mMeetingsList = meetings
        mPresenceList = presences
        mAbsences = absences
        notifyDataSetChanged()
    }
}