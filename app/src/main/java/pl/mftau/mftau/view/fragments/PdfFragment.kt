package pl.mftau.mftau.view.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_pdf.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.viewmodel.MainViewModel

class PdfFragment : Fragment() {

    private var mViewModel: MainViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_pdf, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let { mViewModel = ViewModelProviders.of(it).get(MainViewModel::class.java) }

        loadPdfFile(savedInstanceState, false)
        view.scrollUpBtn.setOnClickListener { loadPdfFile(null, true) }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("page", view?.pdfView?.currentPage ?: 2)
    }

    private fun loadPdfFile(savedInstanceState: Bundle?, isBtnPressed: Boolean) {
        when (PdfFragmentArgs.fromBundle(arguments!!).pdf) {
            "songBook" -> {
                view!!.pdfView.fromAsset("spiewnik.pdf")
                    .defaultPage(if (isBtnPressed) 4 else savedInstanceState?.getInt("page") ?: 4)
                    .nightMode(mViewModel?.isNightMode ?: false)
                    .load()
            }
            "statute" -> {
                view!!.pdfView.fromAsset("statut.pdf")
                    .nightMode(mViewModel?.isNightMode ?: false)
                    .load()
            }
        }
    }
}
