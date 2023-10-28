package pl.mftau.mftau.core.presentation.screenmodels

import cafe.adriel.voyager.core.model.StateScreenModel

data class EmailScreenState(
    val name: String = "",
    val nameError: Boolean = false,
    val text: String = "",
    val textError: Boolean = false,
    val gdprChecked: Boolean = false
)

class EmailScreenModel: StateScreenModel<EmailScreenState>(EmailScreenState()) {

    fun updateName(value: String) {
        mutableState.value = mutableState.value.copy(
            name = value
        )
    }
    fun updateText(value: String) {
        mutableState.value = mutableState.value.copy(
            text = value
        )
    }
    fun updateGdpr(value: Boolean) {
        mutableState.value = mutableState.value.copy(
            gdprChecked = value
        )
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