package pl.mftau.mftau.leaders.presentation.people.screenmodels

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.leaders.domain.model.Emaus
import pl.mftau.mftau.leaders.domain.model.InvalidUserException
import pl.mftau.mftau.leaders.domain.model.Person
import pl.mftau.mftau.leaders.domain.repository.PeopleRepository
import pl.mftau.mftau.leaders.domain.usecase.DeleteDrawsUseCase
import pl.mftau.mftau.leaders.domain.usecase.DrawNewEmausesUseCase
import pl.mftau.mftau.leaders.domain.usecase.GetLastDrawUseCase
import pl.mftau.mftau.leaders.presentation.people.screens.EmausScreen

class EmausScreenModel(
    private val peopleRepository: PeopleRepository,
    getLastDrawUseCase: GetLastDrawUseCase,
    private val drawNewEmausesUseCase: DrawNewEmausesUseCase,
    private val deleteDrawsUseCase: DeleteDrawsUseCase
) : StateScreenModel<EmausScreenModel.EmausScreenState>(EmausScreenState()) {

    data class EmausScreenState(
        val isLoading: Boolean = true,
        val lastDraw: List<Emaus> = listOf(),
        val people: List<Person> = listOf(),
        val noPeopleErrorVisible: Boolean = false,
        val drawInProgress: Boolean = false,
        val drawErrorMessageVisible: Boolean = false,
        val deleteMessageVisible: Boolean? = null
    ) {
        override fun equals(other: Any?): Boolean {
            if (other is EmausScreenState && (people !== other.people || lastDraw !== other.lastDraw))
                return false
            return super.equals(other)
        }

        override fun hashCode(): Int {
            var result = isLoading.hashCode()
            result = 31 * result + lastDraw.hashCode()
            result = 31 * result + people.hashCode()
            result = 31 * result + drawInProgress.hashCode()
            result = 31 * result + drawErrorMessageVisible.hashCode()
            result = 31 * result + (deleteMessageVisible?.hashCode() ?: 0)
            return result
        }
    }

    init {
        screenModelScope.launch(Dispatchers.IO) {
            try {
                getLastDrawUseCase.lastDraw
                    .onEach { mutableState.update { it.copy(isLoading = true) } }
                    .combine(peopleRepository.people) { lastDraw, people ->
                        mutableState.update {
                            it.copy(
                                lastDraw = lastDraw,
                                people = people
                            )
                        }
                    }
                    .onEach { mutableState.update { it.copy(isLoading = false) } }
                    .stateIn(this)
            } catch (exc: InvalidUserException) {
                // no-op
            }
        }
    }

    fun startDraw() {
        screenModelScope.launch(Dispatchers.IO) {
            val people = state.value.people
            if (people.size < 2) {
                toggleNoPeopleErrorVisibility()
            } else {
                toggleDrawProgressVisibility()
                delay(EmausScreen.ANIM_DURATION.toLong())
                val isSuccess = drawNewEmausesUseCase(people)
                delay(2L * EmausScreen.ANIM_DURATION)
                toggleDrawProgressVisibility()
                if (!isSuccess) toggleDrawErrorMessageVisibility()
            }
        }
    }

    private fun toggleDrawProgressVisibility() {
        mutableState.update { it.copy(drawInProgress = !it.drawInProgress) }
    }

    fun toggleNoPeopleErrorVisibility() {
        mutableState.update { it.copy(noPeopleErrorVisible = !it.noPeopleErrorVisible) }
    }

    fun toggleDrawErrorMessageVisibility() {
        mutableState.update { it.copy(drawErrorMessageVisible = !it.drawErrorMessageVisible) }
    }

    fun deleteDraws(clearAll: Boolean) {
        screenModelScope.launch(Dispatchers.IO) {
            val isSuccess = deleteDrawsUseCase(clearAll)
            toggleDeleteMessageVisibility(isSuccess)
        }
    }

    fun toggleDeleteMessageVisibility(isSuccessFul: Boolean? = null) {
        mutableState.update { it.copy(deleteMessageVisible = isSuccessFul) }
    }
}