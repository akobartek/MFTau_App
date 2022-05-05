package pl.mftau.mftau.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.FragmentRetreatsBinding
import pl.mftau.mftau.view.adapters.RetreatRecyclerAdapter
import pl.mftau.mftau.viewmodel.MainViewModel
import java.util.*

class RetreatsFragment : BindingFragment<FragmentRetreatsBinding>() {

    private lateinit var mViewModel: MainViewModel
    private lateinit var mAdapter: RetreatRecyclerAdapter

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentRetreatsBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        binding.retreatsToolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        activity?.let {
            mViewModel = ViewModelProvider(it)[MainViewModel::class.java]
            mAdapter = RetreatRecyclerAdapter(mViewModel.currentUserType, this@RetreatsFragment)
        }

        if (mViewModel.currentUserType != MainViewModel.USER_TYPE_ADMIN) {
            binding.addRetreatBtn.hide()
        }

        binding.contentRetreats.retreatRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
            adapter = mAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (mViewModel.currentUserType == MainViewModel.USER_TYPE_ADMIN) {
                        if (dy < 0 && !binding.addRetreatBtn.isShown)
                            binding.addRetreatBtn.show()
                        else if (dy > 0 && binding.addRetreatBtn.isShown)
                            binding.addRetreatBtn.hide()
                    }
                }
            })
        }

        mViewModel.getAllRetreats().observe(viewLifecycleOwner) { retreats ->
            binding.contentRetreats.retreatRecyclerView.layoutAnimation =
                AnimationUtils.loadLayoutAnimation(
                    binding.contentRetreats.retreatRecyclerView.context,
                    R.anim.layout_animation_fall_down
                )
            mAdapter.setRetreatList(retreats)
            binding.contentRetreats.retreatRecyclerView.scheduleLayoutAnimation()

            binding.contentRetreats.loadingIndicator.hide()
            binding.contentRetreats.emptyView.visibility =
                if (retreats.isEmpty()) View.VISIBLE else View.INVISIBLE

            val expiredRetreats =
                retreats.filter { it.endDate.toDate() < Date(Date().time - 86400000) }
            expiredRetreats.forEach { mViewModel.deleteRetreat(requireActivity(), it.id, false) }
        }

        binding.addRetreatBtn.setOnClickListener {
            findNavController().navigate(RetreatsFragmentDirections.showEditorFragment(null))
        }
    }
}
