package pl.mftau.mftau.view.fragments

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.transition.MaterialElevationScale
import kotlinx.coroutines.launch
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.DialogSongBookFilterBinding
import pl.mftau.mftau.databinding.FragmentSongBookBinding
import pl.mftau.mftau.utils.*
import pl.mftau.mftau.view.adapters.SongBookRecyclerAdapter
import pl.mftau.mftau.view.ui.SongBookBottomAppBar
import pl.mftau.mftau.viewmodel.SongBookViewModel

class SongBookFragment : BindingFragment<FragmentSongBookBinding>() {

    private lateinit var mRecyclerAdapter: SongBookRecyclerAdapter
    private val mViewModel: SongBookViewModel by activityViewModels()
    private val mLoadingDialog: AlertDialog by lazy {
        MaterialAlertDialogBuilder(requireContext())
            .setView(R.layout.dialog_loading)
            .create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = MaterialElevationScale(false)
        reenterTransition = MaterialElevationScale(true)
    }

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSongBookBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        binding.songBookBottomAppBar.setContent {
            SongBookBottomAppBar(
                { findNavController().navigateUp() },
                {
                    if (PreferencesManager.getOpenSongBookAsPdf())
                        findNavController().navigateUp()
                    else
                        findNavController().navigate(SongBookFragmentDirections.showPdfFragment("song_book"))
                },
                {
                    binding.songBookBottomAppBar.transitionName = "shared_element_user"
                    findNavController().navigate(
                        SongBookFragmentDirections.showSongBookUserFragment(),
                        FragmentNavigatorExtras(binding.songBookBottomAppBar to "shared_element_user")
                    )
                },
                {
                    binding.songBookBottomAppBar.transitionName = "shared_element_playlist"
                    findNavController().navigate(
                        SongBookFragmentDirections.showSongBookPlaylistFragment(),
                        FragmentNavigatorExtras(binding.songBookBottomAppBar to "shared_element_playlist")
                    )
                },
                {
                    binding.songsRecyclerView.layoutAnimation =
                        AnimationUtils.loadLayoutAnimation(
                            binding.songsRecyclerView.context,
                            R.anim.layout_animation_fall_down
                        )
                    PreferencesManager.setSongBookShowCords(it)
                    mRecyclerAdapter.updateShowChords(it)
                },
                {
                    showChangeTextSizeDialog(mRecyclerAdapter.getCurrentTextSize()) {
                        mRecyclerAdapter.updateTextSize(it)
                    }
                },
                {
                    binding.songBookBottomAppBar.transitionName = "shared_element_editor"
                    findNavController().navigate(
                        SongBookFragmentDirections.showSongEditorFragment(null),
                        FragmentNavigatorExtras(binding.songBookBottomAppBar to "shared_element_editor")
                    )
                }
            )
        }

        mLoadingDialog.show()

        binding.songsRecyclerView.apply {
            mRecyclerAdapter = SongBookRecyclerAdapter(mViewModel) {
                smoothScrollBy(0, it, null, 1111)
            }
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
            adapter = mRecyclerAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy < 0 && binding.songBookToolbar.visibility == View.GONE)
                        binding.songBookToolbar.expand(222)
                    else if (dy > 77 && binding.songBookToolbar.visibility == View.VISIBLE)
                        binding.songBookToolbar.collapse(222)
                    super.onScrolled(recyclerView, dx, dy)
                }
            })
            layoutAnimation =
                AnimationUtils.loadLayoutAnimation(this.context, R.anim.layout_animation_fall_down)
        }

        binding.filterIcon.setOnClickListener { openFilterDialog() }

        val searchManager =
            requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        binding.searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                findNavController().navigate(
                    SongBookFragmentDirections.showSongBookSearchFragment(query ?: "")
                )
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })

        lifecycleScope.launch {
            mViewModel.combinedFlow.collect {
                mViewModel.fetchSongs()
            }
        }
        mViewModel.visibleSongs.observe(viewLifecycleOwner) { songs ->
            mRecyclerAdapter.updateList(songs)
            binding.songsRecyclerView.scheduleLayoutAnimation()
            mLoadingDialog.hide()
        }
    }

    override fun onResume() {
        super.onResume()
        binding.searchView.apply {
            setQuery("", false)
            isIconified = true
        }
    }

    private fun openFilterDialog() {
        val dialogBinding = DialogSongBookFilterBinding.inflate(layoutInflater)
        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setView(dialogBinding.root)
            .setPositiveButton(R.string.save) { dialog, _ ->
                val selectedChip =
                    dialogBinding.topicsChipGroup.findViewById<Chip>(dialogBinding.topicsChipGroup.checkedChipId)
                mViewModel.fetchSongs(Integer.valueOf(selectedChip.tag.toString()))
                dialog.dismiss()
            }
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
        dialog.setOnShowListener {
            dialogBinding.topicsChipGroup
                .findViewWithTag<Chip>(mViewModel.getFilter().value.toString())
                ?.isChecked = true
        }
        dialog.show()
    }
}