package pl.mftau.mftau

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pdf.*

class PdfActivity : AppCompatActivity() {

    companion object {
        const val songBookAsset = "spiewnik.pdf"
        const val statuteAsset = "statut.pdf"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf)

        loadPdfFile()

        scrollUpBtn.setOnClickListener { loadPdfFile() }
    }

    private fun loadPdfFile() {
        if (intent.getStringExtra(MainActivity.pdfActivityExtra) == MainActivity.songBookExtraString) {
            pdfView.fromAsset(songBookAsset)
                    .defaultPage(2)
//                    .nightMode(true)
                    .load()
        } else if (intent.getStringExtra(MainActivity.pdfActivityExtra) == MainActivity.statuteExtraString) {
            pdfView.fromAsset(statuteAsset)
                    .load()
        }
    }
}
