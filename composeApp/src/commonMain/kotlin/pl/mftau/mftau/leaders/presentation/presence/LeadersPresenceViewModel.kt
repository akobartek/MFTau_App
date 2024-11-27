package pl.mftau.mftau.leaders.presentation.presence

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.common.data.PreferencesRepository
import pl.mftau.mftau.leaders.domain.model.InvalidUserException
import pl.mftau.mftau.leaders.domain.model.Meeting
import pl.mftau.mftau.leaders.domain.model.MeetingType
import pl.mftau.mftau.leaders.domain.model.PersonPresence
import pl.mftau.mftau.leaders.domain.repository.MeetingsRepository
import pl.mftau.mftau.leaders.domain.repository.PeopleRepository

class LeadersPresenceViewModel(
    private val meetingsRepository: MeetingsRepository,
    private val peopleRepository: PeopleRepository,
    private val preferencesRepository: PreferencesRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(LeadersPresenceScreenState())
    val state: StateFlow<LeadersPresenceScreenState> = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                combine(
                    meetingsRepository.getMeetings(),
                    peopleRepository.getPeople(),
                ) { meetings, people ->
                    val map = hashMapOf<MeetingType, List<Meeting>>()
                    map[MeetingType.FORMATION] =
                        meetings.filter { it.meetingType == MeetingType.FORMATION }
                    map[MeetingType.PRAYERFUL] =
                        meetings.filter { it.meetingType == MeetingType.PRAYERFUL }
                    map[MeetingType.OTHER] =
                        meetings.filter { it.meetingType == MeetingType.OTHER }

                    val presence = people.map { person ->
                        val presenceMap =
                            mutableMapOf<MeetingType, Triple<Float, Float, Float>>()
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
                    map to presence
                }
                    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(1000L), null)
                    .collect { pair ->
                        val (meetings, presence) = pair ?: return@collect
                        _state.update {
                            it.copy(
                                meetings = meetings,
                                presence = presence,
                                isLoading = false,
                            )
                        }
                    }
            } catch (exc: InvalidUserException) {
                _state.update { it.copy(isLoading = false) }
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.getShowJustified().collect { showJustified ->
                _state.update { it.copy(showJustified = showJustified) }
            }
        }
    }

    fun toggleDetailsVisibility(presence: PersonPresence?) {
        _state.update { it.copy(detailsPersonSelected = presence) }
    }

    fun toggleShowJustified(currentValue: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.updatePresenceShowJustified(!currentValue)
        }
    }
}