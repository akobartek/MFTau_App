package pl.mftau.mftau.view.activities

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NavUtils
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_chart.*
import pl.mftau.mftau.R
import pl.mftau.mftau.utils.FirestoreUtils
import pl.mftau.mftau.model.Member
import pl.mftau.mftau.view.adapters.ChartRecyclerAdapter

class ChartActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mFirestore: FirebaseFirestore
    private lateinit var mAdapter: ChartRecyclerAdapter

    private var mMemberList = listOf<Member>()
    private val mPresence = HashMap<String, Array<Int>>()
    private val mNumberOfMeetings = arrayOf(0, 0, 0)
    private lateinit var mLoadingDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)
        setSupportActionBar(chartToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.WHITE
        }

        mAuth = FirebaseAuth.getInstance()
        mFirestore = FirebaseFirestore.getInstance()
        mAdapter = ChartRecyclerAdapter()

        mLoadingDialog = AlertDialog.Builder(this@ChartActivity)
                .setView(R.layout.dialog_loading)
                .create()
        mLoadingDialog.show()

        chartRecyclerView.layoutManager = LinearLayoutManager(this@ChartActivity)
        chartRecyclerView.itemAnimator = DefaultItemAnimator()

        getMembers(mAuth.currentUser!!.email!!.substring(0, mAuth.currentUser!!.email!!.indexOf("@")))
    }

    private fun getMembers(city: String) {
        mFirestore.collection(FirestoreUtils.firestoreCollectionCities)
                .document(city)
                .collection(FirestoreUtils.firestoreCollectionMembers)
                .orderBy(FirestoreUtils.firestoreKeyName, Query.Direction.ASCENDING)
                .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                    if (firebaseFirestoreException != null) {
                        Log.e("MembersActivity", firebaseFirestoreException.toString())
                        return@addSnapshotListener
                    }

                    mMemberList = querySnapshot!!.toObjects(Member::class.java)
                    querySnapshot.forEachIndexed { index, queryDocumentSnapshot ->
                        mMemberList[index].id = queryDocumentSnapshot.id
                        mPresence[queryDocumentSnapshot.id] = arrayOf(0, 0, 0)
                    }

                    if (mMemberList.isNotEmpty())
                        getPresence(city)
                    else {
                        emptyView.visibility = View.VISIBLE
                        chartRecyclerView.visibility = View.INVISIBLE
                        mLoadingDialog.dismiss()
                    }
                }
    }

    private fun getPresence(city: String) {
        for (i in 0 until FirestoreUtils.meetingTypes.size) {
            val meetingType = FirestoreUtils.meetingTypes[i]
            mFirestore.collection(FirestoreUtils.firestoreCollectionCities)
                    .document(city)
                    .collection(FirestoreUtils.firestoreCollectionMeetings)
                    .document(meetingType)
                    .collection(FirestoreUtils.firestoreCollectionMeetings)
                    .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                        if (firebaseFirestoreException != null) {
                            Log.e("ChartActivity", firebaseFirestoreException.toString())
                            return@addSnapshotListener
                        }
                        mNumberOfMeetings[i] = querySnapshot!!.size()
                        querySnapshot.forEach { queryDocumentSnapshot ->
                            (queryDocumentSnapshot[FirestoreUtils.firestoreKeyAttendanceList] as ArrayList<String>).forEach {
                                mPresence[it]!![i] = mPresence[it]!![i] + 1
                            }
                        }
                    }
        }
        setDataToChart()
    }

    private fun setDataToChart() {
        mAdapter.setLists(mMemberList, mPresence, mNumberOfMeetings)
        chartRecyclerView.adapter = mAdapter
        mLoadingDialog.dismiss()
        chartRecyclerView.scrollToPosition(mMemberList.size - 1)
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
