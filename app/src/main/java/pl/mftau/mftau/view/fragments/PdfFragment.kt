package pl.mftau.mftau.view.fragments

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.FragmentPdfBinding
import pl.mftau.mftau.utils.PreferencesManager

class PdfFragment : BindingFragment<FragmentPdfBinding>() {

    private lateinit var mSearchView: SearchView

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentPdfBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        inflateToolbarMenu(binding.pdfToolbar)

        if (PdfFragmentArgs.fromBundle(requireArguments()).pdf == "song_book" && PreferencesManager.getAwakeSongBook())
            requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        binding.pdfToolbarTitle.text =
            if (PdfFragmentArgs.fromBundle(requireArguments()).pdf == "song_book") ""
            else getString(R.string.statute)

        loadPdfFile(savedInstanceState, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("page", binding.pdfView.currentPage)
    }

    override fun onStop() {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        super.onStop()
    }

    private fun loadPdfFile(savedInstanceState: Bundle?, isBtnPressed: Boolean) {
        when (PdfFragmentArgs.fromBundle(requireArguments()).pdf) {
            "song_book" -> {
                binding.pdfView.fromAsset("spiewnik.pdf")
                    .defaultPage(if (isBtnPressed) 4 else savedInstanceState?.getInt("page") ?: 4)
                    .nightMode(PreferencesManager.getNightMode())
                    .load()
            }
            "statute" -> {
                binding.pdfView.fromAsset("statut.pdf")
                    .nightMode(PreferencesManager.getNightMode())
                    .load()
            }
        }
    }

    private fun inflateToolbarMenu(toolbar: Toolbar) {
        toolbar.apply {
            setNavigationOnClickListener { findNavController().navigateUp() }
            inflateMenu(R.menu.menu_pdf)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_scroll_up -> {
                        loadPdfFile(null, true)
                        true
                    }
                    R.id.action_show_as_list -> {
                        if (PreferencesManager.getOpenSongBookAsPdf())
                            findNavController().navigate(PdfFragmentDirections.showSongBookFragment())
                        else
                            findNavController().navigateUp()
                        true
                    }
                    else -> false
                }
            }
        }
        toolbar.menu.findItem(R.id.action_show_as_list)?.isVisible =
            PdfFragmentArgs.fromBundle(requireArguments()).pdf == "song_book"
        toolbar.menu.findItem(R.id.action_search)?.isVisible =
            PdfFragmentArgs.fromBundle(requireArguments()).pdf == "song_book"
        val searchManager =
            requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        mSearchView = toolbar.menu.findItem(R.id.action_search).actionView as SearchView
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        mSearchView.maxWidth = Integer.MAX_VALUE

        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                findNavController().navigate(
                    PdfFragmentDirections.showSongBookSearchFragment(query ?: "")
                )
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })
    }

    fun onBackPressed() {
        if (::mSearchView.isInitialized && !mSearchView.isIconified) mSearchView.onActionViewCollapsed()
        else findNavController().navigateUp()
    }
}
