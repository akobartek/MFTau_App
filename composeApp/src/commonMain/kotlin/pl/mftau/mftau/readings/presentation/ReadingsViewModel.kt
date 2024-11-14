package pl.mftau.mftau.readings.presentation

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.readings.domain.PrayersRepository
import pl.mftau.mftau.readings.domain.WritingsRepository
import pl.mftau.mftau.readings.domain.model.Reading

class ReadingsViewModel(
    private val prayersRepository: PrayersRepository,
    private val writingsRepository: WritingsRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(ReadingsScreenState())
    val state: StateFlow<ReadingsScreenState> = _state.asStateFlow()

    fun init(color: Color) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    prayers = prayersRepository.getPrayers(color),
                    writings = writingsRepository.getWritings(color),
                )
            }
        }
    }

    fun onTabSelected(tab: Int) {
        _state.update { it.copy(selectedTab = tab) }
    }

    fun onReadingSelected(reading: Reading?) {
        _state.update { it.copy(selectedReading = reading) }
    }
}