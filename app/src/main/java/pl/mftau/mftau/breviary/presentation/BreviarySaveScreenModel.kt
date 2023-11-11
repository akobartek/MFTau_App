package pl.mftau.mftau.breviary.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BreviarySaveScreenModel(

) : StateScreenModel<BreviarySaveScreenModel.State>(State.Init) {

    sealed class State {
        data object Init : State()
        data object Loading : State()
        data class MultipleOffices(val offices: Map<String, String>) : State()
        data class DownloadingState(val number: Int) : State()
        data object Failure : State()
        data object Cancelled : State()
    }

    private var selectedOfficeLink = ""
    private var daysFromToday = 0

    fun setup(daysFromToday: Int) {
        this.daysFromToday = daysFromToday
        checkIfThereAreMultipleOffices()
    }

    fun checkIfThereAreMultipleOffices() {
        screenModelScope.launch {
//            val result = repository.checkIfThereAreMultipleOffices(daysFromToday).first()
//            if (result.isSuccess) {
//                val offices = result.getOrNull()
//                if (offices == null) loadBreviary()
//                else mutableState.update { State.MultipleOffices(offices) }
//            } else mutableState.update { State.Failure }
        }
    }

    fun officeSelected(office: String) {
        mutableState.update { State.Loading }
        selectedOfficeLink = office
        loadBreviary(office)
    }

    private fun loadBreviary(office: String = "") {
        screenModelScope.launch {
//            val result = repository.loadBreviary(office, daysFromToday, type).first()
//            mutableState.update {
//                if (result.isFailure || result.getOrNull() == null) State.Failure
//                else State.BreviaryAvailable(result.getOrNull()!!)
//            }
        }
    }

    fun cancelScreen() {
        mutableState.update { State.Cancelled }
    }
}