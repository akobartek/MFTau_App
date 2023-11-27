package pl.mftau.mftau.core.presentation.activities

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.common.data.PreferencesRepository
import pl.mftau.mftau.common.data.UserPreferences

class MainViewModel(private val preferencesRepository: PreferencesRepository) : ViewModel() {

    private val _splashScreenEnded = MutableStateFlow(false)
    val splashScreenEnded = _splashScreenEnded.asStateFlow()

    private val _preferences = MutableStateFlow(UserPreferences())
    val preferences = _preferences.asStateFlow()

    init {
        viewModelScope.launch {
            preferencesRepository.userPreferencesFlow
                .stateIn(this, SharingStarted.WhileSubscribed(5000L), UserPreferences())
                .collect { preferences ->
                    preferences.colorTheme.setupAppCompatDelegate()
                    _preferences.update { preferences }
                }
        }
    }

    fun splashScreenEnded() {
        _splashScreenEnded.update { true }

    }

    fun updateNotificationsAsked(asked: Boolean) {
        viewModelScope.launch {
            preferencesRepository.updateNotificationAsked(asked)
        }
    }

    fun updateAccentColor(color: Color) {
        viewModelScope.launch {
            preferencesRepository.updateAccentColor(color.toArgb())
        }
    }
}