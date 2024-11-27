package pl.mftau.mftau.core.presentation

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

class AppViewModel(private val preferencesRepository: PreferencesRepository) : ViewModel() {

    private val _preferences = MutableStateFlow(UserPreferences())
    val preferences = _preferences.asStateFlow()

    init {
        viewModelScope.launch {
            preferencesRepository.userPreferencesFlow
                .stateIn(this, SharingStarted.WhileSubscribed(5000L), UserPreferences())
                .collect { preferences ->
                    _preferences.update { preferences }
                }
        }
    }
}