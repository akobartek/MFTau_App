package pl.mftau.mftau.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.ItemEmausBinding
import pl.mftau.mftau.db.entities.MemberEntity
import pl.mftau.mftau.utils.GlideApp
import kotlin.random.Random

class EmausRecyclerAdapter : RecyclerView.Adapter<EmausRecyclerAdapter.EmausViewHolder>() {

    inner class EmausViewHolder(val binding: ItemEmausBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var mDraws = arrayListOf<Pair<MemberEntity?, MemberEntity?>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = EmausViewHolder(
        ItemEmausBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: EmausViewHolder, position: Int) {
        with(holder.binding) {
            val pair = mDraws[position]
            if ("example@mftau.pl" != FirebaseAuth.getInstance().currentUser!!.email!!) {
                var storageReference = FirebaseStorage.getInstance()
                    .reference.child("members/${pair.first?.id}.jpg")
                GlideApp.with(root.context)
                    .load(storageReference)
                    .circleCrop()
                    .placeholder(R.drawable.ic_user)
                    .into(drawFirstPicture)

                storageReference = FirebaseStorage.getInstance()
                    .reference.child("/members/${pair.second?.id}.jpg")
                GlideApp.with(root.context)
                    .load(storageReference)
                    .circleCrop()
                    .placeholder(R.drawable.ic_user)
                    .into(drawSecondPicture)
            } else {
                var randomNumber = Random.nextInt(100)
                var address = if (pair.first?.name?.last() == 'a')
                    "https://randomuser.me/api/portraits/women/$randomNumber.jpg"
                else
                    "https://randomuser.me/api/portraits/men/$randomNumber.jpg"

                GlideApp.with(root.context)
                    .load(address)
                    .circleCrop()
                    .placeholder(R.drawable.ic_user)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(drawFirstPicture)

                randomNumber = Random.nextInt(100)
                address = if (pair.second?.name?.last() == 'a')
                    "https://randomuser.me/api/portraits/women/$randomNumber.jpg"
                else
                    "https://randomuser.me/api/portraits/men/$randomNumber.jpg"

                GlideApp.with(root.context)
                    .load(address)
                    .circleCrop()
                    .placeholder(R.drawable.ic_user)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(drawSecondPicture)
            }
            val pairOfNames = "${pair.first?.name} + ${pair.second?.name}"
            drawName.text = pairOfNames
        }
    }

    override fun getItemCount(): Int = mDraws.size

    @SuppressLint("NotifyDataSetChanged")
    fun setDraws(draws: ArrayList<Pair<MemberEntity?, MemberEntity?>>) {
        mDraws = draws
        notifyDataSetChanged()
    }
}