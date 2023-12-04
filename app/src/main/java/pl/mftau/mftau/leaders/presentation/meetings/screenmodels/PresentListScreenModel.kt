package pl.mftau.mftau.leaders.presentation.meetings.screenmodels

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.common.data.PreferencesRepository
import pl.mftau.mftau.leaders.domain.model.InvalidUserException
import pl.mftau.mftau.leaders.domain.model.Meeting
import pl.mftau.mftau.leaders.domain.model.MeetingType
import pl.mftau.mftau.leaders.domain.model.PersonPresence
import pl.mftau.mftau.leaders.domain.repository.MeetingsRepository
import pl.mftau.mftau.leaders.domain.repository.PeopleRepository

class PresentListScreenModel(
    private val meetingsRepository: MeetingsRepository,
    private val peopleRepository: PeopleRepository,
    private val preferencesRepository: PreferencesRepository
) : StateScreenModel<PresentListScreenModel.PresenceListState>(PresenceListState()) {

    data class PresenceListState(
        val meetings: Map<MeetingType, List<Meeting>> = mapOf(),
        val presence: List<PersonPresence> = listOf(),
        val isLoading: Boolean = true,
        val detailsPersonSelected: PersonPresence? = null,
        val showJustified: Boolean = false
    ) {
        override fun equals(other: Any?): Boolean {
            if (other is MeetingsListScreenModel.MeetingsListState && (meetings !== other.meetings || presence !== other.people))
                return false
            return super.equals(other)
        }

        override fun hashCode(): Int {
            var result = meetings.hashCode()
            result = 31 * result + presence.hashCode()
            result = 31 * result + isLoading.hashCode()
            result = 31 * result + (detailsPersonSelected?.hashCode() ?: 0)
            result = 31 * result + showJustified.hashCode()
            return result
        }
    }

    init {
        screenModelScope.launch(Dispatchers.IO) {
            try {
                peopleRepository.people
                    .combine(meetingsRepository.meetings) { p, m -> Pair(p, m) }
                    .collect { (people, meetings) ->
                        val map = hashMapOf<MeetingType, List<Meeting>>()
                        map[MeetingType.FORMATION] =
                            meetings.filter { it.meetingType == MeetingType.FORMATION }
                        map[MeetingType.PRAYERFUL] =
                            meetings.filter { it.meetingType == MeetingType.PRAYERFUL }
                        map[MeetingType.OTHER] =
                            meetings.filter { it.meetingType == MeetingType.OTHER }

                        val presence = people.map { person ->
                            val presenceMap =
                                sortedMapOf<MeetingType, Triple<Float, Float, Float>>(compareBy { it.index })
                            map.keys.forEach { type ->
                                val list = map[type] ?: listOf()
                                presenceMap[type] =
                                    if (list.isNotEmpty()) {
                                        val attendance =
                                            list.count { it.attendanceList.contains(person.id) }
                                                .toFloat() / list.size
                                        val absence =
                                            list.count { it.absenceList.keys.contains(person.id) }
                                                .toFloat() / list.size
                                        Triple(
                                            attendance,
                                            absence,
                                            1f - attendance - absence,
                                        )
                                    } else Triple(0f, 0f, 0f)
                            }
                            PersonPresence(person.id, person.name, presenceMap)
                        }

                        mutableState.update {
                            it.copy(meetings = map, presence = presence, isLoading = false)
                        }
                    }
            } catch (exc: InvalidUserException) {
                mutableState.update { it.copy(isLoading = false) }
            }
        }

        screenModelScope.launch(Dispatchers.IO) {
            preferencesRepository.getShowJustified().collect { showJustified ->
                mutableState.update { it.copy(showJustified = showJustified) }
            }
        }
    }

    fun toggleDetailsVisibility(presence: PersonPresence) {
        mutableState.update { it.copy(detailsPersonSelected = presence) }
    }

    fun toggleShowJustified() {
        screenModelScope.launch(Dispatchers.IO) {
            val currentValue = state.value.showJustified
            preferencesRepository.updatePresenceShowJustified(!currentValue)
        }
    }
}