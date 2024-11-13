package pl.mftau.mftau.breviary.presentation

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.breviary.domain.model.Breviary
import pl.mftau.mftau.breviary.domain.model.BreviaryType
import pl.mftau.mftau.breviary.domain.usecases.CheckOfficesUseCase
import pl.mftau.mftau.breviary.domain.usecases.LoadBreviaryUseCase

class BreviaryTextViewModel(
    private val checkOfficesUseCase: CheckOfficesUseCase,
    private val loadBreviaryUseCase: LoadBreviaryUseCase,
) : ViewModel() {

    sealed class State {
        data object Init : State()
        data object Loading : State()
        data class MultipleOffices(val offices: Map<String, String>) : State()
        data class BreviaryAvailable(val breviary: Breviary) : State()
        data object Failure : State()
        data object Cancelled : State()
    }

    private var selectedOfficeLink = ""
    private lateinit var type: BreviaryType
    private var date = ""
    private var color = Color.White

    private val _screenState = MutableStateFlow<State>(State.Init)
    val screenState: StateFlow<State> = _screenState.asStateFlow()

    fun setup(position: Int, date: String, accentColor: Color) {
        this.type = BreviaryType.fromPosition(position)
        this.date = date
        this.color = accentColor
        if (screenState.value is State.Init)
            checkIfThereAreMultipleOffices()
    }

    fun checkIfThereAreMultipleOffices() {
        viewModelScope.launch(Dispatchers.IO) {
            _screenState.update { State.Loading }
            val result = checkOfficesUseCase(date)
            if (result.isSuccess) {
                result.getOrNull().let { offices ->
                    if (offices == null) loadBreviary()
                    else _screenState.update { State.MultipleOffices(offices) }
                }
            } else {
                _screenState.update { State.Failure }
            }
        }
    }

    fun officeSelected(office: String) {
        selectedOfficeLink = office
        loadBreviary(office)
    }

    private fun loadBreviary(office: String = "") {
        viewModelScope.launch(Dispatchers.IO) {
            _screenState.update { State.Loading }
            val result = loadBreviaryUseCase(office, date, type, color)
            _screenState.update {
                result.getOrNull()
                    ?.let { State.BreviaryAvailable(it) }
                    ?: State.Failure
            }
        }
    }

    fun cancelScreen() {
        _screenState.update { State.Cancelled }
    }
}