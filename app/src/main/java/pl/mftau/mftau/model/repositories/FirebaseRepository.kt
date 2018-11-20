package pl.mftau.mftau.model.repositories

import android.app.Activity
import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import pl.mftau.mftau.R
import pl.mftau.mftau.model.Meeting
import pl.mftau.mftau.model.Member
import pl.mftau.mftau.model.utils.FirestoreUtils
import java.io.InputStream

class FirebaseRepository(val app: Application) {

    private val mAuth = FirebaseAuth.getInstance()
    private val mFirestore = FirebaseFirestore.getInstance()
    private val mStorageRef = FirebaseStorage.getInstance().reference


    /**
     * Members methods.
     */

    fun getAllMembers(): LiveData<List<Member>> {
        val mutableLiveData = MutableLiveData<List<Member>>()
        mFirestore.collection(FirestoreUtils.firestoreCollectionCities)
                .document(mAuth.currentUser!!.email!!.substring(0, mAuth.currentUser!!.email!!.indexOf("@")))
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
                        memberList[index].isResponsible = queryDocumentSnapshot[FirestoreUtils.firestoreKeyIsResponsible] as Boolean
                    }
                    mutableLiveData.value = memberList
                }
        return mutableLiveData
    }

    fun addMember(activity: Activity, memberValues: HashMap<String, Any>, filePath: InputStream?) {
        mFirestore.collection(FirestoreUtils.firestoreCollectionCities)
                .document(mAuth.currentUser!!.email!!.substring(0, mAuth.currentUser!!.email!!.indexOf("@")))
                .collection(FirestoreUtils.firestoreCollectionMembers)
                .add(memberValues)
                .addOnSuccessListener { documentReference ->
                    if (filePath != null) {
                        val dialog = AlertDialog.Builder(activity)
                                .setView(R.layout.dialog_loading)
                                .create()
                        dialog.show()
                        putPhoto(activity, documentReference.id, filePath, dialog)
                    } else {
                        Toast.makeText(activity, activity.getString(R.string.member_added), Toast.LENGTH_SHORT).show()
                        activity.finish()
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(activity, activity.getString(R.string.member_add_error), Toast.LENGTH_SHORT).show()
                }
    }

    fun updateMemberPhoto(activity: Activity, memberId: String, filePath: InputStream?) {
        val dialog = AlertDialog.Builder(activity)
                .setView(R.layout.dialog_loading)
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

    fun updateMember(activity: Activity, memberId: String, memberValues: HashMap<String, Any>, filePath: InputStream?) {
        mFirestore.collection(FirestoreUtils.firestoreCollectionCities)
                .document(mAuth.currentUser!!.email!!.substring(0, mAuth.currentUser!!.email!!.indexOf("@")))
                .collection(FirestoreUtils.firestoreCollectionMembers)
                .document(memberId)
                .set(memberValues)
                .addOnSuccessListener {
                    if (filePath != null) {
                        val dialog = AlertDialog.Builder(activity)
                                .setView(R.layout.dialog_loading)
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
                        Toast.makeText(activity, activity.getString(R.string.member_added), Toast.LENGTH_SHORT).show()
                        activity.finish()
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(activity, activity.getString(R.string.member_update_error), Toast.LENGTH_SHORT).show()
                }
    }


    private fun putPhoto(activity: Activity, memberId: String, filePath: InputStream?, dialog: AlertDialog) {
        mStorageRef.child("${FirestoreUtils.firestoreCollectionMembers}/$memberId.jpg")
                .putStream(filePath!!)
                .addOnSuccessListener {
                    dialog.dismiss()
                    Toast.makeText(activity, activity.getString(R.string.member_with_photo_saved), Toast.LENGTH_SHORT).show()
                    activity.finish()
                }
                .addOnFailureListener {
                    dialog.dismiss()
                    Toast.makeText(activity, activity.getString(R.string.member_photo_error), Toast.LENGTH_SHORT).show()
                    activity.finish()
                }
    }

    fun deleteMember(activity: Activity, memberId: String) {
        mFirestore.collection(FirestoreUtils.firestoreCollectionCities)
                .document(mAuth.currentUser!!.email!!.substring(0, mAuth.currentUser!!.email!!.indexOf("@")))
                .collection(FirestoreUtils.firestoreCollectionMembers)
                .document(memberId)
                .delete()
                .addOnSuccessListener {
                    mStorageRef.child("${FirestoreUtils.firestoreCollectionMembers}/$memberId.jpg")
                            .delete()
                            .addOnCompleteListener {
                                Toast.makeText(activity, activity.getString(R.string.member_delete_successfully), Toast.LENGTH_SHORT).show()
                            }
                }
                .addOnFailureListener {
                    Toast.makeText(activity, activity.getString(R.string.delete_error), Toast.LENGTH_SHORT).show()
                }
    }


    /**
     * Meetings methods.
     */

    fun getAllMeetings(meetingType: Int): MutableLiveData<List<Meeting>> {
        val mutableLiveData = MutableLiveData<List<Meeting>>()
        mFirestore.collection(FirestoreUtils.firestoreCollectionCities)
                .document(mAuth.currentUser!!.email!!.substring(0, mAuth.currentUser!!.email!!.indexOf("@")))
                .collection(FirestoreUtils.firestoreCollectionMeetings)
                .document(FirestoreUtils.meetingTypes[meetingType])
                .collection(FirestoreUtils.firestoreCollectionMeetings)
                .orderBy(FirestoreUtils.firestoreKeyDate, Query.Direction.ASCENDING)
                .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                    if (firebaseFirestoreException != null) {
                        Log.e("MeetingsFragment", firebaseFirestoreException.toString())
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

    fun addMeeting(activity: Activity, meetingType: Int, meetingValues: HashMap<String, Any>, attendanceList: ArrayList<String>?) {
        mFirestore.collection(FirestoreUtils.firestoreCollectionCities)
                .document(mAuth.currentUser!!.email!!.substring(0, mAuth.currentUser!!.email!!.indexOf("@")))
                .collection(FirestoreUtils.firestoreCollectionMeetings)
                .document(FirestoreUtils.meetingTypes[meetingType])
                .collection(FirestoreUtils.firestoreCollectionMeetings)
                .add(meetingValues)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(activity, activity.getString(R.string.meeting_saved), Toast.LENGTH_SHORT).show()

                    if (attendanceList != null) {
                        val mNewMeetingId = documentReference.id
                        mFirestore.collection(FirestoreUtils.firestoreCollectionCities)
                                .document(mAuth.currentUser!!.email!!.substring(0, mAuth.currentUser!!.email!!.indexOf("@")))
                                .collection(FirestoreUtils.firestoreCollectionMeetings)
                                .document(FirestoreUtils.meetingTypes[meetingType])
                                .collection(FirestoreUtils.firestoreCollectionMeetings)
                                .document(mNewMeetingId)
                                .update(FirestoreUtils.firestoreKeyAttendanceList, attendanceList)
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(activity, activity.getString(R.string.meeting_add_error), Toast.LENGTH_SHORT).show()
                }
        activity.finish()
    }

    fun updateMeeting(activity: Activity, meetingId: String, meetingType: Int, meetingValues: HashMap<String, Any>) {
        mFirestore.collection(FirestoreUtils.firestoreCollectionCities)
                .document(mAuth.currentUser!!.email!!.substring(0, mAuth.currentUser!!.email!!.indexOf("@")))
                .collection(FirestoreUtils.firestoreCollectionMeetings)
                .document(FirestoreUtils.meetingTypes[meetingType])
                .collection(FirestoreUtils.firestoreCollectionMeetings)
                .document(meetingId)
                .set(meetingValues)
                .addOnSuccessListener {
                    Toast.makeText(activity, activity.getString(R.string.meeting_updated), Toast.LENGTH_SHORT).show()
                    activity.finish()
                }
                .addOnFailureListener {
                    Toast.makeText(activity, activity.getString(R.string.meeting_update_error), Toast.LENGTH_SHORT).show()
                }
    }

    fun updateAttendanceList(meetingId: String, meetingType: Int, attendanceList: ArrayList<String>) {
        mFirestore.collection(FirestoreUtils.firestoreCollectionCities)
                .document(mAuth.currentUser!!.email!!.substring(0, mAuth.currentUser!!.email!!.indexOf("@")))
                .collection(FirestoreUtils.firestoreCollectionMeetings)
                .document(FirestoreUtils.meetingTypes[meetingType])
                .collection(FirestoreUtils.firestoreCollectionMeetings)
                .document(meetingId)
                .update(FirestoreUtils.firestoreKeyAttendanceList, attendanceList)
    }

    fun deleteMeeting(activity: Activity, meetingId: String, meetingType: Int) {
        mFirestore.collection(FirestoreUtils.firestoreCollectionCities)
                .document(mAuth.currentUser!!.email!!.substring(0, mAuth.currentUser!!.email!!.indexOf("@")))
                .collection(FirestoreUtils.firestoreCollectionMeetings)
                .document(FirestoreUtils.meetingTypes[meetingType])
                .collection(FirestoreUtils.firestoreCollectionMeetings)
                .document(meetingId)
                .delete()
                .addOnSuccessListener {
                    Toast.makeText(activity, activity.getString(R.string.meeting_delete_successfully), Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(activity, activity.getString(R.string.delete_error), Toast.LENGTH_SHORT).show()
                }
    }

    fun clearMeetings() {
        for (meetingType in FirestoreUtils.meetingTypes) {
            mFirestore.collection(FirestoreUtils.firestoreCollectionCities)
                    .document(mAuth.currentUser!!.email!!.substring(0, mAuth.currentUser!!.email!!.indexOf("@")))
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
                                    .document(mAuth.currentUser!!.email!!.substring(0, mAuth.currentUser!!.email!!.indexOf("@")))
                                    .collection(FirestoreUtils.firestoreCollectionMeetings)
                                    .document(meetingType)
                                    .collection(FirestoreUtils.firestoreCollectionMeetings)
                                    .document(it.id)
                                    .delete()
                        }
                    }
        }
    }
}