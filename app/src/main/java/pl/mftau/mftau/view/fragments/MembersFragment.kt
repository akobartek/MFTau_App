package pl.mftau.mftau.view.fragments


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_members.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.view.adapters.MembersRecyclerAdapter
import pl.mftau.mftau.viewmodel.MainViewModel

class MembersFragment : Fragment() {

    private lateinit var mViewModel: MainViewModel
    private lateinit var mAdapter: MembersRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_members, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            mViewModel = ViewModelProviders.of(it).get(MainViewModel::class.java)
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

        mViewModel.getAllMembers().observe(this, Observer { members ->
            view.membersRecyclerView.layoutAnimation =
                    AnimationUtils.loadLayoutAnimation(view.membersRecyclerView.context, R.anim.layout_animation_fall_down)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_members, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_emauses -> {
                findNavController().navigate(MembersFragmentDirections.showEmausFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
