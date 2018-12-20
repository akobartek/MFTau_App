package pl.mftau.mftau.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import pl.mftau.mftau.model.Member
import pl.mftau.mftau.model.repositories.FirebaseRepository
import pl.mftau.mftau.utils.FirestoreUtils
import java.util.*

class ChartViewModel(val app: Application) : AndroidViewModel(app) {

    private val mFirebaseRepository = FirebaseRepository(app)
    private val mFirestore = FirebaseFirestore.getInstance()
    private val mAuth = FirebaseAuth.getInstance()

    var members: List<Member>? = null
    var presence = HashMap<String, Array<Int>>()
    var numberOfMeetings = arrayOfNulls<Int>(3)


    fun getAllMember(): LiveData<List<Member>> = mFirebaseRepository.getAllMembers()

    fun getPresence(): LiveData<HashMap<String, Array<Int>>> {
        val mutableLiveData = MutableLiveData<HashMap<String, Array<Int>>>()
        for (i in 0 until FirestoreUtils.meetingTypes.size) {
            val meetingType = FirestoreUtils.meetingTypes[i]
            mFirestore.collection(FirestoreUtils.firestoreCollectionCities)
                    .document(mAuth.currentUser!!.email!!.substring(0, mAuth.currentUser!!.email!!.indexOf("@")))
                    .collection(FirestoreUtils.firestoreCollectionMeetings)
                    .document(meetingType)
                    .collection(FirestoreUtils.firestoreCollectionMeetings)
                    .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                        if (firebaseFirestoreException != null) {
                            Log.e("ChartViewModel", firebaseFirestoreException.toString())
                            return@addSnapshotListener
                        }
                        numberOfMeetings[i] = querySnapshot!!.size()
                        querySnapshot.forEach { queryDocumentSnapshot ->
                            (queryDocumentSnapshot[FirestoreUtils.firestoreKeyAttendanceList] as ArrayList<String>).forEach {
                                presence[it]!![i] = presence[it]!![i] + 1
                            }
                        }
                        mutableLiveData.value = presence
                    }
        }
        return mutableLiveData
    }
}