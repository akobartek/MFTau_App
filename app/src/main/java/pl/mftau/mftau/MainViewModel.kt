package pl.mftau.mftau

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.core.data.PreferencesRepository
import pl.mftau.mftau.core.data.UserPreferences

class MainViewModel(private val preferencesRepository: PreferencesRepository) : ViewModel() {

    private val _splashScreenEnded = MutableStateFlow(false)
    val splashScreenEnded = _splashScreenEnded.asStateFlow()

    private val _preferences = MutableStateFlow(UserPreferences())
    val preferences = _preferences.asStateFlow()

    init {
        viewModelScope.launch {
            preferencesRepository.preferencesFlow.collect { preferences ->
                preferences.colorTheme.setupAppCompatDelegate()
                _preferences.update { preferences }
            }
        }
    }

    fun splashScreenEnded() {
        _splashScreenEnded.update { true }

    }

    fun updateAccentColor(color: Color) {
        viewModelScope.launch {
            preferencesRepository.updateAccentColor(color.toArgb())
        }
    }
}