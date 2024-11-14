package pl.mftau.mftau.leaders.presentation.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.common.presentation.snackbars.SnackbarController
import pl.mftau.mftau.common.presentation.snackbars.SnackbarEvent
import pl.mftau.mftau.leaders.domain.model.InvalidUserException
import pl.mftau.mftau.leaders.domain.model.Person
import pl.mftau.mftau.leaders.domain.repository.PeopleRepository

class LeadersPeopleViewModel(
    private val peopleRepository: PeopleRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(LeadersPeopleScreenState())
    val state: StateFlow<LeadersPeopleScreenState> = _state.asStateFlow()
    
    private fun checkInvalidUserException(action: suspend (CoroutineScope) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                action(this)
            } catch (exc: InvalidUserException) {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }

    init {
        checkInvalidUserException { scope ->
            peopleRepository.getPeople()
                .stateIn(scope, SharingStarted.WhileSubscribed(1000L), null)
                .onEach { _state.update { it.copy(isLoading = true) } }
                .collect { people ->
                    _state.update {
                        it.copy(
                            people = people ?: listOf(),
                            isLoading = false,
                        )
                    }
                }
        }
    }

    fun savePerson(person: Person) {
        checkInvalidUserException {
            peopleRepository.savePerson(person)
            SnackbarController.sendEvent(SnackbarEvent.PersonSaved)
        }
    }

    fun deletePerson(person: Person?) {
        if (person == null) return
        checkInvalidUserException {
            peopleRepository.deletePerson(person)
            SnackbarController.sendEvent(SnackbarEvent.PersonDeleted)
        }
    }

    fun togglePersonEditorVisibility(person: Person? = null) {
        _state.update {
            it.copy(
                personEditorVisible = !it.personEditorVisible,
                personToEdit = person
            )
        }
    }
}