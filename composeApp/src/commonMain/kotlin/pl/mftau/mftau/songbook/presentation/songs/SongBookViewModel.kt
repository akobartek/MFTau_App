package pl.mftau.mftau.songbook.presentation.songs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.common.data.PreferencesRepository
import pl.mftau.mftau.common.presentation.PdfOpener
import pl.mftau.mftau.common.presentation.launchPdf
import pl.mftau.mftau.common.presentation.snackbars.SnackbarController
import pl.mftau.mftau.common.presentation.snackbars.SnackbarEvent
import pl.mftau.mftau.songbook.domain.model.Playlist
import pl.mftau.mftau.songbook.domain.model.Song
import pl.mftau.mftau.songbook.domain.model.SongTopic
import pl.mftau.mftau.songbook.domain.usecase.GetSongBookUseCase
import pl.mftau.mftau.songbook.domain.usecase.MarkSongAsFavouriteUseCase
import pl.mftau.mftau.songbook.domain.usecase.SavePlaylistUseCase
import pl.mftau.mftau.songbook.domain.usecase.SaveSongUseCase
import pl.mftau.mftau.songbook.domain.usecase.SaveSongsInPlaylistUseCase

private const val SONG_BOOK_FILE_NAME = "spiewnik.pdf"

@OptIn(FlowPreview::class)
class SongBookViewModel(
    private val preferencesRepository: PreferencesRepository,
    private val getSongBookUseCase: GetSongBookUseCase,
    private val saveSongUseCase: SaveSongUseCase,
    private val markSongAsFavouriteUseCase: MarkSongAsFavouriteUseCase,
    private val savePlaylistUseCase: SavePlaylistUseCase,
    private val saveSongsInPlaylistUseCase: SaveSongsInPlaylistUseCase,
    private val pdfOpener: PdfOpener,
) : ViewModel() {

    private val _state = MutableStateFlow(SongBookScreenState())
    val state = _state.asStateFlow()

    private val _searchBarState = MutableStateFlow(SongBookSearchBarState())
    val searchBarState = _searchBarState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.songBookPreferencesFlow.collect { prefs ->
                _state.update { it.copy(preferences = prefs) }
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
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

                    _state.update {
                        it.copy(
                            songs = songs,
                            playlists = songBook.playlists,
                            isLoading = false,
                        )
                    }
                }
                .onEach { toggleLoadingState(false) }
                .stateIn(this)
        }
    }

    private fun filterSongs(searchState: SongBookSearchBarState, allSongs: List<Song>): List<Song> {
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

    fun openPdf() {
        viewModelScope.launchPdf(
            pdfOpener = pdfOpener,
            fileName = SONG_BOOK_FILE_NAME,
            onFailure = { togglePdfDialogVisibility(true) },
        )
    }

    fun togglePdfDialogVisibility(value : Boolean = false) {
        _state.update { it.copy(pdfDialogVisible = value) }
    }

    fun toggleChordsVisibility() {
        viewModelScope.launch {
            val visibility = !state.value.preferences.areChordsVisible
            preferencesRepository.updateChordsVisibility(visibility)
        }
    }

    fun changeFontSize(newSize: Int) {
        viewModelScope.launch {
            preferencesRepository.updateSongBookFontSize(newSize)
        }
    }

    fun saveSong(song: Song) {
        viewModelScope.launch(Dispatchers.IO) {
            saveSongUseCase(song)
            SnackbarController.sendEvent(SnackbarEvent.SongSaved)
        }
    }

    fun toggleSongEditorVisibility() {
        _state.update { it.copy(songEditorVisible = !it.songEditorVisible) }
    }

    fun markSongAsFavourite(song: Song) {
        viewModelScope.launch(Dispatchers.IO) {
            markSongAsFavouriteUseCase(song)
        }
    }

    fun togglePlaylistDialogVisibility(song: Song?) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(songSelectedToPlaylists = song) }
        }
    }

    fun saveSongInPlaylists(playlists: List<Playlist>) {
        viewModelScope.launch(Dispatchers.IO) {
            saveSongsInPlaylistUseCase(
                song = state.value.songSelectedToPlaylists,
                allPlaylists = state.value.playlists,
                selectedPlaylists = playlists
            )
            togglePlaylistDialogVisibility(null)
            SnackbarController.sendEvent(SnackbarEvent.PlaylistUpdated)
        }
    }

    fun addNewPlaylist(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            savePlaylistUseCase(name)
        }
    }

    private fun toggleLoadingState(isLoading: Boolean) {
        _state.update { it.copy(isLoading = isLoading) }
    }
}