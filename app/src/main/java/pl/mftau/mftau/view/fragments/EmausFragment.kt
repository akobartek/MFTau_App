package pl.mftau.mftau.view.fragments

import android.animation.Animator
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_emaus.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.db.entities.MemberEntity
import pl.mftau.mftau.view.adapters.EmausRecyclerAdapter
import pl.mftau.mftau.viewmodel.MainViewModel
import kotlin.math.hypot

class EmausFragment : Fragment() {

    private lateinit var mViewModel: MainViewModel
    private lateinit var mAdapter: EmausRecyclerAdapter

    var members: List<MemberEntity>? = null
    private var draws: List<String>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_emaus, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            mViewModel = ViewModelProviders.of(it).get(MainViewModel::class.java)
        }
        mAdapter = EmausRecyclerAdapter()
        setupRecyclerView()

        mViewModel.getAllMembersFromDatabase().observe(this, Observer { databaseList ->
            if (databaseList.isEmpty()) {
                loadMembersFromFirebase()
            } else {
                members = databaseList
                trySetAdapter()
            }
        })
        mViewModel.getLastDrawsFromDatabase().observe(this, Observer { allDraws ->
            if (allDraws.isNullOrEmpty()) {
                draws = null
                view.drawsEmptyView.visibility = View.VISIBLE
                view.drawsRecyclerView.visibility = View.INVISIBLE
                view.oddPerson.visibility = View.INVISIBLE
            } else {
                draws = allDraws.toString().replace("[", "").replace("]", "").split(",")
                trySetAdapter()
            }
            activity?.invalidateOptionsMenu()
        })

        setOnClickListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) =
        inflater.inflate(R.menu.menu_emaus, menu)

    override fun onPrepareOptionsMenu(menu: Menu) {
        if (draws.isNullOrEmpty()) {
            menu.findItem(R.id.action_delete_last_draw)?.isVisible = false
            menu.findItem(R.id.action_reset_draws)?.isVisible = false
            menu.findItem(R.id.action_copy_draws)?.isVisible = false
        } else {
            menu.findItem(R.id.action_delete_last_draw)?.isVisible = true
            menu.findItem(R.id.action_reset_draws)?.isVisible = true
            menu.findItem(R.id.action_copy_draws)?.isVisible = true
        }
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_copy_draws -> {
            mViewModel.copyDrawsToClipboard(members, draws)
            Snackbar.make(view!!.emausLayout, R.string.copied_draws, Snackbar.LENGTH_LONG).show()
            true
        }
        R.id.action_reload_members -> {
            loadMembersFromFirebase()
            true
        }
        R.id.action_delete_last_draw -> {
            showDeleteLastDrawDialog()
            true
        }
        R.id.action_reset_draws -> {
            showResetDrawsDialog()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun setupRecyclerView() {
        view?.drawsRecyclerView?.layoutManager = LinearLayoutManager(context)
        view?.drawsRecyclerView?.itemAnimator = DefaultItemAnimator()
        view?.drawsRecyclerView?.adapter = mAdapter
        view?.drawsRecyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy < 0 && !view!!.startDrawBtn.isShown)
                    view!!.startDrawBtn?.show()
                else if (dy > 0 && view!!.startDrawBtn.isShown)
                    view!!.startDrawBtn.hide()
            }
        })
    }

    private fun setOnClickListeners() {
        view?.startDrawBtn?.setOnClickListener {
            if (members != null && (mViewModel.getMaxNumberOfDraw() >= (members!!.size - 1))) {
                showFullListDialog()
            } else {
                createCircularReveal(view!!.circularRevealView)
            }
        }
    }

    private fun createCircularReveal(circularRevealView: View) {
        // to get the center of FAB
        val centerX = view!!.startDrawBtn.x.toInt() + view!!.startDrawBtn.width / 2
        val centerY = view!!.startDrawBtn.y.toInt()
        val finalRadius = hypot(circularRevealView.width.toDouble(), circularRevealView.height.toDouble()).toFloat()
        // starts the effect at centerX, center Y and covers final radius
        val revealAnimator = ViewAnimationUtils.createCircularReveal(
            circularRevealView,
            centerX, centerY, 0f, finalRadius
        )
        revealAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {}
            override fun onAnimationEnd(p0: Animator?) {
                if (!mViewModel.startDraw(members)) {
                    showFullListDialog()
                }
            }

            override fun onAnimationCancel(p0: Animator?) {}
            override fun onAnimationStart(p0: Animator?) {
                view!!.startDrawBtn.hide()
            }
        })

        circularRevealView.alpha = 1f
        circularRevealView.visibility = View.VISIBLE
        revealAnimator.start()
    }

    private fun hideCircularReveal(circularRevealView: View?) {
        circularRevealView?.animate()
            ?.alpha(0f)
            ?.withEndAction {
                view?.startDrawBtn?.show()
                circularRevealView.visibility = View.INVISIBLE
            }
            ?.duration = 300
    }

    private fun trySetAdapter() {
        if (members == null || draws == null) {
            return
        } else {
            val drawsWithMembers = arrayListOf<Pair<MemberEntity?, MemberEntity?>>()
            draws?.forEach { draw ->
                drawsWithMembers.add(Pair(members!!.singleOrNull { it.id == draw.substring(0, draw.indexOf("+")) },
                    members!!.singleOrNull { it.id == draw.substring(draw.indexOf("+") + 1, draw.length) }
                ))
            }
            mAdapter.setDraws(drawsWithMembers)
            view?.drawsEmptyView?.visibility = View.INVISIBLE
            view?.drawsRecyclerView?.visibility = View.VISIBLE

            hideCircularReveal(view?.circularRevealView)
            view?.drawsRecyclerView?.layoutAnimation =
                AnimationUtils.loadLayoutAnimation(view?.drawsRecyclerView?.context, R.anim.layout_animation_fall_down)
            view?.drawsRecyclerView?.scheduleLayoutAnimation()

            val oddPersonId = mViewModel.getOddPersonId()
            if (oddPersonId != null && oddPersonId != "") {
                val oddPersonName = mViewModel.getMemberNameByIdFromDatabase(oddPersonId)
                val genderText =
                    if (oddPersonName.substring(0, oddPersonName.indexOf(" ")).last() == 'a')
                        getString(R.string.not_drawn_female)
                    else
                        getString(R.string.not_drawn_male)
                val text = "$oddPersonName $genderText"
                view?.oddPerson?.text = text
                view?.oddPerson?.visibility = View.VISIBLE
            } else {
                view?.oddPerson?.visibility = View.GONE
            }
        }
    }

    private fun loadMembersFromFirebase() {
        mViewModel.getAllMembersFromFirebase().observe(this, Observer { firebaseList ->
            if (firebaseList.isEmpty()) {
                showNoPeopleDialog()
            } else {
                val memberEntities = arrayListOf<MemberEntity>()
                firebaseList.forEach {
                    memberEntities.add(MemberEntity(it.id, it.name, arrayListOf()))
                }
                mViewModel.insertMembersToDatabase(memberEntities)

                // Clear database from members that was deleted from Firebase.
                if (members != null) {
                    mViewModel.deleteMembersInDatabase(
                        members!!.filter { databaseMember ->
                            firebaseList.singleOrNull { firebaseMember ->
                                databaseMember.id == firebaseMember.id
                            } == null
                        })
                }
            }
        })
    }

    private fun showFullListDialog() {
        hideCircularReveal(view?.circularRevealView)

        AlertDialog.Builder(context!!)
            .setMessage(R.string.full_draws_msg)
            .setCancelable(false)
            .setPositiveButton(R.string.yes) { dialog, _ ->
                dialog.dismiss()
                mViewModel.deleteAllDrawsInDatabase(members)
                mViewModel.startDraw(members)
            }
            .setNegativeButton(R.string.no) { dialog, _ -> dialog?.dismiss() }
            .create()
            .show()
    }

    private fun showDeleteLastDrawDialog() =
        AlertDialog.Builder(context!!)
            .setMessage(R.string.delete_last_draw_msg)
            .setCancelable(false)
            .setPositiveButton(R.string.yes) { dialog, _ ->
                dialog.dismiss()
                mViewModel.deleteLastDrawInDatabase(members, draws)
                activity?.recreate()
            }
            .setNegativeButton(R.string.no) { dialog, _ -> dialog?.dismiss() }
            .create()
            .show()

    private fun showResetDrawsDialog() =
        AlertDialog.Builder(context!!)
            .setMessage(R.string.reset_draws_msg)
            .setCancelable(false)
            .setPositiveButton(R.string.yes) { dialog, _ ->
                dialog.dismiss()
                mViewModel.deleteAllDrawsInDatabase(members)
                activity?.recreate()
            }
            .setNegativeButton(R.string.no) { dialog, _ -> dialog?.dismiss() }
            .create()
            .show()

    private fun showNoPeopleDialog() =
        AlertDialog.Builder(context!!)
            .setMessage(getString(R.string.no_people_msg))
            .setCancelable(false)
            .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                dialog?.dismiss()
                findNavController().navigateUp()
            }
            .create()
            .show()
}
