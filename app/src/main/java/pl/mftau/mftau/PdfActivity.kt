package pl.mftau.mftau

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_pdf.*

class PdfActivity : AppCompatActivity() {

    companion object {
        const val songBookAsset = "spiewnik.pdf"
        const val statuteAsset = "statut.pdf"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.WHITE
        }

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
