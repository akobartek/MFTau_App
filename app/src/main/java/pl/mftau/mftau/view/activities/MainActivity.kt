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
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import pl.mftau.mftau.R
import pl.mftau.mftau.model.utils.FirestoreUtils
import pl.mftau.mftau.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    companion object {
        const val pdfActivityExtra = "pdf"
        const val songBookExtraString = "songBook"
        const val statuteExtraString = "statute"
    }

    private lateinit var mMainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.WHITE
        }

        mMainViewModel = ViewModelProviders.of(this@MainActivity).get(MainViewModel::class.java)

        setOnClickListeners()
    }

    override fun onResume() {
        super.onResume()
        // TODO (Auth) -> Turn on verification
        if (FirebaseAuth.getInstance().currentUser != null /*&& mAuth.currentUser!!.isEmailVerified*/) {
            FirebaseFirestore.getInstance().collection(FirestoreUtils.firestoreCollectionUsers)
                    .document(FirebaseAuth.getInstance().currentUser!!.uid)
                    .get()
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            when {
                                (task.result!!.get(FirestoreUtils.firestoreKeyIsAdmin) as Boolean) -> {
                                    showUIChanges(MainViewModel.USER_TYPE_ADMIN)
                                }
                                (task.result!!.get(FirestoreUtils.firestoreKeyIsLeader) as Boolean) -> {
                                    showUIChanges(MainViewModel.USER_TYPE_LEADER)
                                }
                                (task.result!!.get(FirestoreUtils.firestoreKeyIsMember) as Boolean) -> {
                                    showUIChanges(MainViewModel.USER_TYPE_MEMBER)
                                }
                                else -> showUIChanges(MainViewModel.USER_TYPE_NONE)
                            }
                        }
                    }
        } else {
            showUIChanges(MainViewModel.USER_TYPE_NONE)
        }
    }

    private fun showUIChanges(userType: Int) {
        when (userType) {
            MainViewModel.USER_TYPE_ADMIN -> {
                showAdminUI(true)
                showLeaderUI(false)
                showNormalUserUI(false)
            }
            MainViewModel.USER_TYPE_LEADER -> {
                showAdminUI(false)
                showLeaderUI(true)
                showNormalUserUI(false)
            }
            MainViewModel.USER_TYPE_MEMBER -> {
                showAdminUI(false)
                showLeaderUI(false)
                showNormalUserUI(true)
            }
            MainViewModel.USER_TYPE_NONE -> {
                showAdminUI(false)
                showLeaderUI(false)
                showNormalUserUI(false)
            }
        }
    }

    private fun showAdminUI(isLogged: Boolean) {

    }

    private fun showLeaderUI(isLogged: Boolean) {
        members.isClickable = isLogged
        meetings.isClickable = isLogged

        if (isLogged && members.alpha == 0f) {
            members.animate().alpha(1f).duration = 333
            meetings.animate().alpha(1f).duration = 333
        } else if (!isLogged && members.alpha == 1f) {
            members.animate().alpha(0f).duration = 333
            meetings.animate().alpha(0f).duration = 333
        }
    }

    private fun showNormalUserUI(isLogged: Boolean) {

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

            if (FirebaseAuth.getInstance().currentUser != null)
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
                        FirebaseAuth.getInstance().signOut()
                        showUIChanges(MainViewModel.USER_TYPE_NONE)
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
            startActivity(Intent(this@MainActivity, PrayerActivity::class.java))
        }

        website.setOnClickListener {
            startActivity(Intent(this@MainActivity, WebsiteActivity::class.java))
        }
    }
}
