package pl.mftau.mftau.songbook.presentation.screenmodels

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.common.data.PreferencesRepository
import pl.mftau.mftau.songbook.domain.model.Playlist
import pl.mftau.mftau.songbook.domain.model.Song
import pl.mftau.mftau.songbook.domain.model.SongBookPreferences
import pl.mftau.mftau.songbook.domain.model.SongTopic
import pl.mftau.mftau.songbook.domain.usecase.GetSongBookUseCase
import pl.mftau.mftau.songbook.domain.usecase.MarkSongAsFavouriteUseCase
import pl.mftau.mftau.songbook.domain.usecase.SavePlaylistUseCase
import pl.mftau.mftau.songbook.domain.usecase.SaveSongUseCase
import pl.mftau.mftau.songbook.domain.usecase.SaveSongsInPlaylistUseCase

@OptIn(FlowPreview::class)
class SongBookListScreenModel(
    private val preferencesRepository: PreferencesRepository,
    private val getSongBookUseCase: GetSongBookUseCase,
    private val saveSongUseCase: SaveSongUseCase,
    private val markSongAsFavouriteUseCase: MarkSongAsFavouriteUseCase,
    private val savePlaylistUseCase: SavePlaylistUseCase,
    private val saveSongsInPlaylistUseCase: SaveSongsInPlaylistUseCase
) : StateScreenModel<SongBookListScreenModel.SongBookState>(SongBookState()) {

    data class SongBookState(
        val songs: List<Song> = listOf(),
        val playlists: List<Playlist> = listOf(),
        val preferences: SongBookPreferences = SongBookPreferences(),
        val isLoading: Boolean = true,
        val songSelectedToPlaylists: Song? = null,
        val songEditorVisible: Boolean = false,
        val songSavedInfoVisible: Boolean = false
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
            result = 31 * result + isLoading.hashCode()
            result = 31 * result + (songSelectedToPlaylists?.hashCode() ?: 0)
            result = 31 * result + songEditorVisible.hashCode()
            result = 31 * result + songSavedInfoVisible.hashCode()
            return result
        }
    }

    data class SearchBarState(
        val searchQuery: String = "",
        val selectedFilter: SongTopic = SongTopic.ALL
    ) {
        fun isChanged() = isQueryChanged() || isFilterChanged()
        fun isQueryChanged() = searchQuery.isNotBlank()
        fun isFilterChanged() = selectedFilter != SongTopic.ALL
    }

    private val _searchBarState = MutableStateFlow(SearchBarState())
    val searchBarState = _searchBarState.asStateFlow()

    init {
        screenModelScope.launch(Dispatchers.IO) {
            preferencesRepository.songBookPreferencesFlow.collect { prefs ->
                mutableState.update { it.copy(preferences = prefs) }
            }
        }
        screenModelScope.launch(Dispatchers.IO) {
            searchBarState
                .onEach { toggleLoadingState(true) }
                .debounce { searchState ->
                    if (searchState.isQueryChanged()) 1226L // PDK
                    else 0L
                }
                .combine(getSongBookUseCase.songBook) { searchState, songBook ->
                    val songs =
                        if (!searchState.isChanged()) songBook.songs
                        else filterSongs(searchState, songBook.songs)

                    mutableState.update {
                        it.copy(
                            songs = songs,
                            playlists = songBook.playlists,
                            isLoading = false
                        )
                    }
                }
                .onEach { toggleLoadingState(false) }
                .stateIn(this)
        }
    }

    private fun filterSongs(searchState: SearchBarState, allSongs: List<Song>): List<Song> {
        var filteredSongs = allSongs

        if (searchState.isFilterChanged())
            filteredSongs = filteredSongs.filter {
                if (searchState.selectedFilter == SongTopic.FAVOURITES) it.isFavourite
                else it.topics.contains(searchState.selectedFilter)
            }

        if (searchState.isQueryChanged())
            filteredSongs = filteredSongs.filter { it.isMatchingQuery(searchState.searchQuery) }

        return filteredSongs
    }

    fun onSearchQueryChange(query: String) {
        _searchBarState.update { it.copy(searchQuery = query) }
    }

    fun onSearchFilterChange(filter: SongTopic) {
        _searchBarState.update { it.copy(selectedFilter = filter) }
    }

    private fun toggleLoadingState(isLoading: Boolean) {
        mutableState.update { it.copy(isLoading = isLoading) }
    }

    fun toggleChordsVisibility() {
        screenModelScope.launch {
            val visibility = !state.value.preferences.areChordsVisible
            preferencesRepository.updateChordsVisibility(visibility)
        }
    }

    fun changeFontSize(newSize: Int) {
        screenModelScope.launch {
            preferencesRepository.updateSongBookFontSize(newSize)
        }
    }

    fun saveSong(song: Song) {
        screenModelScope.launch(Dispatchers.IO) {
            saveSongUseCase(song)
            toggleSongSavedInfoVisibility()
        }
    }

    fun toggleSongEditorVisibility() {
        mutableState.update { it.copy(songEditorVisible = !it.songEditorVisible) }
    }

    fun toggleSongSavedInfoVisibility() {
        mutableState.update { it.copy(songSavedInfoVisible = !it.songSavedInfoVisible) }
    }

    fun markSongAsFavourite(song: Song) {
        screenModelScope.launch(Dispatchers.IO) {
            markSongAsFavouriteUseCase(song)
        }
    }

    fun togglePlaylistDialogVisibility(song: Song?) {
        screenModelScope.launch(Dispatchers.IO) {
            mutableState.update { it.copy(songSelectedToPlaylists = song) }
        }
    }

    fun saveSongInPlaylists(playlists: List<Playlist>) {
        screenModelScope.launch(Dispatchers.IO) {
            saveSongsInPlaylistUseCase(
                song = state.value.songSelectedToPlaylists,
                allPlaylists = state.value.playlists,
                selectedPlaylists = playlists
            )
            togglePlaylistDialogVisibility(null)
        }
    }

    fun addNewPlaylist(name: String) {
        screenModelScope.launch(Dispatchers.IO) {
            savePlaylistUseCase(name)
        }
    }
}