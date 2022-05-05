package pl.mftau.mftau.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.FragmentMembersBinding
import pl.mftau.mftau.view.adapters.MembersRecyclerAdapter
import pl.mftau.mftau.viewmodel.MainViewModel

class MembersFragment : BindingFragment<FragmentMembersBinding>() {

    private lateinit var mViewModel: MainViewModel
    private lateinit var mAdapter: MembersRecyclerAdapter

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMembersBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        inflateToolbarMenu(binding.membersToolbar)

        activity?.let {
            mViewModel = ViewModelProvider(it)[MainViewModel::class.java]
        }
        mAdapter = MembersRecyclerAdapter()

        binding.contentMembers.membersRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
            adapter = mAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy < 0 && !binding.addMemberBtn.isShown)
                        binding.addMemberBtn.show()
                    else if (dy > 0 && binding.addMemberBtn.isShown)
                        binding.addMemberBtn.hide()
                }
            })
        }

        mViewModel.getAllMembers().observe(viewLifecycleOwner) { members ->
            binding.contentMembers.membersRecyclerView.layoutAnimation =
                AnimationUtils.loadLayoutAnimation(
                    binding.contentMembers.membersRecyclerView.context, R.anim.layout_animation_fall_down
                )
            mAdapter.setMemberList(members)
            binding.contentMembers.membersRecyclerView.scheduleLayoutAnimation()

            binding.contentMembers.loadingIndicator.hide()
            binding.contentMembers.emptyView.visibility =
                if (members.isEmpty()) View.VISIBLE else View.INVISIBLE
        }

        binding.addMemberBtn.setOnClickListener {
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
