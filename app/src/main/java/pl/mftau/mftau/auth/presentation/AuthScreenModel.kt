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
import pl.mftau.mftau.auth.domain.model.FirebaseAuthEmailNotVerifiedException
import pl.mftau.mftau.common.data.PreferencesRepository
import pl.mftau.mftau.common.utils.isValidEmail
import java.util.regex.Pattern

class AuthScreenModel(
    private val authRepository: AuthRepository,
    private val preferencesRepository: PreferencesRepository
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
        val noInternetAction: NoInternetAction? = null,
        val forgottenPasswordDialogVisible: Boolean = false,
        val forgottenPasswordDialogSuccess: Boolean = false,
        val forgottenPasswordDialogError: Boolean = false,
        val emailUnverifiedDialogVisible: Boolean = false
    )

    init {
        screenModelScope.launch { updateEmail(preferencesRepository.getLastUsedEmail()) }
    }

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

    fun hideNoInternetDialog() {
        mutableState.update { it.copy(noInternetAction = null) }
    }

    fun toggleForgottenPasswordDialogVisibility() {
        mutableState.update {
            it.copy(
                forgottenPasswordDialogVisible = !it.forgottenPasswordDialogVisible,
                forgottenPasswordDialogError = false
            )
        }
    }

    fun toggleForgottenPasswordSuccessVisibility() {
        mutableState.update { it.copy(forgottenPasswordDialogSuccess = !it.forgottenPasswordDialogSuccess) }
    }

    fun toggleSignUpSuccessVisibility() {
        mutableState.update { it.copy(isSignedUpDialogVisible = !it.isSignedUpDialogVisible) }
    }

    fun toggleEmailUnverifiedDialogVisibility(resend: Boolean) {
        mutableState.update { it.copy(emailUnverifiedDialogVisible = !it.emailUnverifiedDialogVisible) }
        screenModelScope.launch {
            if (resend) authRepository.sendVerificationEmail()
            authRepository.signOut()
        }
    }

    fun signIn() {
        if (!validateInput(false)) return
        screenModelScope.launch(Dispatchers.IO) {
            val result = authRepository.signIn(state.value.email, state.value.password)
            mutableState.update {
                if (result.isSuccess && result.getOrDefault(false)) {
                    preferencesRepository.updateLastUsedEmail(state.value.email)
                    it.copy(isSignedIn = true)
                } else result.exceptionOrNull()?.let { exc ->
                    when (exc) {
                        is FirebaseAuthInvalidUserException ->
                            it.copy(emailError = EmailErrorType.NO_USER)

                        is FirebaseAuthEmailNotVerifiedException ->
                            it.copy(emailUnverifiedDialogVisible = true)

                        is FirebaseNetworkException ->
                            it.copy(noInternetAction = NoInternetAction.SIGN_IN)

                        is FirebaseAuthInvalidCredentialsException ->
                            it.copy(passwordError = PasswordErrorType.INVALID)

                        else -> it.copy(signInErrorSnackbarVisible = true)
                    }
                } ?: it.copy(signInErrorSnackbarVisible = true)
            }
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
                            it.copy(noInternetAction = NoInternetAction.SIGN_UP)

                        else -> it.copy(signUpErrorSnackbarVisible = true)
                    }
                } ?: it.copy(signUpErrorSnackbarVisible = true)
            }
        }
    }

    fun sendResetPasswordEmail(email: String) {
        mutableState.update { it.copy(forgottenPasswordDialogError = !email.isValidEmail()) }
        screenModelScope.launch(Dispatchers.IO) {
            val result = authRepository.sendRecoveryEmail(email)
            mutableState.update {
                if (result.isSuccess && result.getOrDefault(false))
                    it.copy(
                        forgottenPasswordDialogSuccess = true,
                        forgottenPasswordDialogVisible = false
                    )
                else result.exceptionOrNull()?.let { exc ->
                    when (exc) {
                        is FirebaseNetworkException -> it.copy(
                            noInternetAction = NoInternetAction.RESET_PASSWORD,
                            forgottenPasswordDialogVisible = false
                        )

                        else -> it.copy(forgottenPasswordDialogError = true)
                    }
                } ?: it.copy(forgottenPasswordDialogError = true)
            }
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

    enum class NoInternetAction {
        SIGN_IN, SIGN_UP, RESET_PASSWORD
    }
}