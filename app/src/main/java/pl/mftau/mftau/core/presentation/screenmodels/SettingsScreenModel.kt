package pl.mftau.mftau.core.presentation.screenmodels

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch
import pl.mftau.mftau.ui.theme.ColorTheme
import pl.mftau.mftau.common.data.PreferencesRepository

class SettingsScreenModel(private val preferencesRepository: PreferencesRepository) : ScreenModel {

    val preferencesFlow = preferencesRepository.userPreferencesFlow

    fun updateNightMode(value: String) {
        screenModelScope.launch {
            preferencesRepository.updateTheme(ColorTheme.fromValue(value))
        }
    }

    fun updateDynamicColors(dynamicColors: Boolean) {
        screenModelScope.launch {
            preferencesRepository.updateDynamicColors(dynamicColors)
        }
    }

    fun updateRepeatGospel(repeatGospel: Boolean) {
        screenModelScope.launch {
            preferencesRepository.updateRepeatGospel(repeatGospel)
        }
    }

    fun updateKeepScreenAwake(keepScreenAwake: Boolean) {
        screenModelScope.launch {
            preferencesRepository.updateKeepScreenAwake(keepScreenAwake)
        }
    }
}