package pl.mftau.mftau.view.fragments


import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_retreats.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.view.adapters.RetreatRecyclerAdapter
import pl.mftau.mftau.viewmodel.MainViewModel
import java.util.*

class RetreatsFragment : Fragment() {

    private lateinit var mViewModel: MainViewModel
    private lateinit var mAdapter: RetreatRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_retreats, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            mViewModel = ViewModelProviders.of(it).get(MainViewModel::class.java)
            mAdapter = RetreatRecyclerAdapter(mViewModel.currentUserType, this@RetreatsFragment)
        }

        if (mViewModel.currentUserType != MainViewModel.USER_TYPE_ADMIN) {
            view.addRetreatBtn.hide()
        }

        view.retreatRecyclerView.layoutManager = LinearLayoutManager(view.context)
        view.retreatRecyclerView.itemAnimator = DefaultItemAnimator()
        view.retreatRecyclerView.adapter = mAdapter
        view.retreatRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (mViewModel.currentUserType == MainViewModel.USER_TYPE_ADMIN) {
                    if (dy < 0 && !view.addRetreatBtn.isShown)
                        view.addRetreatBtn.show()
                    else if (dy > 0 && view.addRetreatBtn.isShown)
                        view.addRetreatBtn.hide()
                }
            }
        })

        mViewModel.getAllRetreats().observe(this@RetreatsFragment, Observer { retreats ->
            view.retreatRecyclerView.layoutAnimation =
                AnimationUtils.loadLayoutAnimation(view.retreatRecyclerView.context, R.anim.layout_animation_fall_down)
            mAdapter.setRetreatList(retreats)
            view.retreatRecyclerView.scheduleLayoutAnimation()

            view.loadingIndicator.hide()
            if (retreats.isEmpty()) {
                view.emptyView.visibility = View.VISIBLE
            } else {
                view.emptyView.visibility = View.INVISIBLE
            }

            val expiredRetreats = retreats.filter { it.endDate.toDate() < Date(Date().time - 86400000) }
            expiredRetreats.forEach { mViewModel.deleteRetreat(activity!!, it.id, false) }
        })

        view.addRetreatBtn.setOnClickListener {
            findNavController().navigate(RetreatsFragmentDirections.showEditorFragment(null))
        }
    }
}
