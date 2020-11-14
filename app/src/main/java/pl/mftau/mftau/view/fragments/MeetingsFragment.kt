package pl.mftau.mftau.view.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_meetings.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.view.adapters.MeetingPagerAdapter
import pl.mftau.mftau.viewmodel.MainViewModel

class MeetingsFragment : Fragment() {

    private lateinit var mViewModel: MainViewModel
    private lateinit var mAdapter: MeetingPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_meetings, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inflateToolbarMenu(view.meetingsToolbar)

        activity?.let {
            mViewModel = ViewModelProvider(it).get(MainViewModel::class.java)
        }

        mAdapter = MeetingPagerAdapter(
            childFragmentManager, resources.getStringArray(R.array.meeting_types)
        )
        view.viewPager.adapter = mAdapter
        view.viewPager.currentItem = 0
        view.viewPager.offscreenPageLimit = 3
        view.tabLayout.setupWithViewPager(view.viewPager)

        view.addMeetingBtn.setOnClickListener {
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
            requireView().meetingsLayout,
            getString(R.string.delete_meetings_successfully),
            Snackbar.LENGTH_SHORT
        ).show()
    }
}
