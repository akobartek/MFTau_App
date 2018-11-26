package pl.mftau.mftau.view.activities

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.PopupMenu
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import pl.mftau.mftau.R
import pl.mftau.mftau.utils.FirestoreUtils
import pl.mftau.mftau.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    companion object {
        const val pdfActivityExtra = "pdf"
        const val songBookExtraString = "songBook"
        const val statuteExtraString = "statute"
    }

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mMainViewModel: MainViewModel
    private var isNightMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        if (PreferenceManager.getDefaultSharedPreferences(this@MainActivity)
                        .getBoolean(getString(R.string.night_mode_key), false)) {
            setTheme(R.style.AppTheme_Dark)
            isNightMode = true
        } else {
            setTheme(R.style.AppTheme_Light)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!isNightMode && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.WHITE
        }

        mAuth = FirebaseAuth.getInstance()
        mMainViewModel = ViewModelProviders.of(this@MainActivity).get(MainViewModel::class.java)

        setOnClickListeners()
    }

    override fun onResume() {
        super.onResume()
        if (isNightMode != PreferenceManager.getDefaultSharedPreferences(this@MainActivity)
                        .getBoolean(getString(R.string.night_mode_key), false)) {
            recreate()
        }

        if (mAuth.currentUser != null && mAuth.currentUser!!.isEmailVerified) {
            FirebaseFirestore.getInstance().collection(FirestoreUtils.firestoreCollectionUsers)
                    .document(FirebaseAuth.getInstance().currentUser!!.uid)
                    .get()
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            when {
                                (task.result!!.get(FirestoreUtils.firestoreKeyIsAdmin) as Boolean) -> {
                                    showUIChanges(MainViewModel.USER_TYPE_ADMIN)
                                    mMainViewModel.currentUserType = MainViewModel.USER_TYPE_ADMIN
                                }
                                (task.result!!.get(FirestoreUtils.firestoreKeyIsLeader) as Boolean) -> {
                                    showUIChanges(MainViewModel.USER_TYPE_LEADER)
                                    mMainViewModel.currentUserType = MainViewModel.USER_TYPE_LEADER
                                }
                                (task.result!!.get(FirestoreUtils.firestoreKeyIsMember) as Boolean) -> {
                                    showUIChanges(MainViewModel.USER_TYPE_MEMBER)
                                    mMainViewModel.currentUserType = MainViewModel.USER_TYPE_MEMBER
                                }
                                else -> {
                                    showUIChanges(MainViewModel.USER_TYPE_NONE)
                                    mMainViewModel.currentUserType = MainViewModel.USER_TYPE_NONE
                                }
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
            }
            MainViewModel.USER_TYPE_LEADER -> {
                showLeaderUI(true)
            }
            MainViewModel.USER_TYPE_MEMBER -> {
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
        retreat.isClickable = isLogged

        if (isLogged && retreat.alpha == 0f) {
            retreat.animate()
                    .alpha(1f)
                    .withStartAction {
                        members.visibility = View.INVISIBLE
                        meetings.visibility = View.INVISIBLE
                        retreat.visibility = View.VISIBLE
                    }
                    .duration = 333
        } else if (!isLogged && retreat.alpha == 1f) {
            retreat.animate()
                    .alpha(0f)
                    .duration = 333
        }
    }

    private fun showLeaderUI(isLogged: Boolean) {
        members.isClickable = isLogged
        meetings.isClickable = isLogged

        if (isLogged && members.alpha == 0f) {
            members.animate()
                    .alpha(1f)
                    .withStartAction {
                        retreat.visibility = View.INVISIBLE
                        members.visibility = View.VISIBLE
                        meetings.visibility = View.VISIBLE
                    }
                    .duration = 333
            meetings.animate()
                    .alpha(1f)
                    .duration = 333
        } else if (!isLogged && members.alpha == 1f) {
            members.animate()
                    .alpha(0f)
                    .duration = 333
            meetings.animate()
                    .alpha(0f)
                    .duration = 333
        }
    }

    private fun showNormalUserUI(isLogged: Boolean) {
        retreat.isClickable = isLogged

        if (isLogged && retreat.alpha == 0f) {
            retreat.animate()
                    .alpha(1f)
                    .withStartAction {
                        members.visibility = View.INVISIBLE
                        meetings.visibility = View.INVISIBLE
                        retreat.visibility = View.VISIBLE
                    }
                    .duration = 333
        } else if (!isLogged && retreat.alpha == 1f) {
            retreat.animate()
                    .alpha(0f)
                    .duration = 333
        }
    }

    private fun setOnClickListeners() {
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
                        startActivity(Intent(this@MainActivity, PreferenceActivity::class.java))
                        true
                    }
                    R.id.action_ask_for_pray -> {
                        val intent = Intent(this@MainActivity, EmailActivity::class.java)
                        intent.putExtra("email", "pray")
                        startActivity(intent)
                        true
                    }
                    R.id.action_report_error -> {
                        val intent = Intent(this@MainActivity, EmailActivity::class.java)
                        intent.putExtra("email", "error")
                        startActivity(intent)
                        true
                    }
                    else -> true
                }
            }
            popupMenu.show()
        }

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

        members.setOnClickListener {
            startActivity(Intent(this@MainActivity, MembersActivity::class.java))
        }

        meetings.setOnClickListener {
            startActivity(Intent(this@MainActivity, MeetingsActivity::class.java))
        }

        retreat.setOnClickListener {
            startActivity(Intent(this@MainActivity, RetreatActivity::class.java)
                    .putExtra("userType", mMainViewModel.currentUserType))
        }

        prayerBook.setOnClickListener {
            startActivity(Intent(this@MainActivity, PrayerActivity::class.java))
        }

        website.setOnClickListener {
            startActivity(Intent(this@MainActivity, WebsiteActivity::class.java))
        }
    }
}
