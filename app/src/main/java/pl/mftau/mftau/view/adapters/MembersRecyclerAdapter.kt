package pl.mftau.mftau.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.ItemMemberBinding
import pl.mftau.mftau.model.Member
import pl.mftau.mftau.view.fragments.MembersFragmentDirections

class MembersRecyclerAdapter : RecyclerView.Adapter<MembersRecyclerAdapter.MembersViewHolder>() {

    inner class MembersViewHolder(val binding: ItemMemberBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var mMembersList = listOf<Member>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MembersViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_member, parent, false
        )
    )

    override fun onBindViewHolder(holder: MembersViewHolder, position: Int) {
        holder.binding.member = mMembersList[position]
        holder.binding.root.setOnClickListener {
            it.findNavController()
                .navigate(MembersFragmentDirections.showMemberEditorFragment(mMembersList[position]))
        }
    }

    override fun getItemCount(): Int = mMembersList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setMemberList(list: List<Member>) {
        mMembersList = list
        notifyDataSetChanged()
    }
}