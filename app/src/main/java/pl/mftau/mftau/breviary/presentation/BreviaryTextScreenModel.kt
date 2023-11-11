package pl.mftau.mftau.breviary.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.breviary.domain.usecase.CheckIfThereAreMultipleOfficesUseCase
import pl.mftau.mftau.breviary.domain.usecase.LoadSingleBreviaryUseCase
import pl.mftau.mftau.breviary.domain.model.Breviary
import pl.mftau.mftau.breviary.domain.model.BreviaryType

class BreviaryTextScreenModel(
    private val checkIfThereAreMultipleOfficesUseCase: CheckIfThereAreMultipleOfficesUseCase,
    private val loadSingleBreviaryUseCase: LoadSingleBreviaryUseCase
) : StateScreenModel<BreviaryTextScreenModel.State>(State.Loading) {

    sealed class State {
        data object Loading : State()
        data class MultipleOffices(val offices: Map<String, String>) : State()
        data class BreviaryAvailable(val breviary: Breviary) : State()
        data object Failure : State()
        data object Cancelled : State()
    }

    private var selectedOfficeLink = ""
    private lateinit var type: BreviaryType
    private var daysFromToday = 0

    fun setup(type: BreviaryType, daysFromToday: Int) {
        this.type = type
        this.daysFromToday = daysFromToday
        checkIfThereAreMultipleOffices()
    }

    fun checkIfThereAreMultipleOffices() {
        screenModelScope.launch {
            val result = checkIfThereAreMultipleOfficesUseCase(daysFromToday).first()
            if (result.isSuccess) {
                val offices = result.getOrNull()
                if (offices == null) loadBreviary()
                else mutableState.update { State.MultipleOffices(offices) }
            } else mutableState.update { State.Failure }
        }
    }

    fun officeSelected(office: String) {
        mutableState.update { State.Loading }
        selectedOfficeLink = office
        loadBreviary(office)
    }

    private fun loadBreviary(office: String = "") {
        screenModelScope.launch {
            val result = loadSingleBreviaryUseCase(office, daysFromToday, type).first()
            mutableState.update {
                if (result.isFailure || result.getOrNull() == null) State.Failure
                else State.BreviaryAvailable(result.getOrNull()!!)
            }
        }
    }

    fun cancelScreen() {
        mutableState.update { State.Cancelled }
    }
}