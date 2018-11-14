package pl.mftau.mftau.view.activities

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.app.NavUtils
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_meetings.*
import pl.mftau.mftau.R
import pl.mftau.mftau.view.adapters.MeetingPagerAdapter

class MeetingsActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meetings)
        setSupportActionBar(meetingsToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.WHITE
        }

        mAuth = FirebaseAuth.getInstance()

        initViewPager()

        addMeetingBtn.setOnClickListener { startActivity(Intent(this@MeetingsActivity, MeetingEditorActivity::class.java)) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
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
        tabLayout.setupWithViewPager(viewPager)
    }
}
