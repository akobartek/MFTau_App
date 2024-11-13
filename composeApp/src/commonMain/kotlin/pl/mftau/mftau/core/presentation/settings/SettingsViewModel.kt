package pl.mftau.mftau.core.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.mftau.mftau.ui.theme.ColorTheme
import pl.mftau.mftau.common.data.PreferencesRepository

class SettingsViewModel(private val preferencesRepository: PreferencesRepository) : ViewModel() {

    val preferencesFlow = preferencesRepository.userPreferencesFlow

    fun updateNightMode(value: String) {
        viewModelScope.launch {
            preferencesRepository.updateTheme(ColorTheme.fromValue(value))
        }
    }

    fun updateDynamicColors(dynamicColors: Boolean) {
        viewModelScope.launch {
            preferencesRepository.updateDynamicColors(dynamicColors)
        }
    }

    fun updateRepeatGospel(repeatGospel: Boolean) {
        viewModelScope.launch {
            preferencesRepository.updateRepeatGospel(repeatGospel)
        }
    }

    fun updateKeepScreenAwake(keepScreenAwake: Boolean) {
        viewModelScope.launch {
            preferencesRepository.updateKeepScreenAwake(keepScreenAwake)
        }
    }
}