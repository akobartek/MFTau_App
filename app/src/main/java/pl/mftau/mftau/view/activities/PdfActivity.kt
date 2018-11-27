package pl.mftau.mftau.view.activities

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_pdf.*
import pl.mftau.mftau.R

class PdfActivity : AppCompatActivity() {

    companion object {
        const val songBookAsset = "spiewnik.pdf"
        const val statuteAsset = "statut.pdf"
    }

    private var isNightMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        if (PreferenceManager.getDefaultSharedPreferences(this@PdfActivity)
                        .getBoolean(getString(R.string.night_mode_key), false)) {
            setTheme(R.style.AppTheme_Dark)
            window.statusBarColor = Color.BLACK
            isNightMode = true
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                window.statusBarColor = Color.WHITE
            }
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf)

        loadPdfFile()

        scrollUpBtn.setOnClickListener { loadPdfFile() }
    }

    override fun onBackPressed() {
        startActivity(Intent(this@PdfActivity, MainActivity::class.java))
        finish()
    }

    private fun loadPdfFile() {
        if (intent.getStringExtra(MainActivity.pdfActivityExtra) == MainActivity.songBookExtraString) {
            pdfView.fromAsset(songBookAsset)
                    .defaultPage(2)
                    .nightMode(isNightMode)
                    .load()
        } else if (intent.getStringExtra(MainActivity.pdfActivityExtra) == MainActivity.statuteExtraString) {
            pdfView.fromAsset(statuteAsset)
                    .nightMode(isNightMode)
                    .load()
        }
    }
}
