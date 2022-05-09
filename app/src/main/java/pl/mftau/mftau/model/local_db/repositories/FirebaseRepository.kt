package pl.mftau.mftau.model.local_db.repositories

import android.app.Activity
import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import pl.mftau.mftau.R
import pl.mftau.mftau.model.local_db.Meeting
import pl.mftau.mftau.model.local_db.Member
import pl.mftau.mftau.model.local_db.Retreat
import pl.mftau.mftau.utils.FirestoreUtils
import pl.mftau.mftau.view.fragments.PresenceListFragment
import java.io.InputStream

class FirebaseRepository(val app: Application) {

    private val mAuth = FirebaseAuth.getInstance()
    private val mFirestore = FirebaseFirestore.getInstance()
    private val mStorageRef = FirebaseStorage.getInstance().reference

    // region Retreats
    /**
     * Retreats methods.
     */

    fun getAllRetreats(): MutableLiveData<List<Retreat>> {
        val mutableLiveData = MutableLiveData<List<Retreat>>()
        mFirestore.collection(FirestoreUtils.firestoreCollectionRetreats)
            .orderBy(FirestoreUtils.firestoreKeyBeginDate, Query.Direction.ASCENDING)
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (firebaseFirestoreException != null) {
                    Log.e("FirebaseRepository", firebaseFirestoreException.toString())
                }

                val retreatList = querySnapshot!!.toObjects(Retreat::class.java)
                querySnapshot.forEachIndexed { index, queryDocumentSnapshot ->
                    retreatList[index].id = queryDocumentSnapshot.id
                }
                mutableLiveData.value = retreatList
            }
        return mutableLiveData
    }

    fun addRetreat(activity: Activity, retreatValues: HashMap<String, Any>) {
        mFirestore.collection(FirestoreUtils.firestoreCollectionRetreats)
            .add(retreatValues)
            .addOnSuccessListener {
                Toast.makeText(
                    activity, activity.getString(R.string.retreat_added), Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener {
                Toast.makeText(
                    activity, activity.getString(R.string.retreat_add_error), Toast.LENGTH_SHORT
                ).show()
            }
    }

    fun updateRetreat(activity: Activity, retreatId: String, retreatValues: HashMap<String, Any>) {
        mFirestore.collection(FirestoreUtils.firestoreCollectionRetreats)
            .document(retreatId)
            .set(retreatValues)
            .addOnSuccessListener {
                Toast.makeText(
                    activity, activity.getString(R.string.retreat_updated), Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener {
                Toast.makeText(
                    activity, activity.getString(R.string.retreat_update_error), Toast.LENGTH_SHORT
                ).show()
            }
    }

    fun deleteRetreat(activity: Activity, retreatId: String, withToast: Boolean) {
        mFirestore.collection(FirestoreUtils.firestoreCollectionRetreats)
            .document(retreatId)
            .delete()
            .addOnSuccessListener {
                if (withToast)
                    Toast.makeText(
                        activity,
                        activity.getString(R.string.retreat_delete_successfully),
                        Toast.LENGTH_SHORT
                    ).show()
            }
            .addOnFailureListener {
                if (withToast)
                    Toast.makeText(
                        activity, activity.getString(R.string.delete_error), Toast.LENGTH_SHORT
                    ).show()
            }
    }
    // endregion Retreats

    // region Members
    /**
     * Members methods.
     */

    fun getAllMembers(): MutableLiveData<List<Member>> {
        val mutableLiveData = MutableLiveData<List<Member>>()
        mFirestore.collection(FirestoreUtils.firestoreCollectionCities)
            .document(
                mAuth.currentUser!!.email!!.substring(
                    0, mAuth.currentUser!!.email!!.indexOf("@")
                )
            )
            .collection(FirestoreUtils.firestoreCollectionMembers)
            .orderBy(FirestoreUtils.firestoreKeyName, Query.Direction.ASCENDING)
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (firebaseFirestoreException != null) {
                    Log.e("FirebaseRepository", firebaseFirestoreException.toString())
                    return@addSnapshotListener
                }

                val memberList = querySnapshot!!.toObjects(Member::class.java)
                querySnapshot.forEachIndexed { index, queryDocumentSnapshot ->
                    memberList[index].id = queryDocumentSnapshot.id
                    memberList[index].isResponsible =
                        queryDocumentSnapshot[FirestoreUtils.firestoreKeyIsResponsible] as Boolean
                }
                mutableLiveData.value = memberList
            }
        return mutableLiveData
    }

    fun getMemberById(memberId: String): MutableLiveData<Member> {
        val mutableLiveData = MutableLiveData<Member>()
        mFirestore.collection(FirestoreUtils.firestoreCollectionCities)
            .document(
                mAuth.currentUser!!.email!!.substring(
                    0,
                    mAuth.currentUser!!.email!!.indexOf("@")
                )
            )
            .collection(FirestoreUtils.firestoreCollectionMembers)
            .document(memberId)
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (firebaseFirestoreException != null) {
                    Log.e("FirebaseRepository", firebaseFirestoreException.toString())
                    return@addSnapshotListener
                }

                val member = querySnapshot!!.toObject(Member::class.java)
                member?.id = memberId
                member?.isResponsible =
                    querySnapshot[FirestoreUtils.firestoreKeyIsResponsible] as Boolean
                member?.let { mutableLiveData.value = it }
            }
        return mutableLiveData
    }

    fun addMember(activity: Activity, memberValues: HashMap<String, Any>, filePath: InputStream?) {
        mFirestore.collection(FirestoreUtils.firestoreCollectionCities)
            .document(
                mAuth.currentUser!!.email!!.substring(
                    0, mAuth.currentUser!!.email!!.indexOf("@")
                )
            )
            .collection(FirestoreUtils.firestoreCollectionMembers)
            .add(memberValues)
            .addOnSuccessListener { documentReference ->
                if (filePath != null) {
                    val dialog = AlertDialog.Builder(activity)
                        .setView(R.layout.dialog_loading)
                        .setCancelable(false)
                        .create()
                    dialog.show()
                    putPhoto(activity, documentReference.id, filePath, dialog)
                } else {
                    Toast.makeText(
                        activity, activity.getString(R.string.member_added), Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(
                    activity, activity.getString(R.string.member_add_error), Toast.LENGTH_SHORT
                ).show()
            }
    }

    fun updateMemberPhoto(activity: Activity, memberId: String, filePath: InputStream?) {
        val dialog = AlertDialog.Builder(activity)
            .setView(R.layout.dialog_loading)
            .setCancelable(false)
            .create()
        dialog.show()

        mStorageRef.child("${FirestoreUtils.firestoreCollectionMembers}/$memberId.jpg")
            .delete()
            .addOnSuccessListener {
                putPhoto(activity, memberId, filePath, dialog)
            }
            .addOnFailureListener {
                putPhoto(activity, memberId, filePath, dialog)
            }
    }

    fun updateMember(
        activity: Activity,
        memberId: String,
        memberValues: HashMap<String, Any>,
        filePath: InputStream?
    ) {
        mFirestore.collection(FirestoreUtils.firestoreCollectionCities)
            .document(
                mAuth.currentUser!!.email!!.substring(
                    0, mAuth.currentUser!!.email!!.indexOf("@")
                )
            )
            .collection(FirestoreUtils.firestoreCollectionMembers)
            .document(memberId)
            .set(memberValues)
            .addOnSuccessListener {
                if (filePath != null) {
                    val dialog = AlertDialog.Builder(activity)
                        .setView(R.layout.dialog_loading)
                        .setCancelable(false)
                        .create()
                    dialog.show()

                    mStorageRef.child("$FirestoreUtils.firestoreCollectionMembers/$memberId.jpg")
                        .delete()
                        .addOnSuccessListener {
                            putPhoto(activity, memberId, filePath, dialog)
                        }
                        .addOnFailureListener {
                            putPhoto(activity, memberId, filePath, dialog)
                        }
                } else {
                    Toast.makeText(
                        activity, activity.getString(R.string.member_added), Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(
                    activity, activity.getString(R.string.member_update_error), Toast.LENGTH_SHORT
                ).show()
            }
    }


    private fun putPhoto(
        activity: Activity, memberId: String, filePath: InputStream?, dialog: AlertDialog
    ) {
        mStorageRef.child("${FirestoreUtils.firestoreCollectionMembers}/$memberId.jpg")
            .putStream(filePath!!)
            .addOnSuccessListener {
                dialog.dismiss()
                Toast.makeText(
                    activity,
                    activity.getString(R.string.member_with_photo_saved),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
            .addOnFailureListener {
                dialog.dismiss()
                Toast.makeText(
                    activity, activity.getString(R.string.member_photo_error), Toast.LENGTH_SHORT
                ).show()
            }
    }

    fun deleteMember(activity: Activity, memberId: String) {
        mFirestore.collection(FirestoreUtils.firestoreCollectionCities)
            .document(
                mAuth.currentUser!!.email!!.substring(
                    0, mAuth.currentUser!!.email!!.indexOf("@")
                )
            )
            .collection(FirestoreUtils.firestoreCollectionMembers)
            .document(memberId)
            .delete()
            .addOnSuccessListener {
                mStorageRef.child("${FirestoreUtils.firestoreCollectionMembers}/$memberId.jpg")
                    .delete()
                    .addOnCompleteListener {
                        Toast.makeText(
                            activity,
                            activity.getString(R.string.member_delete_successfully),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
            .addOnFailureListener {
                Toast.makeText(
                    activity, activity.getString(R.string.delete_error), Toast.LENGTH_SHORT
                ).show()
            }
    }
    //endregion Members

    //region Meetings
    /**
     * Meetings methods.
     */

    fun getAllMeetings(meetingType: Int): MutableLiveData<List<Meeting>> {
        val mutableLiveData = MutableLiveData<List<Meeting>>()
        mFirestore.collection(FirestoreUtils.firestoreCollectionCities)
            .document(
                mAuth.currentUser!!.email!!.substring(
                    0, mAuth.currentUser!!.email!!.indexOf("@")
                )
            )
            .collection(FirestoreUtils.firestoreCollectionMeetings)
            .document(FirestoreUtils.meetingTypes[meetingType])
            .collection(FirestoreUtils.firestoreCollectionMeetings)
            .orderBy(FirestoreUtils.firestoreKeyDate, Query.Direction.ASCENDING)
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (firebaseFirestoreException != null) {
                    Log.e("MeetingsListFragment", firebaseFirestoreException.toString())
                    return@addSnapshotListener
                }

                val meetingList = querySnapshot!!.toObjects(Meeting::class.java)
                querySnapshot.forEachIndexed { index, queryDocumentSnapshot ->
                    meetingList[index].id = queryDocumentSnapshot.id
                }
                mutableLiveData.value = meetingList
            }
        return mutableLiveData
    }

    fun getMeetingById(meetingId: String, meetingType: Int): MutableLiveData<Meeting> {
        val mutableLiveData = MutableLiveData<Meeting>()
        mFirestore.collection(FirestoreUtils.firestoreCollectionCities)
            .document(
                mAuth.currentUser!!.email!!.substring(
                    0, mAuth.currentUser!!.email!!.indexOf("@")
                )
            )
            .collection(FirestoreUtils.firestoreCollectionMeetings)
            .document(FirestoreUtils.meetingTypes[meetingType])
            .collection(FirestoreUtils.firestoreCollectionMeetings)
            .document(meetingId)
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (firebaseFirestoreException != null) {
                    Log.e("FirebaseRepository", firebaseFirestoreException.toString())
                    return@addSnapshotListener
                }

                val meeting = querySnapshot!!.toObject(Meeting::class.java)
                meeting?.id = meetingId
                meeting?.let { mutableLiveData.value = it }
            }
        return mutableLiveData
    }

    fun addMeeting(activity: Activity, meetingType: Int, meetingValues: HashMap<String, Any>) {
        mFirestore.collection(FirestoreUtils.firestoreCollectionCities)
            .document(
                mAuth.currentUser!!.email!!.substring(
                    0, mAuth.currentUser!!.email!!.indexOf("@")
                )
            )
            .collection(FirestoreUtils.firestoreCollectionMeetings)
            .document(FirestoreUtils.meetingTypes[meetingType])
            .collection(FirestoreUtils.firestoreCollectionMeetings)
            .add(meetingValues)
            .addOnSuccessListener {
                Toast.makeText(
                    activity, activity.getString(R.string.meeting_saved), Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener {
                Toast.makeText(
                    activity, activity.getString(R.string.meeting_add_error), Toast.LENGTH_SHORT
                ).show()
            }
    }

    fun addMeetingWithAttendanceList(
        activity: Activity,
        meetingType: Int,
        meetingValues: HashMap<String, Any>
    ) {
        mFirestore.collection(FirestoreUtils.firestoreCollectionCities)
            .document(
                mAuth.currentUser!!.email!!.substring(
                    0, mAuth.currentUser!!.email!!.indexOf("@")
                )
            )
            .collection(FirestoreUtils.firestoreCollectionMeetings)
            .document(FirestoreUtils.meetingTypes[meetingType])
            .collection(FirestoreUtils.firestoreCollectionMeetings)
            .add(meetingValues)
            .addOnSuccessListener {
                Toast.makeText(
                    activity, activity.getString(R.string.meeting_saved), Toast.LENGTH_SHORT
                ).show()

                val mNewMeetingId = it.id
                val meetingDocument =
                    mFirestore.collection(FirestoreUtils.firestoreCollectionCities)
                        .document(
                            mAuth.currentUser!!.email!!.substring(
                                0, mAuth.currentUser!!.email!!.indexOf("@")
                            )
                        )
                        .collection(FirestoreUtils.firestoreCollectionMeetings)
                        .document(FirestoreUtils.meetingTypes[meetingType])
                        .collection(FirestoreUtils.firestoreCollectionMeetings)
                        .document(mNewMeetingId)

                meetingDocument.update(
                    FirestoreUtils.firestoreKeyAttendanceList,
                    meetingValues[FirestoreUtils.firestoreKeyAttendanceList]
                )
                meetingDocument.update(
                    FirestoreUtils.firestoreKeyAbsenceList,
                    meetingValues[FirestoreUtils.firestoreKeyAbsenceList]
                )
            }
            .addOnFailureListener {
                Toast.makeText(
                    activity, activity.getString(R.string.meeting_add_error), Toast.LENGTH_SHORT
                ).show()
            }
    }

    fun updateMeeting(
        activity: Activity, meetingId: String, meetingType: Int, meetingValues: HashMap<String, Any>
    ) {
        mFirestore.collection(FirestoreUtils.firestoreCollectionCities)
            .document(
                mAuth.currentUser!!.email!!.substring(
                    0, mAuth.currentUser!!.email!!.indexOf("@")
                )
            )
            .collection(FirestoreUtils.firestoreCollectionMeetings)
            .document(FirestoreUtils.meetingTypes[meetingType])
            .collection(FirestoreUtils.firestoreCollectionMeetings)
            .document(meetingId)
            .set(meetingValues)
            .addOnSuccessListener {
                Toast.makeText(
                    activity, activity.getString(R.string.meeting_updated), Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener {
                Toast.makeText(
                    activity, activity.getString(R.string.meeting_update_error), Toast.LENGTH_SHORT
                ).show()
            }
    }

    fun updateAttendanceList(
        activity: Activity, meetingId: String, meetingType: Int,
        attendanceList: ArrayList<String>, absenceList: HashMap<String, String>
    ) {
        val meetingDocument = mFirestore.collection(FirestoreUtils.firestoreCollectionCities)
            .document(
                mAuth.currentUser!!.email!!.substring(
                    0, mAuth.currentUser!!.email!!.indexOf("@")
                )
            )
            .collection(FirestoreUtils.firestoreCollectionMeetings)
            .document(FirestoreUtils.meetingTypes[meetingType])
            .collection(FirestoreUtils.firestoreCollectionMeetings)
            .document(meetingId)

        meetingDocument.update(FirestoreUtils.firestoreKeyAttendanceList, attendanceList)
        meetingDocument.update(FirestoreUtils.firestoreKeyAbsenceList, absenceList)
            .addOnSuccessListener {
                Toast.makeText(
                    activity, activity.getString(R.string.meeting_updated), Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener {
                Toast.makeText(
                    activity, activity.getString(R.string.meeting_update_error), Toast.LENGTH_SHORT
                ).show()
            }
    }

    fun deleteMeeting(activity: Activity, meetingId: String, meetingType: Int) {
        mFirestore.collection(FirestoreUtils.firestoreCollectionCities)
            .document(
                mAuth.currentUser!!.email!!.substring(
                    0, mAuth.currentUser!!.email!!.indexOf("@")
                )
            )
            .collection(FirestoreUtils.firestoreCollectionMeetings)
            .document(FirestoreUtils.meetingTypes[meetingType])
            .collection(FirestoreUtils.firestoreCollectionMeetings)
            .document(meetingId)
            .delete()
            .addOnSuccessListener {
                Toast.makeText(
                    activity,
                    activity.getString(R.string.meeting_delete_successfully),
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener {
                Toast.makeText(
                    activity,
                    activity.getString(R.string.delete_error),
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    fun clearMeetings() {
        for (meetingType in FirestoreUtils.meetingTypes) {
            mFirestore.collection(FirestoreUtils.firestoreCollectionCities)
                .document(
                    mAuth.currentUser!!.email!!.substring(
                        0, mAuth.currentUser!!.email!!.indexOf("@")
                    )
                )
                .collection(FirestoreUtils.firestoreCollectionMeetings)
                .document(meetingType)
                .collection(FirestoreUtils.firestoreCollectionMeetings)
                .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                    if (firebaseFirestoreException != null) {
                        Log.e("MeetingsActivity", firebaseFirestoreException.toString())
                        return@addSnapshotListener
                    }

                    querySnapshot!!.forEach {
                        mFirestore.collection(FirestoreUtils.firestoreCollectionCities)
                            .document(
                                mAuth.currentUser!!.email!!.substring(
                                    0, mAuth.currentUser!!.email!!.indexOf("@")
                                )
                            )
                            .collection(FirestoreUtils.firestoreCollectionMeetings)
                            .document(meetingType)
                            .collection(FirestoreUtils.firestoreCollectionMeetings)
                            .document(it.id)
                            .delete()
                    }
                }
        }
    }

    fun getPresence(presence: HashMap<String, Array<Int>>): MutableLiveData<HashMap<String, Array<Int>>> {
        val mutableLiveData = MutableLiveData<HashMap<String, Array<Int>>>()
        for (i in FirestoreUtils.meetingTypes.indices) {
            val meetingType = FirestoreUtils.meetingTypes[i]
            mFirestore.collection(FirestoreUtils.firestoreCollectionCities)
                .document(
                    mAuth.currentUser!!.email!!.substring(
                        0, mAuth.currentUser!!.email!!.indexOf("@")
                    )
                )
                .collection(FirestoreUtils.firestoreCollectionMeetings)
                .document(meetingType)
                .collection(FirestoreUtils.firestoreCollectionMeetings)
                .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                    if (firebaseFirestoreException != null) {
                        Log.e("ChartViewModel", firebaseFirestoreException.toString())
                        return@addSnapshotListener
                    }

                    PresenceListFragment.numberOfMeetings[i] = querySnapshot!!.size()
                    querySnapshot.forEach { queryDocumentSnapshot ->
                        (queryDocumentSnapshot[FirestoreUtils.firestoreKeyAttendanceList] as ArrayList<*>).forEach {
                            if (presence[it] != null)
                                presence[it]!![i] = presence[it]!![i] + 1
                        }
                    }
                    mutableLiveData.value = presence
                }
        }
        return mutableLiveData
    }
    // endregion Meetings
}