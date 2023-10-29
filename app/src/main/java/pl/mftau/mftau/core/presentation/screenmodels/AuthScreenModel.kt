package pl.mftau.mftau.core.presentation.screenmodels

import cafe.adriel.voyager.core.model.StateScreenModel

data class AuthScreenState(
    val email: String = "",
    val emailError: Boolean = false,
    val password: String = "",
    val passwordHidden: Boolean = true,
    val passwordErrorId: Int = -1,
    val isSigningUp: Boolean = false
)

class AuthScreenModel : StateScreenModel<AuthScreenState>(AuthScreenState()) {

    fun updateEmail(value: String) {
        mutableState.value = mutableState.value.copy(
            email = value
        )
    }
    fun updatePassword(value: String) {
        mutableState.value = mutableState.value.copy(
            password = value
        )
    }
    fun updatePasswordHidden() {
        mutableState.value = mutableState.value.copy(
            passwordHidden = !mutableState.value.passwordHidden
        )
    }
    fun updateSigningState(value: Boolean) {
        mutableState.value = mutableState.value.copy(
            isSigningUp = value
        )
    }

    fun validateInput(): Boolean {
        val newState = mutableState.value.copy(
            emailError = mutableState.value.email.isEmpty(),
            passwordErrorId = if (mutableState.value.password.isEmpty()) 1 else -1
        )
        mutableState.value = newState
        return !newState.emailError && newState.passwordErrorId != -1
    }
}