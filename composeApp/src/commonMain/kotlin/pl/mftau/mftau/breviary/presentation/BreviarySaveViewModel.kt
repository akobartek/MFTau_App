package pl.mftau.mftau.breviary.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.breviary.domain.model.BreviaryDay
import pl.mftau.mftau.breviary.domain.usecases.CheckOfficesUseCase
import pl.mftau.mftau.breviary.domain.usecases.SaveBreviaryUseCase

class BreviarySaveViewModel(
    private val checkOfficesUseCase: CheckOfficesUseCase,
    private val saveBreviaryUseCase: SaveBreviaryUseCase,
): ViewModel() {

    sealed class State {
        data object Init : State()
        data object Loading : State()
        data class MultipleOffices(val offices: Map<String, String>) : State()
        data class Downloading(val breviaryDay: BreviaryDay) : State()
        data object Failure : State()
        data object Cancelled : State()
    }

    private val _screenState = MutableStateFlow<State>(State.Init)
    val screenState: StateFlow<State> = _screenState.asStateFlow()

    private var selectedOfficeLink = ""
    private var date = ""

    fun setup(date: String) {
        this.date = date
    }

    fun checkIfThereAreMultipleOffices() {
        viewModelScope.launch(Dispatchers.IO) {
            _screenState.update { State.Loading }
            val result = checkOfficesUseCase(date)
            if (result.isSuccess) {
                val offices = result.getOrNull()
                if (offices == null) loadAndSaveBreviary()
                else _screenState.update { State.MultipleOffices(offices) }
            } else _screenState.update { State.Failure }
        }
    }

    fun officeSelected(office: String) {
        _screenState.update { State.Loading }
        selectedOfficeLink = office
        loadAndSaveBreviary(office)
    }

    private fun loadAndSaveBreviary(office: String = "") {
        viewModelScope.launch(Dispatchers.IO) {
            saveBreviaryUseCase(office, date).collect { value ->
                _screenState.update {
                    if (value != null) State.Downloading(value)
                    else State.Failure
                }
            }
        }
    }

    fun cancelScreen() {
        _screenState.update { State.Cancelled }
    }
}