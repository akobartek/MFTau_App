package pl.mftau.mftau.view.fragments

import android.animation.Animator
import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.FragmentEmausBinding
import pl.mftau.mftau.db.entities.MemberEntity
import pl.mftau.mftau.view.adapters.EmausRecyclerAdapter
import pl.mftau.mftau.viewmodel.EmausViewModel
import kotlin.math.hypot

class EmausFragment : BindingFragment<FragmentEmausBinding>() {

    private lateinit var mViewModel: EmausViewModel
    private lateinit var mAdapter: EmausRecyclerAdapter

    var members: List<MemberEntity>? = null
    private var draws: List<String>? = null

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentEmausBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        inflateToolbarMenu(binding.emausToolbar)

        activity?.let {
            mViewModel = ViewModelProvider(it)[EmausViewModel::class.java]
        }
        mAdapter = EmausRecyclerAdapter()
        setupRecyclerView()

        lifecycleScope.launch {
            mViewModel.getAllMembersFromDatabase().collect { databaseList ->
                if (databaseList.isEmpty()) {
                    loadMembersFromFirebase()
                } else {
                    members = databaseList
                    trySetAdapter()
                }
            }
        }
        lifecycleScope.launch {
            mViewModel.getLastDrawsFromDatabase().collect { allDraws ->
                if (allDraws.isEmpty()) {
                    draws = null
                    binding.contentEmaus.drawsEmptyView.visibility = View.VISIBLE
                    binding.contentEmaus.drawsRecyclerView.visibility = View.INVISIBLE
                    binding.contentEmaus.oddPerson.visibility = View.INVISIBLE
                } else {
                    draws = allDraws.toString().replace("[", "").replace("]", "").split(",")
                    trySetAdapter()
                }
                setupToolbarMenuIcons(binding.emausToolbar.menu)
            }
        }

        setOnClickListeners()
    }

    private fun inflateToolbarMenu(toolbar: Toolbar) {
        toolbar.apply {
            setNavigationOnClickListener { findNavController().navigateUp() }
            inflateMenu(R.menu.menu_emaus)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_copy_draws -> {
                        mViewModel.copyDrawsToClipboard(members, draws)
                        Snackbar.make(
                            binding.contentEmaus.emausLayout, R.string.copied_to_clipboard, Snackbar.LENGTH_LONG
                        ).show()
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
                    else -> false
                }
            }
        }
    }

    private fun setupToolbarMenuIcons(menu: Menu) {
        val areDrawsEmpty = draws.isNullOrEmpty()
        menu.findItem(R.id.action_delete_last_draw)?.isVisible = !areDrawsEmpty
        menu.findItem(R.id.action_reset_draws)?.isVisible = !areDrawsEmpty
        menu.findItem(R.id.action_copy_draws)?.isVisible = !areDrawsEmpty
    }

    private fun setupRecyclerView() {
        binding.contentEmaus.drawsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.contentEmaus.drawsRecyclerView.itemAnimator = DefaultItemAnimator()
        binding.contentEmaus.drawsRecyclerView.adapter = mAdapter
        binding.contentEmaus.drawsRecyclerView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy < 0 && !binding.startDrawBtn.isShown)
                    binding.startDrawBtn.show()
                else if (dy > 0 && binding.startDrawBtn.isShown)
                    binding.startDrawBtn.hide()
            }
        })
    }

    private fun setOnClickListeners() {
        binding.startDrawBtn.setOnClickListener {
            if (members != null && (mViewModel.getMaxNumberOfDraw() >= (members!!.size - 1)))
                showFullListDialog()
            else
                createCircularReveal(binding.circularRevealView)
        }
    }

    private fun createCircularReveal(circularRevealView: View) {
        // to get the center of FAB
        val centerX = binding.startDrawBtn.x.toInt() + binding.startDrawBtn.width / 2
        val centerY = binding.startDrawBtn.y.toInt()
        val finalRadius = hypot(
            circularRevealView.width.toDouble(), circularRevealView.height.toDouble()
        ).toFloat()
        // starts the effect at centerX, center Y and covers final radius
        val revealAnimator = ViewAnimationUtils.createCircularReveal(
            circularRevealView,
            centerX, centerY, 0f, finalRadius
        )
        revealAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator) {}
            override fun onAnimationEnd(p0: Animator) {
                if (!mViewModel.startDraw(members))
                    showFullListDialog()
            }

            override fun onAnimationCancel(p0: Animator) {}
            override fun onAnimationStart(p0: Animator) {
                binding.startDrawBtn.hide()
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
                binding.startDrawBtn.show()
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
                drawsWithMembers.add(Pair(members!!.singleOrNull {
                    it.id == draw.substring(0, draw.indexOf("+"))
                },
                    members!!.singleOrNull {
                        it.id == draw.substring(draw.indexOf("+") + 1, draw.length)
                    }
                ))
            }
            mAdapter.setDraws(drawsWithMembers)
            binding.contentEmaus.drawsEmptyView.visibility = View.INVISIBLE
            binding.contentEmaus.drawsRecyclerView.visibility = View.VISIBLE

            hideCircularReveal(binding.circularRevealView)
            binding.contentEmaus.drawsRecyclerView.layoutAnimation =
                AnimationUtils.loadLayoutAnimation(
                    binding.contentEmaus.drawsRecyclerView.context,
                    R.anim.layout_animation_fall_down
                )
            binding.contentEmaus.drawsRecyclerView.scheduleLayoutAnimation()

            val oddPersonId = mViewModel.getOddPersonId()
            if (oddPersonId != null && oddPersonId != "") {
                val oddPersonName = mViewModel.getMemberNameByIdFromDatabase(oddPersonId)
                val genderText =
                    if (oddPersonName.substring(0, oddPersonName.indexOf(" ")).last() == 'a')
                        getString(R.string.not_drawn_female)
                    else
                        getString(R.string.not_drawn_male)
                val text = "$oddPersonName $genderText"
                binding.contentEmaus.oddPerson.text = text
                binding.contentEmaus.oddPerson.visibility = View.VISIBLE
            } else {
                binding.contentEmaus.oddPerson.visibility = View.GONE
            }
        }
    }

    private fun loadMembersFromFirebase() {
        mViewModel.getAllMembersFromFirebase().observe(viewLifecycleOwner) { firebaseList ->
            if (firebaseList.isEmpty()) showNoPeopleDialog()
            else {
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
        }
    }

    private fun showFullListDialog() {
        hideCircularReveal(binding.circularRevealView)

        MaterialAlertDialogBuilder(requireContext())
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
        MaterialAlertDialogBuilder(requireContext())
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
        MaterialAlertDialogBuilder(requireContext())
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
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(getString(R.string.no_people_msg))
            .setCancelable(false)
            .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                dialog?.dismiss()
                findNavController().navigateUp()
            }
            .create()
            .show()
}
