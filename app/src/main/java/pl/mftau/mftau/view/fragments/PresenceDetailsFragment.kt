package pl.mftau.mftau.view.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_presence_details.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.model.Member
import pl.mftau.mftau.view.adapters.PresencePagerAdapter

class PresenceDetailsFragment : Fragment() {

    private lateinit var mMember: Member
    private lateinit var mAdapter: PresencePagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_presence_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.presenceDetailsToolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        arguments?.let { bundle ->
            mMember = PresenceDetailsFragmentArgs.fromBundle(bundle).member
            view.presenceDetailsToolbarTitle.text =
                getString(R.string.presence_list_title, mMember.name)
        }

        mAdapter = PresencePagerAdapter(
            childFragmentManager, resources.getStringArray(R.array.meeting_types), mMember
        )
        view.viewPager.adapter = mAdapter
        view.viewPager.currentItem = 0
        view.viewPager.offscreenPageLimit = 3
        view.tabLayout.setupWithViewPager(view.viewPager)
    }
}
