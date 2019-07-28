package pl.mftau.mftau.view.activities

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.preference.PreferenceManager
import com.crashlytics.android.Crashlytics
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import pl.mftau.mftau.R
import pl.mftau.mftau.model.Member
import pl.mftau.mftau.model.Retreat
import pl.mftau.mftau.utils.PrayerUtils
import pl.mftau.mftau.utils.checkNetworkConnection
import pl.mftau.mftau.utils.showNoInternetDialogDataOutOfDate
import pl.mftau.mftau.view.fragments.*
import pl.mftau.mftau.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mMainViewModel: MainViewModel
    private var isNightMode = false
    private var currentFragmentId = 0

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        if (PreferenceManager.getDefaultSharedPreferences(this@MainActivity)
                .getBoolean(getString(R.string.night_mode_key), false)
        ) {
            setTheme(R.style.AppTheme_Dark)
            window.decorView.systemUiVisibility = 0
            isNightMode = true
        } else {
            setTheme(R.style.AppTheme_Light)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(mainToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        if (!isNightMode && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.WHITE
        }

        val navController = (navHostFragment as NavHostFragment? ?: return).navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        mMainViewModel = ViewModelProviders.of(this@MainActivity).get(MainViewModel::class.java)
        mMainViewModel.isNightMode = isNightMode

        mAuth = FirebaseAuth.getInstance()
        if (mAuth.currentUser != null) {
            Crashlytics.setUserIdentifier(mAuth.currentUser!!.uid)
            Crashlytics.setUserEmail(mAuth.currentUser!!.email)
        }

        navController.addOnDestinationChangedListener { _, destination, arguments ->
            currentFragmentId = destination.id

            when (destination.id) {
                R.id.mainFragment, R.id.pdfFragment, R.id.websiteFragment -> supportActionBar?.hide()
                else -> supportActionBar?.show()
            }
            mainToolbarTitle.text = when (destination.id) {
                R.id.mainFragment -> getString(R.string.app_name)
                R.id.listFragment -> when {
                    arguments!!["listType"] as String == "breviary" -> getString(R.string.breviary)
                    arguments["listType"] as String == "prayer" -> getString(R.string.prayer)
                    else -> ""
                }
                R.id.breviaryFragment -> resources.getStringArray(R.array.breviary_list)[arguments!!["position"] as Int]
                R.id.prayerFragment -> PrayerUtils.prayerNames[arguments!!["position"] as Int]
                R.id.gospelFragment -> getString(R.string.gospel_for_today)
                R.id.emailFragment -> when {
                    arguments!!["emailType"] as String == "pray" -> getString(R.string.ask_for_pray)
                    arguments["emailType"] as String == "error" -> getString(R.string.report_error)
                    else -> ""
                }
                R.id.preferencesFragment -> getString(R.string.preferences)
                R.id.loginFragment -> ""
                R.id.membersFragment -> getString(R.string.members)
                R.id.emausFragment -> getString(R.string.emauses)
                R.id.memberEditorFragment -> if (arguments!!["member"] == null) getString(R.string.add_member) else getString(
                    R.string.edit_member
                )
                R.id.meetingsFragment -> getString(R.string.meetings)
                R.id.meetingEditorFragment -> if (arguments!!["meeting"] == null) getString(R.string.add_meeting) else getString(
                    R.string.edit_meeting
                )
                R.id.presenceCheckFragment -> getString(R.string.check_presence)
                R.id.presenceListFragment -> getString(R.string.presence)
                R.id.retreatsFragment -> getString(R.string.retreat)
                R.id.retreatEditorFragment -> if (arguments!!["retreat"] == null) getString(R.string.add_retreat) else getString(
                    R.string.edit_retreat
                )
                R.id.retreatDetailsFragment -> (arguments!!["retreat"] as Retreat).name
                R.id.presenceDetailsFragment -> getString(
                    R.string.presence_list_title,
                    (arguments!!["member"] as Member).name
                )
                else -> getString(R.string.app_name)
            }
            when (destination.id) {
                R.id.membersFragment, R.id.meetingsFragment, R.id.retreatsFragment, R.id.presenceDetailsFragment ->
                    if (!checkNetworkConnection()) showNoInternetDialogDataOutOfDate()
            }
        }

        when (intent.getStringExtra("shortcut")) {
            "songBook" -> {
                intent.putExtra("shortcut", "")
                findNavController(R.id.navHostFragment).navigate(MainFragmentDirections.showPdfFragment("songBook"))
            }
            "breviary" -> {
                intent.putExtra("shortcut", "")
                findNavController(R.id.navHostFragment).navigate(MainFragmentDirections.showListFragment("breviary"))
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

    override fun onResume() {
        super.onResume()
        if (isNightMode != PreferenceManager.getDefaultSharedPreferences(this@MainActivity)
                .getBoolean(getString(R.string.night_mode_key), false)
        ) recreate()
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
            findNavController(R.id.navHostFragment).navigate(R.id.moveBackToMeetingsList)
            true
        } else findNavController(R.id.navHostFragment).navigateUp()
    }

    private fun showUnsavedChangesDialog() =
        AlertDialog.Builder(this@MainActivity)
            .setMessage(R.string.unsaved_changes_dialog_msg)
            .setCancelable(false)
            .setPositiveButton(R.string.discard) { dialog, _ ->
                dialog.dismiss()
                findNavController(R.id.navHostFragment).navigateUp()
            }
            .setNegativeButton(R.string.keep_editing) { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
}
