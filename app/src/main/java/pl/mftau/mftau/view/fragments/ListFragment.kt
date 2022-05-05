package pl.mftau.mftau.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.FragmentListBinding
import pl.mftau.mftau.utils.PrayerUtils

class ListFragment : BindingFragment<FragmentListBinding>() {

    private lateinit var listType: String

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentListBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        binding.listToolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        context?.let {
            if (ListFragmentArgs.fromBundle(requireArguments()).listType == "prayer") {
                binding.listToolbarTitle.text = getString(R.string.prayer)
                listType = "prayer"
                binding.list.adapter =
                    ArrayAdapter(it, R.layout.item_listview, PrayerUtils.prayerNames)
            } else {
                binding.listToolbarTitle.text = getString(R.string.breviary)
                listType = "breviary"
                binding.list.adapter =
                    ArrayAdapter(
                        it, R.layout.item_listview, resources.getStringArray(R.array.breviary_list)
                    )
            }
        }

        binding.list.setOnItemClickListener { _, _, position, _ ->
            if (listType == "prayer")
                findNavController().navigate(ListFragmentDirections.showPrayerFragment(position))
            else
                findNavController().navigate(ListFragmentDirections.showBreviaryFragment(position))
        }
    }
}
