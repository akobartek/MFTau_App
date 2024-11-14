package pl.mftau.mftau.leaders.presentation.meetings

import pl.mftau.mftau.leaders.domain.model.Meeting
import pl.mftau.mftau.leaders.domain.model.MeetingType
import pl.mftau.mftau.leaders.domain.model.Person

data class LeadersMeetingsScreenState(
    val meetings: Map<MeetingType, List<Meeting>> = mapOf(),
    val people: List<Person> = listOf(),
    val isLoading: Boolean = true,
    val meetingEditorVisible: Boolean = false,
    val meetingToEdit: Meeting? = null,
)