package pl.mftau.mftau.auth.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import kotlinx.coroutines.flow.update
import java.util.regex.Pattern

class AuthScreenModel : StateScreenModel<AuthScreenModel.AuthScreenState>(AuthScreenState()) {

    data class AuthScreenState(
        val email: String = "",
        val emailError: Boolean = false,
        val password: String = "",
        val passwordHidden: Boolean = true,
        val passwordErrorCode: Int = -1,
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
        val newState = mutableState.value.let { state ->
            state.copy(
                emailError = state.email.isValidEmail(),
                passwordErrorCode =
                if (state.password.isBlank()) 0
                else if (!state.password.isValidPassword()) 1
                else -1
            )
        }
        mutableState.value = newState
        return !newState.emailError && newState.passwordErrorCode != -1
    }

    private fun CharSequence.isValidEmail(): Boolean =
        android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()

    private fun CharSequence.isValidPassword(): Boolean {
        val passwordRegex = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z]).{8,20})"
        val pattern = Pattern.compile(passwordRegex)
        val matcher = pattern.matcher(this)
        return matcher.matches()
    }
}