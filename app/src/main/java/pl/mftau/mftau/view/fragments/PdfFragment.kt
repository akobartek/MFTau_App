package pl.mftau.mftau.view.fragments


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_pdf.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.utils.PreferencesManager

class PdfFragment : Fragment() {

    private lateinit var mSearchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_pdf, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inflateToolbarMenu(view.pdfToolbar)

        if (PdfFragmentArgs.fromBundle(requireArguments()).pdf == "songbook" && PreferencesManager.getAwakeSongbook())
            activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        view.pdfToolbarTitle.text = getString(
            if (PdfFragmentArgs.fromBundle(requireArguments()).pdf == "songbook") R.string.songbook
            else R.string.statute
        )

        loadPdfFile(savedInstanceState, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("page", view?.pdfView?.currentPage ?: 2)
    }

    private fun loadPdfFile(savedInstanceState: Bundle?, isBtnPressed: Boolean) {
        when (PdfFragmentArgs.fromBundle(requireArguments()).pdf) {
            "songbook" -> {
                requireView().pdfView.fromAsset("spiewnik.pdf")
                    .defaultPage(if (isBtnPressed) 4 else savedInstanceState?.getInt("page") ?: 4)
                    .nightMode(PreferencesManager.getNightMode())
                    .load()
            }
            "statute" -> {
                requireView().pdfView.fromAsset("statut.pdf")
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
                    else -> false
                }
            }
        }
        toolbar.menu.findItem(R.id.action_search)?.isVisible =
            PdfFragmentArgs.fromBundle(requireArguments()).pdf == "songbook"
        val searchManager =
            requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        mSearchView = toolbar.menu.findItem(R.id.action_search).actionView as SearchView
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        mSearchView.maxWidth = Integer.MAX_VALUE

        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                findNavController().navigate(
                    PdfFragmentDirections.showSongbookSearchFragment(query ?: "")
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
