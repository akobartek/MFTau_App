package pl.mftau.mftau.view.activities

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_retreat.*
import pl.mftau.mftau.R
import pl.mftau.mftau.view.adapters.RetreatRecyclerAdapter
import pl.mftau.mftau.viewmodel.MainViewModel
import pl.mftau.mftau.viewmodel.RetreatViewModel
import java.util.*

class RetreatActivity : AppCompatActivity() {

    private lateinit var mRetreatViewModel: RetreatViewModel
    private lateinit var mAdapter: RetreatRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        if (PreferenceManager.getDefaultSharedPreferences(this@RetreatActivity)
                        .getBoolean(getString(R.string.night_mode_key), false)) {
            setTheme(R.style.AppTheme_Dark)
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                window.statusBarColor = Color.WHITE
            }
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retreat)
        setSupportActionBar(retreatToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        mRetreatViewModel = ViewModelProviders.of(this@RetreatActivity).get(RetreatViewModel::class.java)

        val userType = intent.getIntExtra("userType", MainViewModel.USER_TYPE_NONE)
        mAdapter = RetreatRecyclerAdapter(userType, this@RetreatActivity)

        if (userType != MainViewModel.USER_TYPE_ADMIN) {
            addRetreatBtn.hide()
        }

        retreatRecyclerView.layoutManager = LinearLayoutManager(this@RetreatActivity)
        retreatRecyclerView.itemAnimator = DefaultItemAnimator()
        retreatRecyclerView.adapter = mAdapter
        retreatRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy < 0 && !addRetreatBtn.isShown)
                    addRetreatBtn.show()
                else if (dy > 0 && addRetreatBtn.isShown)
                    addRetreatBtn.hide()
            }
        })

        mRetreatViewModel.getAllRetreats().observe(this@RetreatActivity, Observer { retreats ->
            retreatRecyclerView.layoutAnimation =
                    AnimationUtils.loadLayoutAnimation(retreatRecyclerView.context, R.anim.layout_animation_fall_down)
            mAdapter.setRetreatList(retreats)
            retreatRecyclerView.scheduleLayoutAnimation()

            loadingIndicator.hide()
            if (retreats.isEmpty()) {
                emptyView.visibility = View.VISIBLE
            } else {
                emptyView.visibility = View.INVISIBLE
            }

            val expiredRetreats = retreats.filter { it.endDate.toDate() < Date(Date().time - 86400000) }
            expiredRetreats.forEach { mRetreatViewModel.deleteRetreat(this@RetreatActivity, it.id) }
        })

        addRetreatBtn.setOnClickListener {
            startActivity(Intent(this@RetreatActivity, RetreatEditActivity::class.java))
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this@RetreatActivity, MainActivity::class.java))
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data != null) {
            val userType = data.getIntExtra("userType", MainViewModel.USER_TYPE_NONE)
            mAdapter.userType = userType
            if (userType != MainViewModel.USER_TYPE_ADMIN) {
                addRetreatBtn.hide()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
