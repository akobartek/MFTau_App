package pl.mftau.mftau.view.activities

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.app.NavUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_members.*
import pl.mftau.mftau.R
import pl.mftau.mftau.view.adapters.MembersRecyclerAdapter
import pl.mftau.mftau.viewmodel.MembersViewModel

class MembersActivity : AppCompatActivity() {

    private lateinit var mMembersViewModel: MembersViewModel
    private lateinit var mAdapter: MembersRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        if (PreferenceManager.getDefaultSharedPreferences(this@MembersActivity)
                        .getBoolean(getString(R.string.night_mode_key), false)) {
            setTheme(R.style.AppTheme_Dark)
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                window.statusBarColor = Color.WHITE
            }
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_members)
        setSupportActionBar(membersToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        mMembersViewModel = ViewModelProviders.of(this@MembersActivity).get(MembersViewModel::class.java)
        mAdapter = MembersRecyclerAdapter()

        membersRecyclerView.layoutManager = LinearLayoutManager(this@MembersActivity)
        membersRecyclerView.itemAnimator = DefaultItemAnimator()
        membersRecyclerView.adapter = mAdapter
        membersRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy < 0 && !addMemberBtn.isShown)
                    addMemberBtn.show()
                else if (dy > 0 && addMemberBtn.isShown)
                    addMemberBtn.hide()
            }
        })

        mMembersViewModel.getAllMembers().observe(this@MembersActivity, Observer { members ->
            membersRecyclerView.layoutAnimation =
                    AnimationUtils.loadLayoutAnimation(membersRecyclerView.context, R.anim.layout_animation_fall_down)
            mAdapter.setMemberList(members)
            membersRecyclerView.scheduleLayoutAnimation()

            loadingIndicator.hide()
            if (members.isEmpty()) {
                emptyView.visibility = View.VISIBLE
            } else {
                emptyView.visibility = View.INVISIBLE
            }
        })

        addMemberBtn.setOnClickListener { startActivity(Intent(this@MembersActivity, MemberEditorActivity::class.java)) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_members, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_emauses -> {
                startActivity(Intent(this@MembersActivity, EmausActivity::class.java))
                true
            }
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this@MembersActivity)
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
