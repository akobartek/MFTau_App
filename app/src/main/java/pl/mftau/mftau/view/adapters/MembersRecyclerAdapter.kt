package pl.mftau.mftau.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.ItemMemberBinding
import pl.mftau.mftau.model.local_db.Member
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
        with(holder.binding) {
            member = mMembersList[position]
            root.setOnClickListener {
                memberPhoto.transitionName = "shared_photo"
                memberName.transitionName = "shared_name"
                memberCity.transitionName = "shared_city"
                val extras = FragmentNavigatorExtras(
                    memberPhoto to "shared_photo",
                    memberName to "shared_name",
                    memberCity to "shared_city"
                )
                it.findNavController().navigate(
                    MembersFragmentDirections.showMemberEditorFragment(mMembersList[position]),
                    extras
                )
            }
        }
    }

    override fun getItemCount(): Int = mMembersList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setMemberList(list: List<Member>) {
        mMembersList = list
        notifyDataSetChanged()
    }
}