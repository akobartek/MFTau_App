package pl.mftau.mftau.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.FragmentPresenceListBinding
import pl.mftau.mftau.model.local_db.Member
import pl.mftau.mftau.view.adapters.PresenceListRecyclerAdapter
import pl.mftau.mftau.viewmodel.MainViewModel
import java.util.*

class PresenceListFragment : BindingFragment<FragmentPresenceListBinding>() {

    companion object {
        var numberOfMeetings = arrayOfNulls<Int>(3)
    }

    private lateinit var mViewModel: MainViewModel
    private lateinit var mAdapter: PresenceListRecyclerAdapter
    private lateinit var mLoadingDialog: AlertDialog

    private var mPresence = HashMap<String, Array<Int>>()
    private var mMembers: List<Member>? = null

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentPresenceListBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        binding.presenceListToolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        activity?.let {
            mViewModel = ViewModelProvider(it)[MainViewModel::class.java]
        }
        mAdapter = PresenceListRecyclerAdapter()

        mLoadingDialog = MaterialAlertDialogBuilder(requireContext())
            .setView(R.layout.dialog_loading)
            .setCancelable(false)
            .create()
        mLoadingDialog.show()

        binding.contentPresenceList.chartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.contentPresenceList.chartRecyclerView.itemAnimator = DefaultItemAnimator()

        mViewModel.getAllMembers().observe(viewLifecycleOwner) { members ->
            mMembers = members
            members.forEach { mPresence[it.id] = arrayOf(0, 0, 0) }

            if (members.isNotEmpty()) {
                mViewModel.getPresence(mPresence).observe(viewLifecycleOwner) {
                    if (!numberOfMeetings.contains(null)) {
                        mPresence = it
                        setDataToChart()
                    }
                }
            } else {
                binding.contentPresenceList.emptyView.visibility = View.VISIBLE
                binding.contentPresenceList.chartRecyclerView.visibility = View.INVISIBLE
                mLoadingDialog.dismiss()
            }
        }
    }

    private fun setDataToChart() {
        if (mMembers.isNullOrEmpty() || mPresence.isEmpty())
            return

        mAdapter.setLists(mMembers!!, mPresence, numberOfMeetings)
        binding.contentPresenceList.chartRecyclerView.adapter = mAdapter
        mLoadingDialog.dismiss()
    }
}
