package pl.mftau.mftau.songbook.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.core.data.PreferencesRepository
import pl.mftau.mftau.songbook.domain.SongBookRepository
import pl.mftau.mftau.songbook.domain.model.SongBookPreferences

class SongBookScreenModel(
    private val songBookRepository: SongBookRepository,
    private val preferencesRepository: PreferencesRepository
) : StateScreenModel<SongBookScreenModel.SongBookState>(SongBookState()) {

    data class SongBookState(
        val songs: List<String> = listOf(),
        val preferences: SongBookPreferences = SongBookPreferences(),
    )

    init {
        screenModelScope.launch(Dispatchers.IO) {
            songBookRepository // TODO
            preferencesRepository.songBookPreferencesFlow.collect { prefs ->
                mutableState.update { it.copy(preferences = prefs) }
            }
        }
    }
}