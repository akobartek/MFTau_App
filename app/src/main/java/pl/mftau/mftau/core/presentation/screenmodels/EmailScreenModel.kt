package pl.mftau.mftau.core.presentation.screenmodels

import cafe.adriel.voyager.core.model.StateScreenModel
import kotlinx.coroutines.flow.update

data class EmailScreenState(
    val name: String = "",
    val nameError: Boolean = false,
    val text: String = "",
    val textError: Boolean = false,
    val gdprChecked: Boolean = false
)

class EmailScreenModel : StateScreenModel<EmailScreenState>(EmailScreenState()) {

    fun updateName(value: String) {
        mutableState.update { it.copy(name = value) }
    }

    fun updateText(value: String) {
        mutableState.update { it.copy(text = value) }
    }

    fun updateGdpr(value: Boolean) {
        mutableState.update { it.copy(gdprChecked = value) }
    }

    fun validateInput(): Boolean {
        val newState = mutableState.value.copy(
            nameError = mutableState.value.name.isEmpty(),
            textError = mutableState.value.text.isEmpty()
        )
        mutableState.value = newState
        return !newState.nameError && !newState.textError
    }
}