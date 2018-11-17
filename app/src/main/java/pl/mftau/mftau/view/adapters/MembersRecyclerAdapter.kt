package pl.mftau.mftau.view.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.item_member.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.model.Member
import pl.mftau.mftau.utils.GlideApp
import pl.mftau.mftau.view.activities.MemberEditorActivity

class MembersRecyclerAdapter : RecyclerView.Adapter<MembersRecyclerAdapter.MembersViewHolder>() {

    private var mMembersList = listOf<Member>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MembersViewHolder =
            MembersViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_member, parent, false))

    override fun onBindViewHolder(holder: MembersViewHolder, position: Int) =
            holder.bindView(mMembersList[position])

    override fun getItemCount(): Int = mMembersList.size

    fun setMemberList(list: List<Member>) {
        mMembersList = list
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
                    .circleCrop()
                    .placeholder(R.drawable.ic_user)
                    .into(itemView.memberPhoto)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, MemberEditorActivity::class.java)
                intent.putExtra("member", member)
                itemView.context.startActivity(intent)
            }
        }
    }
}