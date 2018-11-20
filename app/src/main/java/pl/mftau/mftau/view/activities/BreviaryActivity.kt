package pl.mftau.mftau.view.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NavUtils
import android.view.MenuItem
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_breviary.*
import android.os.Build
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import pl.mftau.mftau.R
import pl.mftau.mftau.viewmodel.BreviaryViewModel


class BreviaryActivity : AppCompatActivity() {

    private lateinit var mBreviaryViewModel: BreviaryViewModel

    private val animationDuration = 444L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breviary)
        setSupportActionBar(breviaryToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.WHITE
        }

        mBreviaryViewModel = ViewModelProviders.of(this@BreviaryActivity).get(BreviaryViewModel::class.java)

        breviaryList.adapter = ArrayAdapter<String>(this@BreviaryActivity,
                android.R.layout.simple_list_item_1, resources.getStringArray(R.array.breviary_list))

        mBreviaryViewModel.getActivityStatus().observe(this@BreviaryActivity, Observer { activityStatus ->
            when {
                activityStatus.first -> {
                    breviaryText.loadData(mBreviaryViewModel.getBreviary(), "text/html", "UTF-8")
                    breviaryText.visibility = View.VISIBLE
                    breviaryText.scrollTo(0, 0)
                    breviaryText.animate().alpha(1f).duration = animationDuration
                    title = breviaryList.getItemAtPosition(activityStatus.second).toString()
                }
                activityStatus.third -> {
                    if (activityStatus.second == -1) {
                        NavUtils.navigateUpFromSameTask(this@BreviaryActivity)
                    } else {
                        title = getString(R.string.breviary)
                        breviaryText.animate()
                                .alpha(0f)
                                .withEndAction { breviaryText.visibility = View.INVISIBLE }
                                .duration = animationDuration * 3 / 5
                        mBreviaryViewModel.setActivityStatus(false, -1, false)
                    }
                }
            }
        })

        breviaryList.setOnItemClickListener { _, _, position, _ ->
            mBreviaryViewModel.setActivityStatus(true, position, false)
        }
    }

    override fun onBackPressed() {
        mBreviaryViewModel.setActivityStatus(false, null, true)
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