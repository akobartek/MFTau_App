package pl.mftau.mftau.view.fragments


import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_meetings.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.view.adapters.MeetingPagerAdapter
import pl.mftau.mftau.viewmodel.MainViewModel

class MeetingsFragment : Fragment() {

    private lateinit var mViewModel: MainViewModel
    private lateinit var mAdapter: MeetingPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_meetings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            mViewModel = ViewModelProviders.of(it).get(MainViewModel::class.java)
        }

        mAdapter = MeetingPagerAdapter(childFragmentManager, resources.getStringArray(R.array.meeting_types))
        view.viewPager.adapter = mAdapter
        view.viewPager.currentItem = 0
        view.viewPager.offscreenPageLimit = 3
        view.tabLayout.setupWithViewPager(view.viewPager)

        view.addMeetingBtn.setOnClickListener {
            findNavController().navigate(MeetingsFragmentDirections.showMeetingEditorFragment(null))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_meetings, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_show_presence -> {
                findNavController().navigate(MeetingsFragmentDirections.showPresenceListFragment())
                true
            }
            R.id.action_clear_meetings -> {
                showDeleteConfirmationDialog()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun showDeleteConfirmationDialog() =
            AlertDialog.Builder(context!!)
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
        Snackbar.make(view!!.meetingsLayout, getString(R.string.delete_meetings_successfully), Snackbar.LENGTH_SHORT).show()
    }
}
