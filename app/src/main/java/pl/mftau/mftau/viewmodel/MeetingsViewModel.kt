package pl.mftau.mftau.viewmodel

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import pl.mftau.mftau.model.local_db.Meeting
import pl.mftau.mftau.model.local_db.Member
import pl.mftau.mftau.model.local_db.repositories.FirebaseRepository
import java.util.ArrayList

class MeetingsViewModel(val app: Application) : AndroidViewModel(app) {

    private val mFirebaseRepository = FirebaseRepository(app)

    fun getAllMembers(): LiveData<List<Member>> = mFirebaseRepository.getAllMembers()

    fun getAllMeetings(meetingType: Int): LiveData<List<Meeting>> =
        mFirebaseRepository.getAllMeetings(meetingType)

//    fun getMeetingById(meetingId: String, meetingType: Int): LiveData<Meeting> =
//            mFirebaseRepository.getMeetingById(meetingId, meetingType)

    fun clearMeetings() = mFirebaseRepository.clearMeetings()

    fun addMeeting(activity: Activity, meetingType: Int, meetingValues: HashMap<String, Any>) =
        mFirebaseRepository.addMeeting(activity, meetingType, meetingValues)

    fun addMeetingWithAttendanceList(
        activity: Activity, meetingType: Int, meetingValues: HashMap<String, Any>
    ) = mFirebaseRepository.addMeetingWithAttendanceList(activity, meetingType, meetingValues)

    fun updateMeeting(
        activity: Activity, meetingId: String, meetingType: Int, meetingValues: HashMap<String, Any>
    ) = mFirebaseRepository.updateMeeting(activity, meetingId, meetingType, meetingValues)

    fun updateAttendanceList(
        activity: Activity, meetingId: String, meetingType: Int,
        attendanceList: ArrayList<String>, absenceList: HashMap<String, String>
    ) = mFirebaseRepository.updateAttendanceList(
        activity, meetingId, meetingType, attendanceList, absenceList
    )

    fun deleteMeeting(activity: Activity, meetingId: String, meetingType: Int) =
        mFirebaseRepository.deleteMeeting(activity, meetingId, meetingType)

    fun getPresence(presence: HashMap<String, Array<Int>>): LiveData<HashMap<String, Array<Int>>> =
        mFirebaseRepository.getPresence(presence)
}