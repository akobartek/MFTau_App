package pl.mftau.mftau

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE


class MainActivity : AppCompatActivity() {

    companion object {
        const val pdfActivityExtra = "pdf"
        const val songBookExtraString = "songBook"
        const val statuteExtraString = "statute"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.WHITE
        }

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        songBook.setOnClickListener {
            startActivity(Intent(this@MainActivity, PdfActivity::class.java)
                    .putExtra(pdfActivityExtra, songBookExtraString))
        }

        prayerBook.setOnClickListener {

        }

        statute.setOnClickListener {
            startActivity(Intent(this@MainActivity, PdfActivity::class.java)
                    .putExtra(pdfActivityExtra, statuteExtraString))
        }

        breviary.setOnClickListener {
            startActivity(Intent(this@MainActivity, BreviaryActivity::class.java))
        }

        website.setOnClickListener {
            startActivity(Intent(this@MainActivity, WebsiteActivity::class.java))
        }
    }
}
