package pl.mftau.mftau.auth.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.auth.domain.AuthRepository
import java.util.regex.Pattern

class AuthScreenModel(
    private val authRepository: AuthRepository
) : StateScreenModel<AuthScreenModel.AuthScreenState>(AuthScreenState()) {

    data class AuthScreenState(
        val email: String = "",
        val emailError: EmailErrorType? = null,
        val password: String = "",
        val passwordHidden: Boolean = true,
        val passwordError: PasswordErrorType? = null,
        val isSignedIn: Boolean = false,
        val isSignedUpDialogVisible: Boolean = false,
        val signInErrorSnackbarVisible: Boolean = false,
        val signUpErrorSnackbarVisible: Boolean = false,
        val noInternetDialogVisible: Boolean = false, // TODO
        val forgottenPasswordDialogVisible: Boolean = false, // TODO
        val emailUnverifiedDialogVisible: Boolean = false // TODO
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

    fun signInErrorShowed() {
        mutableState.update { it.copy(signInErrorSnackbarVisible = !it.signInErrorSnackbarVisible) }
    }

    fun signUpErrorShowed() {
        mutableState.update { it.copy(signUpErrorSnackbarVisible = !it.signUpErrorSnackbarVisible) }
    }

    fun signIn() {
        if (!validateInput(false)) return
        screenModelScope.launch(Dispatchers.IO) {
            val result = authRepository.signIn(state.value.email, state.value.password)
            mutableState.update {
                if (result.isSuccess && result.getOrDefault(false))
                    it.copy(isSignedIn = true)
                else result.exceptionOrNull()?.let { exc ->
                    when (exc) {
                        is FirebaseAuthInvalidUserException ->
                            it.copy(emailError = EmailErrorType.NO_USER)

                        is FirebaseNetworkException ->
                            it.copy(noInternetDialogVisible = true)

                        is FirebaseAuthInvalidCredentialsException ->
                            it.copy(passwordError = PasswordErrorType.INVALID)

                        else -> it.copy(signInErrorSnackbarVisible = true)
                    }
                } ?: it.copy(signInErrorSnackbarVisible = true)
            }
            println("SIGN IN: $result")
        }
    }

    fun signUp() {
        if (!validateInput(true)) return
        screenModelScope.launch(Dispatchers.IO) {
            val result = authRepository.signUp(state.value.email, state.value.password)
            mutableState.update {
                if (result.isSuccess && result.getOrDefault(false))
                    it.copy(isSignedUpDialogVisible = true)
                else result.exceptionOrNull()?.let { exc ->
                    when (exc) {
                        is FirebaseAuthUserCollisionException ->
                            it.copy(emailError = EmailErrorType.USER_EXISTS)

                        is FirebaseNetworkException ->
                            it.copy(noInternetDialogVisible = true)

                        else -> it.copy(signUpErrorSnackbarVisible = true)
                    }
                } ?: it.copy(signUpErrorSnackbarVisible = true)
            }
            println("SIGN UP: $result")
        }
    }

    private fun validateInput(isSigningUp: Boolean): Boolean {
        val newState = mutableState.value.let { state ->
            state.copy(
                emailError = if (state.email.isValidEmail()) null else EmailErrorType.INVALID,
                passwordError = when {
                    (!isSigningUp && state.password.isBlank()) -> PasswordErrorType.EMPTY
                    (isSigningUp && state.password.length < 8) -> PasswordErrorType.TOO_SHORT
                    (isSigningUp && !state.password.isValidPassword()) -> PasswordErrorType.WRONG
                    else -> null
                }
            )
        }
        mutableState.value = newState
        return newState.emailError == null && newState.passwordError == null
    }

    private fun CharSequence.isValidEmail(): Boolean =
        android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()

    private fun CharSequence.isValidPassword(): Boolean {
        val passwordRegex = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z]).{8,20})"
        val pattern = Pattern.compile(passwordRegex)
        val matcher = pattern.matcher(this)
        return matcher.matches()
    }

    enum class EmailErrorType {
        INVALID, NO_USER, USER_EXISTS
    }

    enum class PasswordErrorType {
        EMPTY, TOO_SHORT, WRONG, INVALID
    }
}