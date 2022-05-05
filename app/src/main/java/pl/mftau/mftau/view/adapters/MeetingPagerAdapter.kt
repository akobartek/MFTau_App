package pl.mftau.mftau.view.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import pl.mftau.mftau.view.fragments.MeetingsListFragment

class MeetingPagerAdapter(fragment: Fragment, private val names: Array<String>) : FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int): Fragment =
        MeetingsListFragment.newInstance(position)

    override fun getItemCount(): Int = 3

    fun getTabTitle(position: Int) = names[position]
}