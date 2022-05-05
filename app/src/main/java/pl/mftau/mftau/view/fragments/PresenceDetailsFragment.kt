package pl.mftau.mftau.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.FragmentPresenceDetailsBinding
import pl.mftau.mftau.model.Member
import pl.mftau.mftau.view.adapters.PresencePagerAdapter

class PresenceDetailsFragment : BindingFragment<FragmentPresenceDetailsBinding>() {

    private lateinit var mMember: Member
    private lateinit var mAdapter: PresencePagerAdapter

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentPresenceDetailsBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        binding.presenceDetailsToolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        arguments?.let { bundle ->
            mMember = PresenceDetailsFragmentArgs.fromBundle(bundle).member
            binding.presenceDetailsToolbarTitle.text =
                getString(R.string.presence_list_title, mMember.name)
        }

        mAdapter = PresencePagerAdapter(
            this@PresenceDetailsFragment, resources.getStringArray(R.array.meeting_types), mMember
        )
        binding.viewPager.apply {
            adapter = mAdapter
            currentItem = 0
            offscreenPageLimit = 3
        }
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = mAdapter.getTabTitle(position)
        }
    }
}
