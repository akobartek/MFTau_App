package pl.mftau.mftau.breviary.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.breviary.domain.model.BreviaryEntity
import pl.mftau.mftau.breviary.domain.usecase.LoadAndSaveBreviaryUseCase
import pl.mftau.mftau.breviary.domain.usecase.CheckIfThereAreMultipleOfficesUseCase

class BreviarySaveScreenModel(
    private val checkIfThereAreMultipleOfficesUseCase: CheckIfThereAreMultipleOfficesUseCase,
    private val loadAndSaveUseCase: LoadAndSaveBreviaryUseCase
) : StateScreenModel<BreviarySaveScreenModel.State>(State.Init) {

    sealed class State {
        data object Init : State()
        data object Loading : State()
        data class MultipleOffices(val offices: Map<String, String>) : State()
        data class DownloadingState(val entity: BreviaryEntity) : State()
        data object Failure : State()
        data object Cancelled : State()
    }

    private var selectedOfficeLink = ""
    private var date = ""

    fun setup(date: String) {
        this.date = date
    }

    fun checkIfThereAreMultipleOffices() {
        screenModelScope.launch {
            mutableState.update { State.Loading }
            val result = checkIfThereAreMultipleOfficesUseCase(date).first()
            if (result.isSuccess) {
                val offices = result.getOrNull()
                if (offices == null) loadAndSaveBreviary()
                else mutableState.update { State.MultipleOffices(offices) }
            } else mutableState.update { State.Failure }
        }
    }

    fun officeSelected(office: String) {
        mutableState.update { State.Loading }
        selectedOfficeLink = office
        loadAndSaveBreviary(office)
    }

    private fun loadAndSaveBreviary(office: String = "") {
        screenModelScope.launch {
            loadAndSaveUseCase(office, date).collect { value ->
                mutableState.update {
                    if (value != null) State.DownloadingState(value)
                    else State.Failure
                }
            }
        }
    }

    fun cancelScreen() {
        mutableState.update { State.Cancelled }
    }
}