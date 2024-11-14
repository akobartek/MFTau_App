package pl.mftau.mftau.leaders.domain.model

import dev.gitlive.firebase.firestore.Timestamp

data class Meeting(
    val id: String = "",
    val name: String = "",
    val meetingType: MeetingType = MeetingType.FORMATION,
    val date: Timestamp = Timestamp.now(),
    val notes: String = "",
    val attendanceList: List<String> = listOf(),
    val absenceList: Map<String, String> = mapOf(),
)
