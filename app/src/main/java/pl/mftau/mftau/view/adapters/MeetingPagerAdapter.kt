package pl.mftau.mftau.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import pl.mftau.mftau.view.fragments.MeetingsListFragment

class MeetingPagerAdapter(supportFragmentManager: FragmentManager, private val names: Array<String>)
    : FragmentPagerAdapter(supportFragmentManager) {

    override fun getItem(position: Int): Fragment = MeetingsListFragment.newInstance(position)

    override fun getPageTitle(position: Int): CharSequence? = names[position]

    override fun getCount(): Int = 3
}