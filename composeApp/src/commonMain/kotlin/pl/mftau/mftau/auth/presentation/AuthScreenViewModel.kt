package pl.mftau.mftau.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.gitlive.firebase.FirebaseNetworkException
import dev.gitlive.firebase.auth.FirebaseAuthInvalidCredentialsException
import dev.gitlive.firebase.auth.FirebaseAuthInvalidUserException
import dev.gitlive.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.auth.domain.AuthRepository
import pl.mftau.mftau.auth.domain.model.EmailNotVerifiedException
import pl.mftau.mftau.common.data.PreferencesRepository
import pl.mftau.mftau.common.utils.isValidEmail

class AuthScreenViewModel(
    private val authRepository: AuthRepository,
    private val preferencesRepository: PreferencesRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(AuthScreenState())
    val state: StateFlow<AuthScreenState> = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) { updateEmail(preferencesRepository.getLastUsedEmail()) }
    }

    fun updateEmail(value: String) {
        _state.update { it.copy(email = value) }
    }

    fun updatePassword(value: String) {
        _state.update { it.copy(password = value) }
    }

    fun updatePasswordHidden() {
        _state.update { it.copy(passwordHidden = !it.passwordHidden) }
    }

    fun signInErrorShowed() {
        _state.update { it.copy(signInErrorSnackbarVisible = !it.signInErrorSnackbarVisible) }
    }

    fun signUpErrorShowed() {
        _state.update { it.copy(signUpErrorSnackbarVisible = !it.signUpErrorSnackbarVisible) }
    }

    fun hideNoInternetDialog() {
        _state.update { it.copy(noInternetAction = null) }
    }

    fun toggleForgottenPasswordDialogVisibility() {
        _state.update {
            it.copy(
                forgottenPasswordDialogVisible = !it.forgottenPasswordDialogVisible,
                forgottenPasswordDialogError = false,
            )
        }
    }

    fun toggleForgottenPasswordSuccessVisibility() {
        _state.update { it.copy(forgottenPasswordDialogSuccess = !it.forgottenPasswordDialogSuccess) }
    }

    fun toggleSignUpSuccessVisibility() {
        _state.update { it.copy(isSignedUpDialogVisible = !it.isSignedUpDialogVisible) }
    }

    fun toggleEmailUnverifiedDialogVisibility(resend: Boolean) {
        _state.update { it.copy(emailUnverifiedDialogVisible = !it.emailUnverifiedDialogVisible) }
        viewModelScope.launch {
            if (resend) authRepository.sendVerificationEmail()
            authRepository.signOut()
        }
    }

    fun signIn() {
        if (!validateInput(false)) return
        viewModelScope.launch(Dispatchers.IO) {
            val result = authRepository.signIn(state.value.email, state.value.password)
            _state.update {
                if (result.isSuccess && result.getOrDefault(false)) {
                    preferencesRepository.updateLastUsedEmail(state.value.email)
                    it.copy(isSignedIn = true)
                } else result.exceptionOrNull()?.let { exc ->
                    when (exc) {
                        is FirebaseAuthInvalidUserException ->
                            it.copy(emailError = EmailErrorType.NO_USER)

                        is EmailNotVerifiedException ->
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
        viewModelScope.launch(Dispatchers.IO) {
            val result = authRepository.signUp(state.value.email, state.value.password)
            _state.update {
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
        _state.update { it.copy(forgottenPasswordDialogError = !email.isValidEmail()) }
        viewModelScope.launch(Dispatchers.IO) {
            val result = authRepository.sendRecoveryEmail(email)
            _state.update {
                if (result.isSuccess && result.getOrDefault(false))
                    it.copy(
                        forgottenPasswordDialogSuccess = true,
                        forgottenPasswordDialogVisible = false,
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
        val newState = _state.value.let { state ->
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
        _state.update { newState }
        return newState.emailError == null && newState.passwordError == null
    }

    private fun CharSequence.isValidPassword(): Boolean {
        val passwordRegex = Regex("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z]).{8,20})")
        return this.matches(passwordRegex)
    }
}