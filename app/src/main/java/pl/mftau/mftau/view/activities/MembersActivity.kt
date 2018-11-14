package pl.mftau.mftau.view.activities

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.core.app.NavUtils
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_members.*
import pl.mftau.mftau.R
import pl.mftau.mftau.model.Member
import pl.mftau.mftau.utils.FirestoreUtils.firestoreCollectionCities
import pl.mftau.mftau.utils.FirestoreUtils.firestoreCollectionMembers
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyName
import pl.mftau.mftau.view.adapters.MembersRecyclerAdapter

class MembersActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mFirestore: FirebaseFirestore
    private lateinit var mMembersQuery: Query
    private lateinit var mAdapter: MembersRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_members)
        setSupportActionBar(membersToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.WHITE
        }

        mAuth = FirebaseAuth.getInstance()
        mFirestore = FirebaseFirestore.getInstance()

        mAdapter = MembersRecyclerAdapter()

        membersRecyclerView.layoutManager = LinearLayoutManager(this@MembersActivity)
        membersRecyclerView.itemAnimator = DefaultItemAnimator()
        membersRecyclerView.adapter = mAdapter

        val city = mAuth.currentUser!!.email!!.substring(0, mAuth.currentUser!!.email!!.indexOf("@"))
        mMembersQuery = mFirestore.collection(firestoreCollectionCities)
                .document(city)
                .collection(firestoreCollectionMembers)
                .orderBy(firestoreKeyName, Query.Direction.ASCENDING)
        mMembersQuery.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            if (firebaseFirestoreException != null) {
                Log.e("MembersActivity", firebaseFirestoreException.toString())
                return@addSnapshotListener
            }

            val memberList = querySnapshot!!.toObjects(Member::class.java)
            querySnapshot.forEachIndexed { index, queryDocumentSnapshot ->
                memberList[index].id = queryDocumentSnapshot.id
            }
            mAdapter.setMemberList(memberList)

            loadingIndicator.visibility = View.INVISIBLE
            if (querySnapshot.isEmpty) {
                emptyView.visibility = View.VISIBLE
            } else {
                emptyView.visibility = View.INVISIBLE
            }
        }

        addMemberBtn.setOnClickListener { startActivity(Intent(this@MembersActivity, MemberEditorActivity::class.java)) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this@MembersActivity)
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
