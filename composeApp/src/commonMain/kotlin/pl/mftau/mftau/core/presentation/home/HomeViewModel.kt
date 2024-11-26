package pl.mftau.mftau.core.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.auth.domain.AuthRepository
import pl.mftau.mftau.common.data.PreferencesRepository
import pl.mftau.mftau.common.presentation.snackbars.SnackbarController
import pl.mftau.mftau.common.presentation.snackbars.SnackbarEvent

class HomeViewModel(
    private val authRepository: AuthRepository,
    private val preferencesRepository: PreferencesRepository,
) : ViewModel() {

    private var userJob: Job? = null
    private val _state = MutableStateFlow(HomeScreenState())
    val state: StateFlow<HomeScreenState> = _state.asStateFlow()

    init {
        startObservingUser()
    }

    private fun startObservingUser() {
        userJob = viewModelScope.launch(Dispatchers.IO) {
            authRepository.currentUser
                .stateIn(this, SharingStarted.WhileSubscribed(5000L), null)
                .collect { user -> _state.update { it.copy(user = user) } }
        }
    }

    fun hideNoInternetDialog() {
        _state.update { it.copy(resetPasswordFailed = false) }
    }

    fun signOut() {
        userJob?.cancel()
        viewModelScope.launch { authRepository.signOut() }
    }

    fun sendResetPasswordEmail() {
        state.value.user?.let { user ->
            viewModelScope.launch(Dispatchers.IO) {
                val result = authRepository.sendRecoveryEmail(user.email ?: "")
                if (result.isSuccess)
                    SnackbarController.sendEvent(SnackbarEvent.ResetPasswordMessageSent)
                else
                    _state.update { it.copy(resetPasswordFailed = true) }
            }
        }
    }

    fun deleteAccount() {
        userJob?.cancel()
        viewModelScope.launch {
            authRepository.deleteAccount()
            SnackbarController.sendEvent(SnackbarEvent.AccountDeleted)
            preferencesRepository.updateLastUsedEmail("")
        }
    }
}