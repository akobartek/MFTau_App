package pl.mftau.mftau.view.activities

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.app.NavUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_prayer.*
import pl.mftau.mftau.R
import pl.mftau.mftau.utils.PrayerUtils
import pl.mftau.mftau.viewmodel.PrayerViewModel

class PrayerActivity : AppCompatActivity() {

    private lateinit var mPrayerViewModel: PrayerViewModel

    private val animationDuration = 444L
    private var isNightMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        if (PreferenceManager.getDefaultSharedPreferences(this@PrayerActivity)
                        .getBoolean(getString(R.string.night_mode_key), false)) {
            setTheme(R.style.AppTheme_Dark)
            isNightMode = true
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                window.statusBarColor = Color.WHITE
            }
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prayer)
        setSupportActionBar(prayerToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        mPrayerViewModel = ViewModelProviders.of(this@PrayerActivity).get(PrayerViewModel::class.java)

        prayerList.adapter = ArrayAdapter<String>(this@PrayerActivity,
                R.layout.item_listview, PrayerUtils.prayerNames)

        mPrayerViewModel.getActivityStatus().observe(this@PrayerActivity, Observer { activityStatus ->
            when {
                activityStatus.first -> {
                    prayerText.text = PrayerUtils.prayerBook[activityStatus.second]
                    prayerScrollView.visibility = View.VISIBLE
                    prayerScrollView.animate()
                            .alpha(1f)
                            .duration = animationDuration
                    prayerList.animate()
                            .alpha(0f)
                            .duration = animationDuration / 2
                    title = prayerList.getItemAtPosition(activityStatus.second).toString()
                }
                activityStatus.third -> {
                    if (activityStatus.second == -1) {
                        startActivity(Intent(this@PrayerActivity, MainActivity::class.java))
                        finish()
                    } else {
                        title = getString(R.string.prayer)
                        prayerScrollView.animate()
                                .alpha(0f)
                                .withEndAction { prayerScrollView.visibility = View.INVISIBLE }
                                .duration = animationDuration * 3 / 5
                        prayerList.animate()
                                .alpha(1f)
                                .duration = animationDuration * 4 / 5
                        mPrayerViewModel.setActivityStatus(false, -1, false)
                    }
                }
            }
        })

        prayerList.setOnItemClickListener { _, _, position, _ ->
            mPrayerViewModel.setActivityStatus(true, position, false)
        }
    }

    override fun onBackPressed() {
        mPrayerViewModel.setActivityStatus(false, null, true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
