package pl.mftau.mftau.view.activities

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NavUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_emaus.*
import pl.mftau.mftau.R
import pl.mftau.mftau.db.entities.MemberEntity
import pl.mftau.mftau.view.adapters.EmausRecyclerAdapter
import pl.mftau.mftau.viewmodel.EmausViewModel

class EmausActivity : AppCompatActivity() {

    private lateinit var mEmausViewModel: EmausViewModel
    private lateinit var mAdapter: EmausRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emaus)
        setSupportActionBar(drawsToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.WHITE
        }

        mEmausViewModel = ViewModelProviders.of(this@EmausActivity).get(EmausViewModel::class.java)
        mAdapter = EmausRecyclerAdapter()

        setupRecyclerView()

        mEmausViewModel.getAllMembersFromDatabase().observe(this@EmausActivity, Observer { databaseList ->
            if (databaseList.isEmpty()) {
                loadMembersFromFirebase()
            } else {
                mEmausViewModel.members = databaseList
                trySetAdapter()
            }
        })

        mEmausViewModel.getLastDrawsFromDatabase().observe(this@EmausActivity, Observer { draws ->
            if (draws.isEmpty()) {
                drawsEmptyView.visibility = View.VISIBLE
                drawsRecyclerView.visibility = View.INVISIBLE
                oddPerson.visibility = View.INVISIBLE
            } else {
                mEmausViewModel.draws = draws.toString()
                        .replace("[", "")
                        .replace("]", "")
                        .split(",")
                trySetAdapter()
            }
            invalidateOptionsMenu()
        })

        setOnClickListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_emaus, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        if (mEmausViewModel.draws == null) {
            menu.findItem(R.id.action_delete_last_draw).isVisible = false
            menu.findItem(R.id.action_reset_draws).isVisible = false
            menu.findItem(R.id.action_copy_draws).isVisible = false
        } else {
            menu.findItem(R.id.action_delete_last_draw).isVisible = true
            menu.findItem(R.id.action_reset_draws).isVisible = true
            menu.findItem(R.id.action_copy_draws).isVisible = true
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_copy_draws -> {
                mEmausViewModel.copyDrawsToClipboard()
                Snackbar.make(emausLayout, R.string.copied_draws, Snackbar.LENGTH_LONG).show()
                true
            }
            R.id.action_reload_members -> {
                loadMembersFromFirebase()
                true
            }
            R.id.action_delete_last_draw -> {
                showDeleteLastDrawDialog()
                true
            }
            R.id.action_reset_draws -> {
                showResetDrawsDialog()
                true
            }
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this@EmausActivity)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupRecyclerView() {
        drawsRecyclerView.layoutManager = LinearLayoutManager(this@EmausActivity)
        drawsRecyclerView.itemAnimator = DefaultItemAnimator()
        drawsRecyclerView.adapter = mAdapter
        drawsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy < 0 && !startDrawBtn.isShown)
                    startDrawBtn.show()
                else if (dy > 0 && startDrawBtn.isShown)
                    startDrawBtn.hide()
            }
        })
    }

    private fun setOnClickListeners() {
        startDrawBtn.setOnClickListener {
            if (mEmausViewModel.members != null
                    && (mEmausViewModel.getMaxNumberOfDraw() >= (mEmausViewModel.members!!.size - 1))) {
                showFullListDialog()
            } else {
                if (!mEmausViewModel.startDraw()) {
                    showFullListDialog()
                }
            }
        }
    }

    private fun trySetAdapter() {
        if (mEmausViewModel.members == null || mEmausViewModel.draws == null) {
            return
        } else {
            val drawsWithMembers = arrayListOf<Pair<MemberEntity?, MemberEntity?>>()
            mEmausViewModel.draws!!.forEach { draw ->
                drawsWithMembers.add(Pair(
                        mEmausViewModel.members!!
                                .singleOrNull {
                                    it.id == draw.substring(0, draw.indexOf("+"))
                                },
                        mEmausViewModel.members!!
                                .singleOrNull {
                                    it.id == draw.substring(draw.indexOf("+") + 1, draw.length)
                                }
                ))
            }
            mAdapter.setDraws(drawsWithMembers)
            drawsEmptyView.visibility = View.INVISIBLE
            drawsRecyclerView.visibility = View.VISIBLE

            val oddPersonID = mEmausViewModel.getOddPersonID()
            if (oddPersonID != null && oddPersonID != "") {
                val text = "${mEmausViewModel.getMemberNameByIdFromDatabase(oddPersonID)} ${getString(R.string.not_drawn)}"
                oddPerson.text = text
                oddPerson.visibility = View.VISIBLE
            } else {
                oddPerson.visibility = View.GONE
            }
        }
    }

    private fun loadMembersFromFirebase() {
        mEmausViewModel.getAllMembersFromFirebase().observe(this@EmausActivity, Observer { firebaseList ->
            if (firebaseList.isEmpty()) {
                showNoPeopleDialog()
            } else {
                val memberEntities = arrayListOf<MemberEntity>()
                firebaseList.forEach {
                    memberEntities.add(MemberEntity(it.id, it.name, arrayListOf()))
                }
                mEmausViewModel.insertMembersToDatabase(memberEntities)

                // Clear database from members that was deleted from Firebase.
                if (mEmausViewModel.members != null) {
                    mEmausViewModel.deleteMembersInDatabase(
                            mEmausViewModel.members!!.filter { databaseMember ->
                                firebaseList.singleOrNull { firebaseMember ->
                                    databaseMember.id == firebaseMember.id
                                } == null
                            })
                }
            }
        })
    }

    private fun showFullListDialog() =
            AlertDialog.Builder(this@EmausActivity)
                    .setMessage(R.string.full_draws_msg)
                    .setCancelable(false)
                    .setPositiveButton(R.string.yes) { dialog, _ ->
                        dialog.dismiss()
                        mEmausViewModel.deleteAllDrawsInDatabase()
                        mEmausViewModel.startDraw()
                    }
                    .setNegativeButton(R.string.no) { dialog, _ -> dialog?.dismiss() }
                    .create()
                    .show()

    private fun showDeleteLastDrawDialog() =
            AlertDialog.Builder(this@EmausActivity)
                    .setMessage(R.string.delete_last_draw_msg)
                    .setCancelable(false)
                    .setPositiveButton(R.string.yes) { dialog, _ ->
                        dialog.dismiss()
                        mEmausViewModel.deleteLastDrawInDatabase()
                    }
                    .setNegativeButton(R.string.no) { dialog, _ -> dialog?.dismiss() }
                    .create()
                    .show()

    private fun showResetDrawsDialog() =
            AlertDialog.Builder(this@EmausActivity)
                    .setMessage(R.string.reset_draws_msg)
                    .setCancelable(false)
                    .setPositiveButton(R.string.yes) { dialog, _ ->
                        dialog.dismiss()
                        mEmausViewModel.deleteAllDrawsInDatabase()
                        finish()
                        startActivity(intent)
                    }
                    .setNegativeButton(R.string.no) { dialog, _ -> dialog?.dismiss() }
                    .create()
                    .show()

    private fun showNoPeopleDialog() =
            AlertDialog.Builder(this@EmausActivity)
                    .setMessage(getString(R.string.no_people_msg))
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                        dialog?.dismiss()
                        finish()
                    }
                    .create()
                    .show()
}
