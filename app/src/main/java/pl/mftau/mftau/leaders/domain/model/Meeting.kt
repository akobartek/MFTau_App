package pl.mftau.mftau.leaders.domain.model

import com.google.firebase.Timestamp

data class Meeting(
    var id: String = "",
    var name: String = "",
    var meetingType: MeetingType = MeetingType.FORMATION,
    var date: Timestamp = Timestamp.now(),
    var notes: String = "",
    var attendanceList: ArrayList<String> = arrayListOf(),
    var absenceList: HashMap<String, String> = HashMap()
)
