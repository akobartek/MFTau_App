package pl.mftau.mftau.songbook.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.core.data.PreferencesRepository
import pl.mftau.mftau.songbook.domain.model.Song
import pl.mftau.mftau.songbook.domain.model.SongBook
import pl.mftau.mftau.songbook.domain.model.SongBookPreferences
import pl.mftau.mftau.songbook.domain.model.SongTopic
import pl.mftau.mftau.songbook.domain.usecase.GetSongBookUseCase

class SongBookScreenModel(
    private val getSongBookUseCase: GetSongBookUseCase,
    private val preferencesRepository: PreferencesRepository
) : StateScreenModel<SongBookScreenModel.SongBookState>(SongBookState()) {

    data class SongBookState(
        val songBook: SongBook = SongBook(),
        val preferences: SongBookPreferences = SongBookPreferences(),
        val selectedFilter: SongTopic = SongTopic.ALL,
        val search: String = ""
    )

    init {
        screenModelScope.launch(Dispatchers.IO) {
            preferencesRepository.songBookPreferencesFlow
                .stateIn(this, SharingStarted.WhileSubscribed(5000L), SongBookPreferences())
                .collect { prefs -> mutableState.update { it.copy(preferences = prefs) } }
        }
        screenModelScope.launch(Dispatchers.IO) {
            getSongBookUseCase()
                .stateIn(this, SharingStarted.WhileSubscribed(5000L), SongBook())
                .collect { songBook -> mutableState.update { it.copy(songBook = songBook) } }
        }
    }

    fun toggleChordsVisibility() {
        screenModelScope.launch {
            val visibility = !state.value.preferences.areChordsVisible
            preferencesRepository.updateChordsVisibility(visibility)
        }
    }
}