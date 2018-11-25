package pl.mftau.mftau.view.activities

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NavUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_chart.*
import pl.mftau.mftau.R
import pl.mftau.mftau.view.adapters.ChartRecyclerAdapter
import pl.mftau.mftau.viewmodel.ChartViewModel

class ChartActivity : AppCompatActivity() {

    private lateinit var mChartViewModel: ChartViewModel
    private lateinit var mAdapter: ChartRecyclerAdapter

    private lateinit var mLoadingDialog: AlertDialog
    private var isNightMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        if (PreferenceManager.getDefaultSharedPreferences(this@ChartActivity)
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
        setContentView(R.layout.activity_chart)
        setSupportActionBar(chartToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        mChartViewModel = ViewModelProviders.of(this@ChartActivity).get(ChartViewModel::class.java)
        mAdapter = ChartRecyclerAdapter()

        mLoadingDialog = AlertDialog.Builder(this@ChartActivity)
                .setView(R.layout.dialog_loading)
                .create()
        mLoadingDialog.show()

        chartRecyclerView.layoutManager = LinearLayoutManager(this@ChartActivity)
        chartRecyclerView.itemAnimator = DefaultItemAnimator()

        mChartViewModel.getAllMember().observe(this@ChartActivity, Observer { members ->
            mChartViewModel.members = members
            members.forEach { mChartViewModel.presence[it.id] = arrayOf(0, 0, 0) }

            if (members.isNotEmpty())
                setPresence()
            else {
                emptyView.visibility = View.VISIBLE
                chartRecyclerView.visibility = View.INVISIBLE
                mLoadingDialog.dismiss()
            }
        })
    }

    private fun setPresence() {
        mChartViewModel.setPresence()
        setDataToChart()
    }

    private fun setDataToChart() {
        if (mChartViewModel.members.isNullOrEmpty() || mChartViewModel.presence.isNullOrEmpty())
            return

        mAdapter.setLists(mChartViewModel.members!!, mChartViewModel.presence, mChartViewModel.numberOfMeetings, isNightMode)
        chartRecyclerView.adapter = mAdapter
        mLoadingDialog.dismiss()
        chartRecyclerView.scrollToPosition(mChartViewModel.members!!.size - 1)
        chartRecyclerView.smoothScrollToPosition(0)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this@ChartActivity)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
