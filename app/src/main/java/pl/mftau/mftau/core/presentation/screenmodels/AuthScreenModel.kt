package pl.mftau.mftau.core.presentation.screenmodels

import cafe.adriel.voyager.core.model.StateScreenModel
import kotlinx.coroutines.flow.update

class AuthScreenModel : StateScreenModel<AuthScreenModel.AuthScreenState>(AuthScreenState()) {

    data class AuthScreenState(
        val email: String = "",
        val emailError: Boolean = false,
        val password: String = "",
        val passwordHidden: Boolean = true,
        val passwordErrorId: Int = -1,
        val isSigningUp: Boolean = false
    )

    fun updateEmail(value: String) {
        mutableState.update { it.copy(email = value) }
    }

    fun updatePassword(value: String) {
        mutableState.update { it.copy(password = value) }
    }

    fun updatePasswordHidden() {
        mutableState.update { it.copy(passwordHidden = !it.passwordHidden) }
    }

    fun updateSigningState(value: Boolean) {
        mutableState.update { it.copy(isSigningUp = value) }
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