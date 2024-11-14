package pl.mftau.mftau.songbook.presentation.playlists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.mftau.mftau.common.data.PreferencesRepository
import pl.mftau.mftau.common.presentation.snackbars.SnackbarController
import pl.mftau.mftau.common.presentation.snackbars.SnackbarEvent
import pl.mftau.mftau.common.utils.swap
import pl.mftau.mftau.songbook.domain.model.Playlist
import pl.mftau.mftau.songbook.domain.model.Song
import pl.mftau.mftau.songbook.domain.usecase.DeletePlaylistUseCase
import pl.mftau.mftau.songbook.domain.usecase.DeleteSongFromPlaylist
import pl.mftau.mftau.songbook.domain.usecase.GetPlaylistFromDbUseCase
import pl.mftau.mftau.songbook.domain.usecase.ImportPlaylistUseCase
import pl.mftau.mftau.songbook.domain.usecase.SharePlaylistUseCase
import pl.mftau.mftau.songbook.domain.usecase.UpdateSongsInPlaylistUseCase

class PlaylistDetailsViewModel(
    private val preferencesRepository: PreferencesRepository,
    private val getPlaylistFromDbUseCase: GetPlaylistFromDbUseCase,
    private val updateSongsInPlaylistUseCase: UpdateSongsInPlaylistUseCase,
    private val deletePlaylistUseCase: DeletePlaylistUseCase,
    private val sharePlaylistUseCase: SharePlaylistUseCase,
    private val importPlaylistUseCase: ImportPlaylistUseCase,
    private val deleteSongFromPlaylist: DeleteSongFromPlaylist,
) : ViewModel() {

    private val _state = MutableStateFlow(PlaylistDetailsScreenState())
    val state: StateFlow<PlaylistDetailsScreenState> = _state.asStateFlow()

    fun init(playlistId: Long?, importCode: String?) {
        viewModelScope.launch {
            preferencesRepository.songBookPreferencesFlow.collect { prefs ->
                _state.update { it.copy(preferences = prefs) }
            }
        }
        viewModelScope.launch {
            if (playlistId != null)
                getPlaylistFromDbUseCase.getPlaylist(playlistId).collect { playlist ->
                    _state.update {
                        it.copy(
                            playlist = playlist,
                            tempSongsList = playlist?.let { list -> updatedTempList(list) }
                                ?: listOf()
                        )
                    }
                }
            else if (importCode != null) {
                _state.update { it.copy(shareCode = importCode, isImported = true) }
                importPlaylistUseCase.getPlaylist(importCode).collect { playlist ->
                    _state.update {
                        if (playlist == null) it.copy(importError = true)
                        else it.copy(playlist = playlist)
                    }
                }
            }
        }
    }

    private fun updatedTempList(playlist: Playlist): List<Song> {
        val currentTempList = state.value.tempSongsList.toMutableList()
        playlist.songs.forEachIndexed { i, song ->
            if (!currentTempList.contains(song)) currentTempList.add(i, song)
        }
        return currentTempList.filter { playlist.songs.contains(it) }
    }

    fun toggleEditMode() {
        _state.update { it.copy(editMode = !it.editMode) }
    }

    fun swapItems(index1: Int, index2: Int) {
        _state.update { it.copy(tempSongsList = it.tempSongsList.swap(index1, index2)) }
    }

    fun toggleChordsVisibility() {
        viewModelScope.launch {
            val visibility = !state.value.preferences.areChordsVisible
            preferencesRepository.updateChordsVisibility(visibility)
        }
    }

    fun savePlaylist() {
        val playlist = state.value.playlist ?: return
        viewModelScope.launch(Dispatchers.IO) {
            updateSongsInPlaylistUseCase(playlist, state.value.tempSongsList)
            toggleEditMode()
        }
    }

    fun deletePlaylist() {
        val playlist = state.value.playlist ?: return
        viewModelScope.launch(Dispatchers.IO) {
            withContext(NonCancellable) {
                deletePlaylistUseCase(playlist)
            }
        }
    }

    fun generateShareCode() {
        val playlist = state.value.playlist
        if (playlist?.songs?.isEmpty() != false) {
            toggleShareErrorFlag()
            return
        }

        toggleShareCodeDialogVisibility()
        if (state.value.isImported) return

        viewModelScope.launch(Dispatchers.IO) {
            sharePlaylistUseCase.getShareCode(playlist).let { code ->
                _state.update { it.copy(shareCode = code) }
            }
        }
    }

    fun toggleShareCodeDialogVisibility() {
        _state.update { it.copy(shareCodeDialogVisible = !it.shareCodeDialogVisible) }
    }

    fun toggleShareErrorFlag() {
        _state.update { it.copy(shareError = !it.shareError) }
    }

    fun toggleImportErrorFlag() {
        _state.update { it.copy(importError = !it.importError) }
    }

    fun deleteSong(song: Song) {
        val playlist = state.value.playlist ?: return
        val index = playlist.songs.indexOf(song)

        viewModelScope.launch(Dispatchers.IO) {
            deleteSongFromPlaylist.delete(song, playlist)
            SnackbarController.sendEvent(
                SnackbarEvent.DeleteSongFromPlaylist(
                    undo = { deleteSongFromPlaylist.undelete(song, playlist, index) },
                )
            )
        }
    }
}