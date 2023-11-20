package pl.mftau.mftau.songbook.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.core.data.PreferencesRepository
import pl.mftau.mftau.songbook.domain.model.Playlist
import pl.mftau.mftau.songbook.domain.model.Song
import pl.mftau.mftau.songbook.domain.model.SongBookPreferences
import pl.mftau.mftau.songbook.domain.model.SongTopic
import pl.mftau.mftau.songbook.domain.usecase.GetSongBookUseCase
import pl.mftau.mftau.songbook.domain.usecase.MarkSongAsFavouriteUseCase
import pl.mftau.mftau.songbook.domain.usecase.SavePlaylistUseCase
import pl.mftau.mftau.songbook.domain.usecase.SaveSongsInPlaylistUseCase

class SongBookScreenModel(
    private val preferencesRepository: PreferencesRepository,
    private val getSongBookUseCase: GetSongBookUseCase,
    private val markSongAsFavouriteUseCase: MarkSongAsFavouriteUseCase,
    private val savePlaylistUseCase: SavePlaylistUseCase,
    private val saveSongsInPlaylistUseCase: SaveSongsInPlaylistUseCase
) : StateScreenModel<SongBookScreenModel.SongBookState>(SongBookState()) {

    data class SongBookState(
        val songs: List<Song> = listOf(),
        val playlists: List<Playlist> = listOf(),
        val preferences: SongBookPreferences = SongBookPreferences(),
        val selectedFilter: SongTopic = SongTopic.ALL,
        val search: String = "",
        val songSelectedToPlaylists: Song? = null
    ) {
        override fun equals(other: Any?): Boolean {
            if (other is SongBookState && (songs !== other.songs || playlists !== other.playlists))
                return false
            return super.equals(other)
        }

        override fun hashCode(): Int {
            var result = songs.hashCode()
            result = 31 * result + playlists.hashCode()
            result = 31 * result + preferences.hashCode()
            result = 31 * result + selectedFilter.hashCode()
            result = 31 * result + search.hashCode()
            return result
        }
    }

    init {
        screenModelScope.launch {
            preferencesRepository.songBookPreferencesFlow.collect { prefs ->
                mutableState.update { it.copy(preferences = prefs) }
            }
        }
        screenModelScope.launch {
            getSongBookUseCase.songBook.collect { songBook ->
                mutableState.update {
                    it.copy(
                        songs = songBook.songs,
                        playlists = songBook.playlists,
                    )
                }
            }
        }
    }

    fun markSongAsFavourite(song: Song) {
        screenModelScope.launch(Dispatchers.IO) {
            markSongAsFavouriteUseCase(song)
        }
    }

    fun toggleChordsVisibility() {
        screenModelScope.launch(Dispatchers.IO) {
            val visibility = !state.value.preferences.areChordsVisible
            preferencesRepository.updateChordsVisibility(visibility)
        }
    }

    fun changeFontSize(newSize: Int) {
        screenModelScope.launch(Dispatchers.IO) {
            preferencesRepository.updateSongBookFontSize(newSize)
        }
    }

    fun togglePlaylistDialogVisibility(song: Song?) {
        screenModelScope.launch {
            mutableState.update { it.copy(songSelectedToPlaylists = song) }
        }
    }

    fun saveSongInPlaylists(playlists: List<Playlist>) {
        screenModelScope.launch {
            saveSongsInPlaylistUseCase(
                song = state.value.songSelectedToPlaylists,
                allPlaylists = state.value.playlists,
                selectedPlaylists = playlists
            )
            togglePlaylistDialogVisibility(null)
        }
    }

    fun addNewPlaylist(name: String) {
        screenModelScope.launch {
            savePlaylistUseCase(name)
        }
    }
}