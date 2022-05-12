package pl.mftau.mftau.view.activities

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.FirebaseCrashlytics
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.ActivityMainBinding
import pl.mftau.mftau.utils.PreferencesManager
import pl.mftau.mftau.utils.checkNetworkConnection
import pl.mftau.mftau.utils.hideKeyboard
import pl.mftau.mftau.utils.showNoInternetDialogDataOutOfDate
import pl.mftau.mftau.view.fragments.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mAuth: FirebaseAuth
    private var currentFragmentId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        if (PreferencesManager.getNightMode())
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)

        if (!PreferencesManager.getNightMode()) {
            val wic = WindowInsetsControllerCompat(window, window.decorView)
            wic.isAppearanceLightStatusBars = true
            wic.isAppearanceLightNavigationBars = true
        }
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorBackground)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.colorBackground)

        mAuth = FirebaseAuth.getInstance()
        if (mAuth.currentUser != null)
            FirebaseCrashlytics.getInstance().setUserId(mAuth.currentUser!!.uid)

        (binding.navHostFragment.getFragment<NavHostFragment>()).navController
            .addOnDestinationChangedListener { _, destination, _ ->
                currentFragmentId = destination.id

                when (destination.id) {
                    R.id.membersFragment, R.id.meetingsFragment, R.id.retreatsFragment, R.id.presenceDetailsFragment ->
                        if (!checkNetworkConnection()) showNoInternetDialogDataOutOfDate()
                }
            }

        when (intent.getStringExtra("shortcut")) {
            "songBook" -> {
                intent.putExtra("shortcut", "")
                findNavController(R.id.navHostFragment).navigate(
                    MainFragmentDirections.showPdfFragment("songBook")
                )
            }
            "breviary" -> {
                intent.putExtra("shortcut", "")
                findNavController(R.id.navHostFragment).navigate(
                    MainFragmentDirections.showBreviarySelectFragment()
                )
            }
            "gospel" -> {
                intent.putExtra("shortcut", "")
                findNavController(R.id.navHostFragment).navigate(MainFragmentDirections.showGospelFragment())
            }
        }

//        if (intent.action != null && intent.action == SearchIntents.ACTION_SEARCH) {
//            when (intent.getStringExtra(SearchManager.QUERY)) {
//                "brewiarz", "breviary" -> {
//                    intent.action = null
//                    findNavController(R.id.navHostFragment).navigate(MainFragmentDirections.showListFragment("breviary"))
//                }
//                "śpiewnik" -> {
//                    intent.action = null
//                    findNavController(R.id.navHostFragment).navigate(MainFragmentDirections.showPdfFragment("songBook"))
//                }
//                else -> Log.d("Search query: ", intent.getStringExtra(SearchManager.QUERY))
//            }
//        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController(R.id.navHostFragment))
                || super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        when (currentFragmentId) {
            R.id.mainFragment -> super.onBackPressed()
            R.id.websiteFragment -> (supportFragmentManager.findFragmentById(R.id.navHostFragment)!!
                .childFragmentManager.fragments[0] as WebsiteFragment).onBackPressed()
            R.id.pdfFragment -> (supportFragmentManager.findFragmentById(R.id.navHostFragment)!!
                .childFragmentManager.fragments[0] as PdfFragment).onBackPressed()
            R.id.songbookSearchFragment -> (supportFragmentManager.findFragmentById(R.id.navHostFragment)!!
                .childFragmentManager.fragments[0] as SongbookSearchFragment).onBackPressed()
            else -> onSupportNavigateUp()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return if ((currentFragmentId == R.id.memberEditorFragment && MemberEditorFragment.personHasChanged)
            || (currentFragmentId == R.id.meetingEditorFragment && MeetingEditorFragment.meetingHasChanged)
            || (currentFragmentId == R.id.retreatEditorFragment && RetreatEditorFragment.retreatHasChanged)
            || (currentFragmentId == R.id.presenceCheckFragment && PresenceCheckFragment.listHasChanged)
        ) {
            showUnsavedChangesDialog()
            true
        } else if (currentFragmentId == R.id.presenceCheckFragment) {
            hideKeyboard()
            findNavController(R.id.navHostFragment).navigate(R.id.moveBackToMeetingsList)
            true
        } else if (currentFragmentId == R.id.pdfFragment) {
            if (PreferencesManager.getAwakeSongbook())
                window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            findNavController(R.id.navHostFragment).navigateUp()
        } else {
            hideKeyboard()
            findNavController(R.id.navHostFragment).navigateUp()
        }
    }

    private fun showUnsavedChangesDialog() =
        AlertDialog.Builder(this@MainActivity)
            .setMessage(R.string.unsaved_changes_dialog_msg)
            .setCancelable(false)
            .setPositiveButton(R.string.discard) { dialog, _ ->
                dialog.dismiss()
                hideKeyboard()
                findNavController(R.id.navHostFragment).navigateUp()
            }
            .setNegativeButton(R.string.keep_editing) { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
}
