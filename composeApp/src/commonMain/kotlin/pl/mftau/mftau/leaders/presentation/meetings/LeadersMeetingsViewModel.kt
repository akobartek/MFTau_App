package pl.mftau.mftau.leaders.presentation.meetings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.common.presentation.snackbars.SnackbarController
import pl.mftau.mftau.common.presentation.snackbars.SnackbarEvent
import pl.mftau.mftau.leaders.domain.model.InvalidUserException
import pl.mftau.mftau.leaders.domain.model.Meeting
import pl.mftau.mftau.leaders.domain.model.MeetingType
import pl.mftau.mftau.leaders.domain.repository.MeetingsRepository
import pl.mftau.mftau.leaders.domain.repository.PeopleRepository

class LeadersMeetingsViewModel(
    private val meetingsRepository: MeetingsRepository,
    private val peopleRepository: PeopleRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LeadersMeetingsScreenState())
    val state: StateFlow<LeadersMeetingsScreenState> = _state.asStateFlow()

    private val _selectedTabState = MutableStateFlow(Pair(0, true))
    val selectedTabState = _selectedTabState.asStateFlow()

    private fun checkInvalidUserException(action: suspend (CoroutineScope) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                action(this)
            } catch (exc: InvalidUserException) {
                toggleLoadingState(false)
            }
        }
    }

    init {
        checkInvalidUserException { scope ->
            meetingsRepository.getMeetings()
                .onEach { toggleLoadingState(true) }
                .combine(peopleRepository.getPeople()) { meetings, people ->
                    val map = hashMapOf<MeetingType, List<Meeting>>()
                    map[MeetingType.FORMATION] =
                        meetings.filter { it.meetingType == MeetingType.FORMATION }
                    map[MeetingType.PRAYERFUL] =
                        meetings.filter { it.meetingType == MeetingType.PRAYERFUL }
                    map[MeetingType.OTHER] =
                        meetings.filter { it.meetingType == MeetingType.OTHER }

                    _state.update {
                        it.copy(
                            meetings = map,
                            people = people,
                        )
                    }
                    toggleLoadingState(false)
                }
                .stateIn(scope, SharingStarted.WhileSubscribed(1000L), null)
        }
    }

    private fun toggleLoadingState(isLoading: Boolean) {
        _state.update { it.copy(isLoading = isLoading) }
    }

    fun updateSelection(selection: Int) {
        _selectedTabState.update { Pair(selection, it.first > selection) }
    }

    fun saveMeeting(meeting: Meeting) {
        checkInvalidUserException {
            meetingsRepository.saveMeeting(meeting)
            SnackbarController.sendEvent(SnackbarEvent.MeetingSaved)
        }
    }

    fun deleteMeeting(meeting: Meeting?) {
        if (meeting == null) return
        checkInvalidUserException {
            meetingsRepository.deleteMeeting(meeting)
            SnackbarController.sendEvent(SnackbarEvent.MeetingDeleted)
        }
    }

    fun clearMeetings() {
        checkInvalidUserException {
            meetingsRepository.clearMeetings()
        }
    }

    fun toggleMeetingEditorVisibility(meeting: Meeting? = null) {
        _state.update {
            it.copy(
                meetingEditorVisible = !it.meetingEditorVisible,
                meetingToEdit = meeting,
            )
        }
    }
}