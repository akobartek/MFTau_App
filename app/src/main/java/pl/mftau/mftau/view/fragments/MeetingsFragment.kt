package pl.mftau.mftau.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.FragmentMeetingsBinding
import pl.mftau.mftau.view.adapters.MeetingPagerAdapter
import pl.mftau.mftau.viewmodel.MainViewModel

class MeetingsFragment : BindingFragment<FragmentMeetingsBinding>() {

    private lateinit var mViewModel: MainViewModel
    private lateinit var mAdapter: MeetingPagerAdapter

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMeetingsBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        inflateToolbarMenu(binding.meetingsToolbar)

        activity?.let {
            mViewModel = ViewModelProvider(it)[MainViewModel::class.java]
        }

        mAdapter = MeetingPagerAdapter(
            this@MeetingsFragment, resources.getStringArray(R.array.meeting_types)
        )
        binding.viewPager.apply {
            adapter = mAdapter
            currentItem = 0
            offscreenPageLimit = 3
        }
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = mAdapter.getTabTitle(position)
        }

        binding.addMeetingBtn.setOnClickListener {
            findNavController().navigate(MeetingsFragmentDirections.showMeetingEditorFragment(null))
        }
    }

    private fun inflateToolbarMenu(toolbar: Toolbar) {
        toolbar.apply {
            setNavigationOnClickListener { findNavController().navigateUp() }
            inflateMenu(R.menu.menu_meetings)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_show_presence -> {
                        findNavController().navigate(MeetingsFragmentDirections.showPresenceListFragment())
                        true
                    }
                    R.id.action_clear_meetings -> {
                        showDeleteConfirmationDialog()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun showDeleteConfirmationDialog() =
        AlertDialog.Builder(requireContext())
            .setMessage(R.string.delete_meetings_dialog_msg)
            .setPositiveButton(R.string.delete) { dialog, _ ->
                dialog.dismiss()
                clearMeetings()
            }
            .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
            .create()
            .show()

    private fun clearMeetings() {
        mViewModel.clearMeetings()
        Snackbar.make(
            binding.meetingsLayout,
            getString(R.string.delete_meetings_successfully),
            Snackbar.LENGTH_SHORT
        ).show()
    }
}
