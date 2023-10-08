package pl.mftau.mftau.view.fragments

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.FragmentSongBookSearchBinding
import pl.mftau.mftau.model.local_db.Song
import pl.mftau.mftau.view.adapters.SongBookSearchAdapter
import pl.mftau.mftau.viewmodel.SongBookViewModel

class SongBookSearchFragment : BindingFragment<FragmentSongBookSearchBinding>() {

    private lateinit var mSearchView: SearchView
    private lateinit var mSongBottomSheetBehavior: BottomSheetBehavior<*>
    private lateinit var mRecyclerAdapter: SongBookSearchAdapter
    private val mViewModel: SongBookViewModel by activityViewModels()

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSongBookSearchBinding.inflate(inflater, container, false)

    @SuppressLint("SetTextI18n")
    override fun setup(savedInstanceState: Bundle?) {
        inflateToolbarMenu(binding.searchToolbar)

        mSongBottomSheetBehavior = BottomSheetBehavior.from(binding.songBottomSheet)
        mSongBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        binding.contentSongBookSearch.apply {
            mRecyclerAdapter = SongBookSearchAdapter(emptyView, ::openBottomSheet)
            songsRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                itemAnimator = DefaultItemAnimator()
                adapter = mRecyclerAdapter
            }
        }

        mViewModel.searchQuery.observe(viewLifecycleOwner) { query ->
            if (query != null) {
                mRecyclerAdapter.filter.filter(query)
                mViewModel.bottomSheetSong.postValue(null)
                binding.searchToolbarTitle.text = "'$query'"
            }
        }
        mViewModel.visibleSongs.observe(viewLifecycleOwner) { songs ->
            mRecyclerAdapter.updateList(songs, mViewModel.searchQuery.value ?: "")
        }

        mViewModel.searchQuery.postValue(SongBookSearchFragmentArgs.fromBundle(requireArguments()).query)
        binding.contentSongBookSearch.loadingIndicator.hide()
    }

    private fun inflateToolbarMenu(toolbar: Toolbar) {
        toolbar.apply {
            setNavigationOnClickListener { findNavController().navigateUp() }
            inflateMenu(R.menu.menu_search)
        }
        val searchManager =
            requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        mSearchView = toolbar.menu.findItem(R.id.action_search).actionView as SearchView
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        mSearchView.maxWidth = Integer.MAX_VALUE

        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (::mSongBottomSheetBehavior.isInitialized && mSongBottomSheetBehavior.state != BottomSheetBehavior.STATE_HIDDEN)
                    mSongBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                mViewModel.searchQuery.postValue(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })
    }

    private fun openBottomSheet(song: Song) {
        mViewModel.bottomSheetSong.postValue(song)
        mSongBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    fun onBackPressed() {
        if (::mSearchView.isInitialized && !mSearchView.isIconified) mSearchView.onActionViewCollapsed()
        else if (::mSongBottomSheetBehavior.isInitialized && mSongBottomSheetBehavior.state != BottomSheetBehavior.STATE_HIDDEN) {
            mRecyclerAdapter.filter.filter(mViewModel.searchQuery.value)
            mSongBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        } else findNavController().navigateUp()
    }
}