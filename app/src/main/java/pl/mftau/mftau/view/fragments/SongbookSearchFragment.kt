package pl.mftau.mftau.view.fragments

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.content_songbook_search.view.*
import kotlinx.android.synthetic.main.fragment_songbook_search.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.view.adapters.SongbookSearchAdapter
import pl.mftau.mftau.viewmodel.SongbookSearchViewModel

class SongbookSearchFragment : Fragment() {

    private lateinit var mSearchView: SearchView
    private lateinit var mSongBottomSheetBehavior: BottomSheetBehavior<*>
    private lateinit var mRecyclerAdapter: SongbookSearchAdapter
    private val mViewModel: SongbookSearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_songbook_search, container, false)

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inflateToolbarMenu(view.searchToolbar)

        mSongBottomSheetBehavior = BottomSheetBehavior.from(view.songBottomSheet)
        mSongBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        mRecyclerAdapter = SongbookSearchAdapter(view.emptyView, ::openSongBottomSheet)
        view.songsRecyclerView.apply {
            layoutManager = LinearLayoutManager(view.context)
            itemAnimator = DefaultItemAnimator()
            adapter = mRecyclerAdapter
        }

        mViewModel.query.observe(viewLifecycleOwner, { query ->
            if (query != null) {
                mRecyclerAdapter.filter.filter(query)
                view.searchToolbarTitle.text = "'$query'"
            }
        })

        mViewModel.query.postValue(SongbookSearchFragmentArgs.fromBundle(requireArguments()).query)
        view.loadingIndicator.hide()
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
                mViewModel.query.postValue(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })
    }

    private fun openSongBottomSheet(songIndex: Int) {
        mViewModel.song.postValue(songIndex)
        mSongBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    fun onBackPressed() {
        if (::mSearchView.isInitialized && !mSearchView.isIconified) mSearchView.onActionViewCollapsed()
        else if (::mSongBottomSheetBehavior.isInitialized && mSongBottomSheetBehavior.state != BottomSheetBehavior.STATE_HIDDEN)
            mSongBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        else findNavController().navigateUp()
    }
}