package pl.mftau.mftau.readings.presentation

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.core.data.PreferencesRepository
import pl.mftau.mftau.readings.domain.PrayersRepository
import pl.mftau.mftau.readings.domain.WritingsRepository

class ReadingsListScreenModel(
    private val prayersRepository: PrayersRepository,
    private val writingsRepository: WritingsRepository,
    private val preferencesRepository: PreferencesRepository
) : StateScreenModel<ReadingsListScreenModel.ReadingsListState>(ReadingsListState()) {
    data class ReadingsListState(
        val selectedTab: Int = 0,
        val prayers: List<Pair<String, AnnotatedString>> = listOf(),
        val writings: List<Pair<String, AnnotatedString>> = listOf()
    )

    init {
        screenModelScope.launch {
            val color = Color(preferencesRepository.getAccentColor())
            mutableState.update {
                it.copy(
                    prayers = prayersRepository.getPrayers(color),
                    writings = writingsRepository.getWritings(color)
                )
            }
        }
    }

    fun updateSelection(selection: Int) {
        mutableState.update { it.copy(selectedTab = selection) }
    }
}