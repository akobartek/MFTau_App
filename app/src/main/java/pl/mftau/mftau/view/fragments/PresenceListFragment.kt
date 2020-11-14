package pl.mftau.mftau.view.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.content_presence_list.view.*
import kotlinx.android.synthetic.main.fragment_presence_list.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.model.Member
import pl.mftau.mftau.view.adapters.PresenceListRecyclerAdapter
import pl.mftau.mftau.viewmodel.MainViewModel
import java.util.*

class PresenceListFragment : Fragment() {

    companion object {
        var numberOfMeetings = arrayOfNulls<Int>(3)
    }

    private lateinit var mViewModel: MainViewModel
    private lateinit var mAdapter: PresenceListRecyclerAdapter
    private lateinit var mLoadingDialog: AlertDialog

    private var mPresence = HashMap<String, Array<Int>>()
    private var mMembers: List<Member>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_presence_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.presenceListToolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        activity?.let {
            mViewModel = ViewModelProvider(it).get(MainViewModel::class.java)
        }
        mAdapter = PresenceListRecyclerAdapter()

        mLoadingDialog = AlertDialog.Builder(view.context)
            .setView(R.layout.dialog_loading)
            .setCancelable(false)
            .create()
        mLoadingDialog.show()

        view.chartRecyclerView.layoutManager = LinearLayoutManager(view.context)
        view.chartRecyclerView.itemAnimator = DefaultItemAnimator()

        mViewModel.getAllMembers().observe(viewLifecycleOwner, { members ->
            mMembers = members
            members.forEach { mPresence[it.id] = arrayOf(0, 0, 0) }

            if (members.isNotEmpty()) {
                mViewModel.getPresence(mPresence).observe(viewLifecycleOwner, {
                    if (!numberOfMeetings.contains(null)) {
                        mPresence = it
                        setDataToChart()
                    }
                })
            } else {
                view.emptyView.visibility = View.VISIBLE
                view.chartRecyclerView.visibility = View.INVISIBLE
                mLoadingDialog.dismiss()
            }
        })
    }

    private fun setDataToChart() {
        if (mMembers.isNullOrEmpty() || mPresence.isNullOrEmpty())
            return

        mAdapter.setLists(mMembers!!, mPresence, numberOfMeetings)
        view?.chartRecyclerView?.adapter = mAdapter
        mLoadingDialog.dismiss()
    }
}
