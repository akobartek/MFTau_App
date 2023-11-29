package pl.mftau.mftau.leaders.presentation.screenmodels

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.leaders.domain.model.InvalidUserException
import pl.mftau.mftau.leaders.domain.model.Person
import pl.mftau.mftau.leaders.domain.repository.PeopleRepository

class PeopleListScreenModel(
    private val peopleRepository: PeopleRepository
) : StateScreenModel<PeopleListScreenModel.PeopleListState>(PeopleListState()) {

    data class PeopleListState(
        val people: List<Person> = listOf(),
        val isLoading: Boolean = true,
        val loadingError: Boolean = false, // TODO() handle loading error
        val personEditorVisible: Boolean = false,
        val personToEdit: Person? = null,
        val personSavedSuccessfully: Boolean? = null,
        val personDeletedSuccessfully: Boolean? = null
    ) {
        override fun equals(other: Any?): Boolean {
            if (other is PeopleListState && people !== other.people)
                return false
            return super.equals(other)
        }

        override fun hashCode(): Int {
            var result = people.hashCode()
            result = 31 * result + isLoading.hashCode()
            result = 31 * result + loadingError.hashCode()
            result = 31 * result + personEditorVisible.hashCode()
            result = 31 * result + (personToEdit?.hashCode() ?: 0)
            result = 31 * result + (personSavedSuccessfully?.hashCode() ?: 0)
            result = 31 * result + (personDeletedSuccessfully?.hashCode() ?: 0)
            return result
        }
    }

    init {
        checkInvalidUserException { scope ->
            peopleRepository.people
                .onEach { mutableState.update { it.copy(isLoading = true) } }
                .stateIn(scope, SharingStarted.WhileSubscribed(5000L), null)
                .collect { people ->
                    mutableState.update {
                        it.copy(people = people ?: listOf(), isLoading = false)
                    }
                }
        }
    }

    private fun checkInvalidUserException(action: suspend (CoroutineScope) -> Unit) {
        screenModelScope.launch(Dispatchers.IO) {
            try {
                action(this)
            } catch (exc: InvalidUserException) {
                mutableState.update { it.copy(loadingError = true, isLoading = false) }
            }
        }
    }

    fun savePerson(person: Person) {
        checkInvalidUserException {
            val isSuccess = peopleRepository.savePerson(person)
            togglePersonSavedVisibility(isSuccess)
        }
    }

    fun deletePerson(person: Person?) {
        if (person == null) return
        checkInvalidUserException {
            val isSuccess = peopleRepository.deletePerson(person)
            togglePersonDeletedVisibility(isSuccess)
        }
    }

    fun togglePersonEditorVisibility(person: Person? = null) {
        mutableState.update {
            it.copy(
                personEditorVisible = !it.personEditorVisible,
                personToEdit = person
            )
        }
    }

    fun togglePersonSavedVisibility(isSuccessFul: Boolean? = null) {
        mutableState.update { it.copy(personSavedSuccessfully = isSuccessFul) }
    }

    fun togglePersonDeletedVisibility(isSuccessFul: Boolean? = null) {
        mutableState.update { it.copy(personDeletedSuccessfully = isSuccessFul) }
    }
}