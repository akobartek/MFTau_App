package pl.mftau.mftau.view.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_meeting.*
import pl.mftau.mftau.R
import pl.mftau.mftau.model.Meeting
import pl.mftau.mftau.view.adapters.MeetingsRecyclerAdapter
import pl.mftau.mftau.utils.FirestoreUtils.firestoreCollectionCities
import pl.mftau.mftau.utils.FirestoreUtils.firestoreCollectionMeetings
import pl.mftau.mftau.utils.FirestoreUtils.firestoreKeyDate
import pl.mftau.mftau.utils.FirestoreUtils.meetingTypes


class MeetingsFragment : Fragment() {

    companion object {
        fun newInstance(meetingType: Int): MeetingsFragment {
            return MeetingsFragment().apply {
                arguments = Bundle().apply {
                    putInt("meetingType", meetingType)
                }
            }
        }
    }

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mFirestore: FirebaseFirestore
    private lateinit var mMembersQuery: Query
    private lateinit var mAdapter: MeetingsRecyclerAdapter

    private var meetingType: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_meeting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        meetingType = arguments!!.getInt("meetingType")

        mAuth = FirebaseAuth.getInstance()
        mFirestore = FirebaseFirestore.getInstance()
        mAdapter = MeetingsRecyclerAdapter()

        meetingsRecyclerView.layoutManager = LinearLayoutManager(view.context)
        meetingsRecyclerView.itemAnimator = DefaultItemAnimator()
        meetingsRecyclerView.adapter = mAdapter
        meetingsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val addMeetingBtn = activity!!.findViewById<FloatingActionButton>(R.id.addMeetingBtn)
                if (dy < 0 && !addMeetingBtn.isShown)
                    addMeetingBtn.show()
                else if (dy > 0 && addMeetingBtn.isShown)
                    addMeetingBtn.hide()
            }
        })

        val city = mAuth.currentUser!!.email!!.substring(0, mAuth.currentUser!!.email!!.indexOf("@"))
        mMembersQuery = mFirestore.collection(firestoreCollectionCities)
                .document(city)
                .collection(firestoreCollectionMeetings)
                .document(meetingTypes[meetingType])
                .collection(firestoreCollectionMeetings)
                .orderBy(firestoreKeyDate, Query.Direction.ASCENDING)
        mMembersQuery.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            if (firebaseFirestoreException != null) {
                Log.e("MeetingsFragment", firebaseFirestoreException.toString())
                return@addSnapshotListener
            }

            val meetingList = querySnapshot!!.toObjects(Meeting::class.java)
            querySnapshot.forEachIndexed { index, queryDocumentSnapshot ->
                meetingList[index].id = queryDocumentSnapshot.id
            }
            mAdapter.setMeetingList(meetingList)

            loadingIndicator.animate().alpha(0f).duration = 100
            if (querySnapshot.isEmpty) {
                emptyView.visibility = View.VISIBLE
            } else {
                emptyView.visibility = View.INVISIBLE
            }
        }
    }
}
