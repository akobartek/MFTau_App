package pl.mftau.mftau.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.item_member.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.model.Member
import pl.mftau.mftau.utils.GlideApp

class MembersRecyclerAdapter : RecyclerView.Adapter<MembersRecyclerAdapter.MembersViewHolder>() {

    private var memberList = listOf<Member>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MembersViewHolder =
            MembersViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_member, parent, false))

    override fun onBindViewHolder(holder: MembersViewHolder, position: Int) =
            holder.bindView(memberList[position])

    override fun getItemCount(): Int = memberList.size

    fun setMemberList(list: List<Member>) {
        memberList = list
        notifyDataSetChanged()
    }


    inner class MembersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(member: Member) {
            itemView.tag = member.id
            itemView.memberName.text = member.name
            itemView.memberCity.text = member.city

            val storageReference = FirebaseStorage.getInstance()
                    .reference.child("members/${member.id}.jpg")

            GlideApp.with(itemView.context)
                    .load(storageReference)
                    .transform(CircleCrop())
                    .placeholder(R.drawable.ic_user)
                    .into(itemView.memberPhoto)

//            if (member.isPhotoAdded) {
//                val storageReference = FirebaseStorage.getInstance()
//                        .reference.child("members/${member.id}")
//
//                GlideApp.with(itemView.context)
//                        .load(storageReference)
//                        .transform(CircleCrop())
//                        .into(itemView.memberPhoto)
//            } else {
//                GlideApp.with(itemView.context)
//                        .load(R.drawable.ic_user)
//                        .transform(CircleCrop())
//                        .into(itemView.memberPhoto)
//            }
        }
    }
}