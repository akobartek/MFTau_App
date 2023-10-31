package pl.mftau.mftau.breviary.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.breviary.data.BreviaryRepository
import pl.mftau.mftau.breviary.data.BreviaryRepositoryImpl
import pl.mftau.mftau.breviary.model.Breviary

class BreviaryScreenModel(
    private val position: Int = 0,
    private val daysFromToday: Int = 0,
    private val repository: BreviaryRepository = BreviaryRepositoryImpl()
) : StateScreenModel<BreviaryScreenModel.State>(State.Loading) {

    sealed class State {
        data object Loading : State()
        data class MultipleOffices(val offices: Map<String, String>) : State()
        data class BreviaryAvailable(val breviary: Breviary) : State()
        data object Failure : State()
    }

    private var selectedOfficeLink = ""

    init {
        checkIfThereAreMultipleOffices()
    }

    fun checkIfThereAreMultipleOffices() {
        screenModelScope.launch {
            val result = repository.checkIfThereAreMultipleOffices(daysFromToday).first()
            if (result.isSuccess) {
                val offices = result.getOrDefault(null)
                if (offices != null)
                    mutableState.update { State.MultipleOffices(offices) }
                else {

                }
            } else mutableState.update { State.Failure }
        }
    }

    fun officeLinkSelected(newLink: String) {
        mutableState.update { State.Loading }
        selectedOfficeLink = newLink
        screenModelScope.launch(Dispatchers.IO) {
            delay(2000)
            mutableState.update { State.BreviaryAvailable(object : Breviary() {}) }
        }
    }
}