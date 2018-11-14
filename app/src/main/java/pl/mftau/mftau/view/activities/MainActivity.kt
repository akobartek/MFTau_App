package pl.mftau.mftau.view.activities

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
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import pl.mftau.mftau.R
import pl.mftau.mftau.utils.FirestoreUtils.firestoreCollectionUsers
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyEmail
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyIsLeader

class MainActivity : AppCompatActivity() {

    companion object {
        const val pdfActivityExtra = "pdf"
        const val songBookExtraString = "songBook"
        const val statuteExtraString = "statute"
    }

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mFirestore: FirebaseFirestore

    private var isLeaderSignedIn = false
    private var isNormalUserSignedIn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.WHITE
        }

        mAuth = FirebaseAuth.getInstance()
        mFirestore = FirebaseFirestore.getInstance()

        setOnClickListeners()
    }

    override fun onResume() {
        super.onResume()
        // TODO (Auth) -> Turn on verification
        if (mAuth.currentUser != null /*&& mAuth.currentUser!!.isEmailVerified*/) {
            mFirestore.collection(firestoreCollectionUsers)
                    .document(mAuth.currentUser!!.uid)
                    .get()
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful)
                            if (task.result!!.get(firestoreKeyIsLeader) as Boolean)
                                showLeaderUI(true)
                    }
        }
    }

    private fun showLeaderUI(isLogged: Boolean) {
        members.isClickable = isLogged
        meetings.isClickable = isLogged
        isLeaderSignedIn = isLogged

        if (isLogged) {
            members.animate().alpha(1f).duration = 400
            meetings.animate().alpha(1f).duration = 400
        } else {
            members.animate().alpha(0f).duration = 400
            meetings.animate().alpha(0f).duration = 400
        }
    }

    private fun showNormalUserUI(isLogged: Boolean) {
        isNormalUserSignedIn = isLogged
    }

    private fun setOnClickListeners() {
        songBook.setOnClickListener {
            startActivity(Intent(this@MainActivity, PdfActivity::class.java)
                    .putExtra(pdfActivityExtra, songBookExtraString))
        }

        breviary.setOnClickListener {
            startActivity(Intent(this@MainActivity, BreviaryActivity::class.java))
        }

        statute.setOnClickListener {
            startActivity(Intent(this@MainActivity, PdfActivity::class.java)
                    .putExtra(pdfActivityExtra, statuteExtraString))
        }

        menuBtn.setOnClickListener {
            val popupMenu = PopupMenu(this@MainActivity, menuBtn)

            if (mAuth.currentUser != null)
                popupMenu.menuInflater.inflate(R.menu.menu_main_out, popupMenu.menu)
            else
                popupMenu.menuInflater.inflate(R.menu.menu_main_in, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.action_sign_in -> {
                        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                        true
                    }
                    R.id.action_sign_out -> {
                        mAuth.signOut()

                        if (isLeaderSignedIn)
                            showLeaderUI(false)
                        else if (isNormalUserSignedIn)
                            showNormalUserUI(false)

                        true
                    }
                    R.id.action_settings -> {
                        // TODO (Ustawienia) -> Dodać ustawienia
                        Toast.makeText(this@MainActivity, "Tu kiedyś będą otwierać się ustawienia", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> true
                }
            }
            popupMenu.show()
        }

        members.setOnClickListener {
            startActivity(Intent(this@MainActivity, MembersActivity::class.java))
        }

        meetings.setOnClickListener {
            startActivity(Intent(this@MainActivity, MeetingsActivity::class.java))
        }

        prayerBook.setOnClickListener {
            // TODO (Modlitewnik) -> Dodać modlitewnik
            Toast.makeText(this@MainActivity, "Jeszcze nie wiem co tu będzie, oczekuj aktualizacji XD", Toast.LENGTH_SHORT).show()
        }

        website.setOnClickListener {
            startActivity(Intent(this@MainActivity, WebsiteActivity::class.java))
        }
    }
}
