package pl.mftau.mftau.view.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import pl.mftau.mftau.model.local_db.Member
import pl.mftau.mftau.view.fragments.PresenceMemberFragment

class PresencePagerAdapter(
    fragment: Fragment, private val names: Array<String>, private val member: Member
) : FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int): Fragment =
        PresenceMemberFragment.newInstance(member, position)

    override fun getItemCount(): Int = 3

    fun getTabTitle(position: Int) = names[position]
}