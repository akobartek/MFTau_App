package pl.mftau.mftau.breviary.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.breviary.domain.usecase.CheckIfThereAreMultipleOfficesUseCase
import pl.mftau.mftau.breviary.domain.usecase.LoadSingleBreviaryUseCase
import pl.mftau.mftau.breviary.domain.model.Breviary
import pl.mftau.mftau.breviary.domain.model.BreviaryType
import pl.mftau.mftau.breviary.domain.usecase.LoadFromDbBreviaryUseCase

class BreviaryTextScreenModel(
    private val checkOfficesUseCase: CheckIfThereAreMultipleOfficesUseCase,
    private val loadSingleUseCase: LoadSingleBreviaryUseCase,
    private val dbLoadUseCase: LoadFromDbBreviaryUseCase
) : StateScreenModel<BreviaryTextScreenModel.State>(State.Init) {

    sealed class State {
        data object Init : State()
        data object Loading : State()
        data class MultipleOffices(val offices: Map<String, String>) : State()
        data class BreviaryAvailable(val breviary: Breviary) : State()
        data class Failure(val processingFailed: Boolean, val downloadsClicked: Boolean = false) : State()
        data object Cancelled : State()
    }

    private var selectedOfficeLink = ""
    private lateinit var type: BreviaryType
    private var date = ""

    fun setup(type: BreviaryType, date: String) {
        this.type = type
        this.date = date
        if (state.value is State.Init)
            checkIfThereAreMultipleOffices()
    }

    fun checkIfThereAreMultipleOffices() {
        screenModelScope.launch(Dispatchers.IO) {
            mutableState.update { State.Loading }
            val result = checkOfficesUseCase(date)
            if (result.isSuccess) {
                val offices = result.getOrNull()
                if (offices == null) loadBreviary()
                else mutableState.update { State.MultipleOffices(offices) }
            } else {
                result.exceptionOrNull()?.let { println("XDDDD ${it.cause}") }
                val dbResult = dbLoadUseCase(date, type)
                mutableState.update {
                    val value = dbResult.getOrNull()
                    if (dbResult.isFailure || value == null || value.html.isBlank())
                        State.Failure(processingFailed = false)
                    else State.BreviaryAvailable(dbResult.getOrNull()!!)
                }
            }
        }
    }

    fun officeSelected(office: String) {
        selectedOfficeLink = office
        loadBreviary(office)
    }

    private fun loadBreviary(office: String = "") {
        screenModelScope.launch(Dispatchers.IO) {
            mutableState.update { State.Loading }
            val result = loadSingleUseCase(office, date, type)
            if (result.isSuccess && result.getOrNull() != null)
                mutableState.update { State.BreviaryAvailable(result.getOrNull()!!) }
            else {
                val dbResult = dbLoadUseCase(date, type)
                mutableState.update {
                    val value = dbResult.getOrNull()
                    if (dbResult.isFailure || value == null || value.html.isBlank())
                        State.Failure(processingFailed = true)
                    else State.BreviaryAvailable(dbResult.getOrNull()!!)
                }
            }
        }
    }

    fun cancelScreen() {
        mutableState.update { State.Cancelled }
    }

    fun onDownloadsDialogClicked() {
        mutableState.update { State.Failure(processingFailed = true, downloadsClicked = true) }
    }
}