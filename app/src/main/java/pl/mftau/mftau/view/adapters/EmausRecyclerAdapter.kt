package pl.mftau.mftau.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.item_emaus.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.db.entities.MemberEntity
import pl.mftau.mftau.model.utils.GlideApp

class EmausRecyclerAdapter: RecyclerView.Adapter<EmausRecyclerAdapter.EmausViewHolder>() {

    private var mDraws = arrayListOf<Pair<MemberEntity?, MemberEntity?>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmausViewHolder =
            EmausViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_emaus, parent, false))

    override fun onBindViewHolder(holder: EmausViewHolder, position: Int) =
            holder.bindView(mDraws[position])

    override fun getItemCount(): Int = mDraws.size

    fun setDraws(draws: ArrayList<Pair<MemberEntity?, MemberEntity?>>) {
        mDraws = draws
        notifyDataSetChanged()
    }

    inner class EmausViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(pairOfMembers: Pair<MemberEntity?, MemberEntity?>) {
            itemView.drawName.text = "${pairOfMembers.first?.name} + ${pairOfMembers.second?.name}"

            var storageReference = FirebaseStorage.getInstance()
                    .reference.child("members/${pairOfMembers.first?.id}.jpg")
            GlideApp.with(itemView.context)
                    .load(storageReference)
                    .circleCrop()
                    .placeholder(R.drawable.ic_user)
                    .into(itemView.drawFirstPicture)

            storageReference = FirebaseStorage.getInstance()
                    .reference.child("/members/${pairOfMembers.second?.id}.jpg")
            GlideApp.with(itemView.context)
                    .load(storageReference)
                    .circleCrop()
                    .placeholder(R.drawable.ic_user)
                    .into(itemView.drawSecondPicture)
        }
    }
}