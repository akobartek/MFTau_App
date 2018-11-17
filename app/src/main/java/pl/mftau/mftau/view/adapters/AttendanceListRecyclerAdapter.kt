package pl.mftau.mftau.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.item_presence_check.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.model.Member
import pl.mftau.mftau.utils.GlideApp

class AttendanceListRecyclerAdapter : RecyclerView.Adapter<AttendanceListRecyclerAdapter.AttendanceListViewHolder>() {

    var mMembersList = listOf<Member>()
    var mAttendanceList = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttendanceListViewHolder =
            AttendanceListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_presence_check, parent, false))

    override fun onBindViewHolder(holder: AttendanceListViewHolder, position: Int) =
            holder.bindView(mMembersList[position])

    override fun getItemCount(): Int = mMembersList.size

    fun setLists(memberList: List<Member>, attandanceList: ArrayList<String>) {
        mMembersList = memberList
        mAttendanceList = attandanceList
        notifyDataSetChanged()
    }

    inner class AttendanceListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(member: Member) {
            itemView.tag = member.id
            itemView.memberName.text = member.name
            // TODO () -> set correct checkbox value
            itemView.isPresentCheckBox.isChecked = mAttendanceList.contains(member.id)

            val storageReference = FirebaseStorage.getInstance()
                    .reference.child("members/${member.id}.jpg")

            GlideApp.with(itemView.context)
                    .load(storageReference)
                    .circleCrop()
                    .placeholder(R.drawable.ic_user)
                    .into(itemView.memberPhoto)

            itemView.isPresentCheckBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    if (!mAttendanceList.contains(member.id)) {
                        mAttendanceList.add(member.id)
                    }
                } else {
                    if (mAttendanceList.contains(member.id)) {
                        mAttendanceList.remove(member.id)
                    }
                }
            }

            itemView.setOnClickListener {
                itemView.isPresentCheckBox.isChecked = true
                mAttendanceList.add(member.id)
            }
        }
    }
}