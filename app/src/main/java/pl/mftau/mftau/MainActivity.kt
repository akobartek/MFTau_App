package pl.mftau.mftau

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.PopupMenu
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    companion object {
        const val pdfActivityExtra = "pdf"
        const val songBookExtraString = "songBook"
        const val statuteExtraString = "statute"
    }

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.WHITE
        }

        mAuth = FirebaseAuth.getInstance()

        setOnClickListeners()
    }

    override fun onResume() {
        super.onResume()
        Log.i("MainActivity", mAuth.currentUser.toString())
    }

    private fun setOnClickListeners() {
        songBook.setOnClickListener {
            startActivity(Intent(this@MainActivity, PdfActivity::class.java)
                    .putExtra(pdfActivityExtra, songBookExtraString))
        }

        prayerBook.setOnClickListener {
            Toast.makeText(this@MainActivity, "Jeszcze nie wiem co tu będzie, oczekuj aktualizacji XD", Toast.LENGTH_SHORT).show()
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

        menuBtn.setOnClickListener { _ ->
            val popupMenu = PopupMenu(this@MainActivity, menuBtn)
            popupMenu.menuInflater.inflate(R.menu.menu_main, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_login -> {
                        // Open loginActivity
                        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                        true
                    }
                    R.id.action_settings -> {
                        Toast.makeText(this@MainActivity, "Tu kiedyś będą otwierać się ustawienia", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> true
                }
            }
            popupMenu.show()
        }
    }
}
