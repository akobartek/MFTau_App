package pl.mftau.mftau

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

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

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        songBook.setOnClickListener {
            startActivity(Intent(this@MainActivity, PdfActivity::class.java)
                    .putExtra(pdfActivityExtra, songBookExtraString))
        }

        prayerBook.setOnClickListener { }

        statute.setOnClickListener {
            startActivity(Intent(this@MainActivity, PdfActivity::class.java)
                    .putExtra(pdfActivityExtra, statuteExtraString))
        }

        breviary.setOnClickListener { }

        website.setOnClickListener {
            startActivity(Intent(this@MainActivity, WebsiteActivity::class.java))
        }
    }
}
