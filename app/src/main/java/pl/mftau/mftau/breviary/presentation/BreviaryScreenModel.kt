package pl.mftau.mftau.breviary.presentation

import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.breviary.data.BreviaryRepository
import pl.mftau.mftau.breviary.data.BreviaryRepositoryImpl
import pl.mftau.mftau.breviary.model.Breviary
import pl.mftau.mftau.breviary.model.BreviaryType

class BreviaryScreenModel(
    private val type: BreviaryType,
    private val daysFromToday: Int = 0,
    private val accentColor: Color = Color.White,
    private val repository: BreviaryRepository = BreviaryRepositoryImpl(accentColor)
) : StateScreenModel<BreviaryScreenModel.State>(State.Loading) {

    sealed class State {
        data object Loading : State()
        data class MultipleOffices(val offices: Map<String, String>) : State()
        data class BreviaryAvailable(val breviary: Breviary) : State()
        data object Failure : State()
        data object Cancelled : State()
    }

    private var selectedOfficeLink = ""

    init {
        checkIfThereAreMultipleOffices()
    }

    fun checkIfThereAreMultipleOffices() {
        screenModelScope.launch {
            val result = repository.checkIfThereAreMultipleOffices(daysFromToday).first()
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
            val result = repository.loadBreviary(office, daysFromToday, type).first()
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