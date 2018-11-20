package pl.mftau.mftau.viewmodel

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.google.firebase.Timestamp
import pl.mftau.mftau.model.Meeting
import pl.mftau.mftau.model.Member
import pl.mftau.mftau.model.repositories.FirebaseRepository
import pl.mftau.mftau.model.utils.FirestoreUtils.firestoreKeyAttendanceList
import pl.mftau.mftau.model.utils.FirestoreUtils.firestoreKeyDate
import pl.mftau.mftau.model.utils.FirestoreUtils.firestoreKeyMeetingType
import pl.mftau.mftau.model.utils.FirestoreUtils.firestoreKeyName
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class MeetingEditorViewModel(val app: Application) : AndroidViewModel(app) {

    private val mFirebaseRepository = FirebaseRepository(app)

    var meeting: Meeting? = null
    var date: Date? = null
    var attendanceList = arrayListOf<String>()


    fun getAllMembers(): LiveData<List<Member>> = mFirebaseRepository.getAllMembers()

    fun createMeetingValues(meetingName: String, meetingType: Int): HashMap<String, Any> {
        val meetingValues = HashMap<String, Any>()
        meetingValues[firestoreKeyName] = meetingName
        meetingValues[firestoreKeyDate] = Timestamp(date)
        meetingValues[firestoreKeyMeetingType] = meetingType
        meetingValues[firestoreKeyAttendanceList] = attendanceList
        return meetingValues
    }

    fun addMeeting(activity: Activity, meetingType: Int, meetingValues: HashMap<String, Any>, attendanceList: ArrayList<String>?) =
            mFirebaseRepository.addMeeting(activity, meetingType, meetingValues, attendanceList)

    fun updateMeeting(activity: Activity, meetingType: Int, meetingValues: HashMap<String, Any>) {
        if (meeting != null) {
            mFirebaseRepository.updateMeeting(activity, meeting!!.id, meetingType, meetingValues)
        }
    }

    fun updateAttendanceList(meetingType: Int) {
        if (meeting != null) {
            mFirebaseRepository.updateAttendanceList(meeting!!.id, meetingType, attendanceList)
        }
    }

    fun deleteMeeting(activity: Activity) {
        if (meeting != null) {
            mFirebaseRepository.deleteMeeting(activity, meeting!!.id, meeting!!.meetingType)
        }
    }
}