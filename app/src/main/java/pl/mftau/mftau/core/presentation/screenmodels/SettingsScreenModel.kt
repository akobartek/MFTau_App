package pl.mftau.mftau.core.presentation.screenmodels

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch
import pl.mftau.mftau.core.data.PreferencesRepository

class SettingsScreenModel(private val preferencesRepository: PreferencesRepository) : ScreenModel {

    val preferencesFlow = preferencesRepository.preferencesFlow

    fun updateNightMode(nightMode: Boolean) {
        screenModelScope.launch {
            preferencesRepository.updateNightMode(nightMode)
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

    fun updateKeepSongBookAwake(keepSongBookAwake: Boolean) {
        screenModelScope.launch {
            preferencesRepository.updateKeepSongBookAwake(keepSongBookAwake)
        }
    }
}