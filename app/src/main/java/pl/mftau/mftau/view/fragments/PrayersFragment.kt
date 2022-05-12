package pl.mftau.mftau.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import pl.mftau.mftau.databinding.FragmentPrayersBinding
import pl.mftau.mftau.utils.PrayerUtils

class PrayersFragment : BindingFragment<FragmentPrayersBinding>() {

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentPrayersBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        binding.prayerToolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        val position = PrayersFragmentArgs.fromBundle(requireArguments()).position
        binding.prayerToolbarTitle.text = PrayerUtils.prayerNames[position]
        binding.prayerText.text = PrayerUtils.prayerBook[position]
    }
}
