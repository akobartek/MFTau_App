package pl.mftau.mftau.leaders.presentation.meetings.screenmodels

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.leaders.domain.model.InvalidUserException
import pl.mftau.mftau.leaders.domain.model.Meeting
import pl.mftau.mftau.leaders.domain.model.MeetingType
import pl.mftau.mftau.leaders.domain.model.Person
import pl.mftau.mftau.leaders.domain.repository.MeetingsRepository
import pl.mftau.mftau.leaders.domain.repository.PeopleRepository

class MeetingsListScreenModel(
    private val meetingsRepository: MeetingsRepository,
    private val peopleRepository: PeopleRepository
) : StateScreenModel<MeetingsListScreenModel.MeetingsListState>(MeetingsListState()) {

    data class MeetingsListState(
        val meetings: Map<MeetingType, List<Meeting>> = mapOf(),
        val people: List<Person> = listOf(),
        val isLoading: Boolean = true,
        val meetingEditorVisible: Boolean = false,
        val meetingToEdit: Meeting? = null,
        val meetingSavedSuccessfully: Boolean? = null,
        val meetingDeletedSuccessfully: Boolean? = null
    ) {
        override fun equals(other: Any?): Boolean {
            if (other is MeetingsListState && (meetings !== other.meetings || people !== other.people))
                return false
            return super.equals(other)
        }

        override fun hashCode(): Int {
            var result = meetings.hashCode()
            result = 31 * result + isLoading.hashCode()
            result = 31 * result + meetingEditorVisible.hashCode()
            result = 31 * result + (meetingToEdit?.hashCode() ?: 0)
            result = 31 * result + (meetingSavedSuccessfully?.hashCode() ?: 0)
            result = 31 * result + (meetingDeletedSuccessfully?.hashCode() ?: 0)
            return result
        }
    }

    private val _selectedTabState = MutableStateFlow(Pair(0, true))
    val selectedTabState = _selectedTabState.asStateFlow()

    private fun checkInvalidUserException(action: suspend (CoroutineScope) -> Unit) {
        screenModelScope.launch(Dispatchers.IO) {
            try {
                action(this)
            } catch (exc: InvalidUserException) {
                mutableState.update { it.copy(isLoading = false) }
            }
        }
    }

    init {
        checkInvalidUserException { scope ->
            meetingsRepository.meetings
                .stateIn(scope, SharingStarted.WhileSubscribed(5000L), null)
                .onEach { toggleLoadingState(true) }
                .collect { meetings ->
                    val map = hashMapOf<MeetingType, List<Meeting>>()
                    map[MeetingType.FORMATION] =
                        meetings?.filter { it.meetingType == MeetingType.FORMATION } ?: listOf()
                    map[MeetingType.PRAYERFUL] =
                        meetings?.filter { it.meetingType == MeetingType.PRAYERFUL } ?: listOf()
                    map[MeetingType.OTHER] =
                        meetings?.filter { it.meetingType == MeetingType.OTHER } ?: listOf()

                    mutableState.update { it.copy(meetings = map) }
                    toggleLoadingState(false)
                }
        }
        checkInvalidUserException { scope ->
            peopleRepository.people
                .stateIn(scope, SharingStarted.WhileSubscribed(5000L), null)
                .collect { people ->
                    mutableState.update { it.copy(people = people ?: listOf()) }
                }
        }
    }

    private fun toggleLoadingState(isLoading: Boolean) {
        mutableState.update { it.copy(isLoading = isLoading) }
    }

    fun updateSelection(selection: Int) {
        _selectedTabState.update { Pair(selection, it.first > selection) }
    }

    fun saveMeeting(meeting: Meeting) {
        checkInvalidUserException {
            val isSuccess = meetingsRepository.saveMeeting(meeting)
            toggleMeetingSavedVisibility(isSuccess)
        }
    }

    fun deleteMeeting(meeting: Meeting?) {
        if (meeting == null) return
        checkInvalidUserException {
            val isSuccess = meetingsRepository.deleteMeeting(meeting)
            toggleMeetingDeletedVisibility(isSuccess)
        }
    }

    fun clearMeetings() {
        checkInvalidUserException {
            meetingsRepository.clearMeetings()
        }
    }

    fun toggleMeetingEditorVisibility(meeting: Meeting? = null) {
        mutableState.update {
            it.copy(
                meetingEditorVisible = !it.meetingEditorVisible,
                meetingToEdit = meeting
            )
        }
    }

    fun toggleMeetingSavedVisibility(isSuccessFul: Boolean? = null) {
        mutableState.update { it.copy(meetingSavedSuccessfully = isSuccessFul) }
    }

    fun toggleMeetingDeletedVisibility(isSuccessFul: Boolean? = null) {
        mutableState.update { it.copy(meetingDeletedSuccessfully = isSuccessFul) }
    }
}