package pl.mftau.mftau.core.presentation.screenmodels

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.google.firebase.FirebaseNetworkException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.auth.domain.AuthRepository
import pl.mftau.mftau.auth.domain.model.User
import pl.mftau.mftau.core.data.PreferencesRepository

class MainScreenModel(
    private val authRepository: AuthRepository,
    private val preferencesRepository: PreferencesRepository
) : StateScreenModel<MainScreenModel.MainScreenState>(MainScreenState()) {

    data class MainScreenState(
        val user: User? = null,
        val noInternetAction: NoInternetAction? = null,
        val resetPasswordDialogVisible: Boolean = false,
        val deleteAccountDialogVisible: Boolean = false,
        val accountDeletedMessageVisible: Boolean = false
    )

    init {
        startObservingUser()
    }

    private fun startObservingUser() {
        screenModelScope.launch(Dispatchers.Default) {
            authRepository.currentUser.collect { user ->
                mutableState.update { it.copy(user = user) }
            }
        }
    }

    fun hideNoInternetDialog() {
        mutableState.update { it.copy(noInternetAction = null) }
    }

    fun toggleResetPasswordDialogVisibility() {
        mutableState.update { it.copy(resetPasswordDialogVisible = !it.resetPasswordDialogVisible) }
    }

    fun toggleDeleteAccountDialogVisibility() {
        mutableState.update { it.copy(deleteAccountDialogVisible = !it.deleteAccountDialogVisible) }
    }

    fun toggleAccountDeletedMessageVisibility() {
        mutableState.update { it.copy(accountDeletedMessageVisible = !it.accountDeletedMessageVisible) }
    }

    fun signOut() {
        screenModelScope.launch { authRepository.signOut() }
    }

    fun sendResetPasswordEmail() {
        if (state.value.user == null) return
        screenModelScope.launch(Dispatchers.IO) {
            val result = authRepository.sendRecoveryEmail(state.value.user?.email ?: "")
            mutableState.update {
                if (result.isSuccess && result.getOrDefault(false))
                    it.copy(resetPasswordDialogVisible = true)
                else result.exceptionOrNull()?.let { exc ->
                    if (exc is FirebaseNetworkException)
                        it.copy(noInternetAction = NoInternetAction.RESET_PASSWORD)
                    else it
                } ?: it
            }
        }
    }

    fun deleteAccount() {
        screenModelScope.launch {
            val result = authRepository.deleteAccount()
            if (result.isSuccess) {
                mutableState.update { it.copy(accountDeletedMessageVisible = true) }
                preferencesRepository.updateLastUsedEmail("")
            }
        }
    }

    enum class NoInternetAction {
        RESET_PASSWORD, DELETE_ACCOUNT
    }
}