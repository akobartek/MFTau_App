package pl.mftau.mftau.readings.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import kotlinx.coroutines.flow.update

class ReadingsListScreenModel : StateScreenModel<Int>(0) {

    fun updateSelection(selection: Int) {
        mutableState.update { selection }
    }
}