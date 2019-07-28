package pl.mftau.mftau.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import pl.mftau.mftau.model.Member
import pl.mftau.mftau.view.fragments.PresenceMemberFragment

class PresencePagerAdapter(
    supportFragmentManager: FragmentManager, private val names: Array<String>,
    private val member: Member
) : FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment = PresenceMemberFragment.newInstance(member, position)

    override fun getPageTitle(position: Int): CharSequence? = names[position]

    override fun getCount(): Int = 3
}