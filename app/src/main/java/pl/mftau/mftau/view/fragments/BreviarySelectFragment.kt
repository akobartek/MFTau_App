package pl.mftau.mftau.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.FragmentBreviarySelectBinding
import java.util.*

class BreviarySelectFragment : BindingFragment<FragmentBreviarySelectBinding>() {

    private var mDaysFromToday = 0

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentBreviarySelectBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        inflateToolbarMenu()
        with(binding) {
            breviarySelectList.adapter = ArrayAdapter(
                requireContext(),
                R.layout.item_listview,
                resources.getStringArray(R.array.breviary_list)
            )
            breviarySelectList.setOnItemClickListener { _, _, position, _ ->
                findNavController().navigate(
                    BreviarySelectFragmentDirections.showBreviaryFragment(position, mDaysFromToday)
                )
            }
        }
    }

    private fun inflateToolbarMenu() {
        setCorrectToolbarTitle()
        binding.breviarySelectToolbar.apply {
            setNavigationOnClickListener { findNavController().navigateUp() }
            setMenuItemsVisibility(menu)
            setOnMenuItemClickListener {
                mDaysFromToday = when (it.itemId) {
                    R.id.action_yesterday_breviary -> -1
                    R.id.action_tomorrow_breviary -> 1
                    else -> 0
                }
                setCorrectToolbarTitle()
                setMenuItemsVisibility(menu)
                true
            }
        }
    }

    private fun setMenuItemsVisibility(menu: Menu) {
        menu.findItem(R.id.action_yesterday_breviary).isVisible = mDaysFromToday != -1
        menu.findItem(R.id.action_today_breviary).isVisible = mDaysFromToday != 0
        menu.findItem(R.id.action_tomorrow_breviary).isVisible = mDaysFromToday != 1
    }

    private fun setCorrectToolbarTitle() {
        val calendar = Calendar.getInstance()
        val dayInt = calendar.get(Calendar.DAY_OF_MONTH) + mDaysFromToday
        val day = if (dayInt < 10) "0$dayInt" else dayInt.toString()
        val monthInt = calendar.get(Calendar.MONTH) + 1
        val month = if (monthInt < 10) "0$monthInt" else monthInt.toString()
        binding.breviarySelectToolbarTitle.text =
            requireContext().getString(R.string.breviary_with_day, "$day.$month")
    }
}