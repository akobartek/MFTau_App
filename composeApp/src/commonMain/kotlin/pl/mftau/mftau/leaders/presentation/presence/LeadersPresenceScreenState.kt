package pl.mftau.mftau.leaders.presentation.presence

import pl.mftau.mftau.leaders.domain.model.Meeting
import pl.mftau.mftau.leaders.domain.model.MeetingType
import pl.mftau.mftau.leaders.domain.model.PersonPresence

data class LeadersPresenceScreenState(
    val meetings: Map<MeetingType, List<Meeting>> = mapOf(),
    val presence: List<PersonPresence> = listOf(),
    val isLoading: Boolean = true,
    val detailsPersonSelected: PersonPresence? = null,
    val showJustified: Boolean = false,
)