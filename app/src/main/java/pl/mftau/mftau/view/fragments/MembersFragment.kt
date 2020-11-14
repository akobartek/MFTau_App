package pl.mftau.mftau.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.content_members.view.*
import kotlinx.android.synthetic.main.fragment_members.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.view.adapters.MembersRecyclerAdapter
import pl.mftau.mftau.viewmodel.MainViewModel

class MembersFragment : Fragment() {

    private lateinit var mViewModel: MainViewModel
    private lateinit var mAdapter: MembersRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_members, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inflateToolbarMenu(view.membersToolbar)

        activity?.let {
            mViewModel = ViewModelProvider(it).get(MainViewModel::class.java)
        }
        mAdapter = MembersRecyclerAdapter()

        view.membersRecyclerView.layoutManager = LinearLayoutManager(view.context)
        view.membersRecyclerView.itemAnimator = DefaultItemAnimator()
        view.membersRecyclerView.adapter = mAdapter
        view.membersRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy < 0 && !view.addMemberBtn.isShown)
                    view.addMemberBtn.show()
                else if (dy > 0 && view.addMemberBtn.isShown)
                    view.addMemberBtn.hide()
            }
        })

        mViewModel.getAllMembers().observe(viewLifecycleOwner, { members ->
            view.membersRecyclerView.layoutAnimation =
                AnimationUtils.loadLayoutAnimation(
                    view.membersRecyclerView.context, R.anim.layout_animation_fall_down
                )
            mAdapter.setMemberList(members)
            view.membersRecyclerView.scheduleLayoutAnimation()

            view.loadingIndicator.hide()
            if (members.isEmpty()) {
                view.emptyView.visibility = View.VISIBLE
            } else {
                view.emptyView.visibility = View.INVISIBLE
            }
        })

        view.addMemberBtn.setOnClickListener {
            findNavController().navigate(MembersFragmentDirections.showMemberEditorFragment(null))
        }
    }

    private fun inflateToolbarMenu(toolbar: Toolbar) {
        toolbar.apply {
            setNavigationOnClickListener { findNavController().navigateUp() }
            inflateMenu(R.menu.menu_members)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_emauses -> {
                        findNavController().navigate(MembersFragmentDirections.showEmausFragment())
                        true
                    }
                    else -> false
                }
            }
        }
    }
}
