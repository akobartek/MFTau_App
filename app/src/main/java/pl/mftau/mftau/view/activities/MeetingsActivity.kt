package pl.mftau.mftau.view.activities

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NavUtils
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_meetings.*
import pl.mftau.mftau.R
import pl.mftau.mftau.view.adapters.MeetingPagerAdapter
import pl.mftau.mftau.viewmodel.MeetingsViewModel

class MeetingsActivity : AppCompatActivity() {

    private lateinit var mMeetingsViewModel: MeetingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        if (PreferenceManager.getDefaultSharedPreferences(this@MeetingsActivity)
                        .getBoolean(getString(R.string.night_mode_key), false)) {
            setTheme(R.style.AppTheme_Dark)
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                window.statusBarColor = Color.WHITE
            }
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meetings)
        setSupportActionBar(meetingsToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        mMeetingsViewModel = ViewModelProviders.of(this@MeetingsActivity).get(MeetingsViewModel::class.java)

        initViewPager()

        addMeetingBtn.setOnClickListener { startActivity(Intent(this@MeetingsActivity, MeetingEditorActivity::class.java)) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_meetings, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_show_presence -> {
                startActivity(Intent(this@MeetingsActivity, ChartActivity::class.java))
                true
            }
            R.id.action_clear_meetings -> {
                showDeleteConfirmationDialog()
                true
            }
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this@MeetingsActivity)
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun initViewPager() {
        viewPager.adapter = MeetingPagerAdapter(supportFragmentManager, resources.getStringArray(R.array.meeting_types))
        viewPager.currentItem = 0
        viewPager.offscreenPageLimit = 3
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun showDeleteConfirmationDialog() =
            AlertDialog.Builder(this@MeetingsActivity)
                    .setMessage(R.string.delete_meetings_dialog_msg)
                    .setPositiveButton(R.string.delete) { dialog, _ ->
                        dialog.dismiss()
                        clearMeetings()
                    }
                    .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
                    .create()
                    .show()

    private fun clearMeetings() {
        mMeetingsViewModel.clearMeetings()
        Snackbar.make(meetingsLayout, getString(R.string.delete_meetings_successfully), Snackbar.LENGTH_SHORT).show()
    }
}
