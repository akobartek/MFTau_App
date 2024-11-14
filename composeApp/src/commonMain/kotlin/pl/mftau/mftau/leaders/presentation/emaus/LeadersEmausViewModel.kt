package pl.mftau.mftau.leaders.presentation.emaus

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
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
import pl.mftau.mftau.leaders.domain.repository.PeopleRepository
import pl.mftau.mftau.leaders.domain.usecase.DeleteDrawsUseCase
import pl.mftau.mftau.leaders.domain.usecase.DrawNewEmausesUseCase
import pl.mftau.mftau.leaders.domain.usecase.GetLastDrawUseCase

class LeadersEmausViewModel(
    private val peopleRepository: PeopleRepository,
    getLastDrawUseCase: GetLastDrawUseCase,
    private val drawNewEmausesUseCase: DrawNewEmausesUseCase,
    private val deleteDrawsUseCase: DeleteDrawsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(LeadersEmausScreenState())
    val state: StateFlow<LeadersEmausScreenState> = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getLastDrawUseCase()
                    .onEach { _state.update { it.copy(isLoading = true) } }
                    .combine(peopleRepository.getPeople()) { lastDraw, people ->
                        _state.update {
                            it.copy(
                                lastDraw = lastDraw,
                                people = people,
                            )
                        }
                    }
                    .onEach { _state.update { it.copy(isLoading = false) } }
                    .stateIn(this)
            } catch (exc: InvalidUserException) {
                // no-op
            }
        }
    }

    fun startDraw() {
        viewModelScope.launch(Dispatchers.IO) {
            val people = state.value.people
            if (people.size < 2) {
                toggleNoPeopleErrorVisibility()
            } else {
                toggleDrawProgressVisibility()

                delay(ANIM_DURATION.toLong())
                val isSuccess = drawNewEmausesUseCase(people)
                delay(2L * ANIM_DURATION)

                toggleDrawProgressVisibility()
                if (!isSuccess) toggleDrawErrorMessageVisibility()
            }
        }
    }

    private fun toggleDrawProgressVisibility() {
        _state.update { it.copy(drawInProgress = !it.drawInProgress) }
    }

    fun toggleNoPeopleErrorVisibility() {
        _state.update { it.copy(noPeopleErrorVisible = !it.noPeopleErrorVisible) }
    }

    fun toggleDrawErrorMessageVisibility() {
        _state.update { it.copy(drawErrorMessageVisible = !it.drawErrorMessageVisible) }
    }

    fun deleteDraws(clearAll: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val isSuccess = deleteDrawsUseCase(clearAll)
            SnackbarController.sendEvent(
                if (isSuccess) SnackbarEvent.EmausDeleteSuccess else SnackbarEvent.EmausDeleteError
            )
        }
    }
}