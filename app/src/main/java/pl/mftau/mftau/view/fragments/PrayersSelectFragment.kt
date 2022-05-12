package pl.mftau.mftau.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.FragmentPrayersSelectBinding
import pl.mftau.mftau.utils.PrayerUtils

class PrayersSelectFragment : BindingFragment<FragmentPrayersSelectBinding>() {

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentPrayersSelectBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        with(binding) {
            prayersSelectToolbar.setNavigationOnClickListener { findNavController().navigateUp() }
            prayersSelectList.adapter =
                ArrayAdapter(requireContext(), R.layout.item_listview, PrayerUtils.prayerNames)
            prayersSelectList.setOnItemClickListener { _, _, position, _ ->
                findNavController().navigate(
                    PrayersSelectFragmentDirections.showPrayersSelectFragment(position)
                )
            }
        }
    }
}
