package pl.mftau.mftau.songbook.presentation.screenmodels

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.mftau.mftau.core.data.PreferencesRepository
import pl.mftau.mftau.core.utils.swap
import pl.mftau.mftau.songbook.domain.model.Playlist
import pl.mftau.mftau.songbook.domain.model.Song
import pl.mftau.mftau.songbook.domain.model.SongBookPreferences
import pl.mftau.mftau.songbook.domain.usecase.DeletePlaylistUseCase
import pl.mftau.mftau.songbook.domain.usecase.DeleteSongFromPlaylist
import pl.mftau.mftau.songbook.domain.usecase.GetPlaylistFromDbUseCase
import pl.mftau.mftau.songbook.domain.usecase.ImportPlaylistUseCase
import pl.mftau.mftau.songbook.domain.usecase.SharePlaylistUseCase
import pl.mftau.mftau.songbook.domain.usecase.UpdateSongsInPlaylistUseCase

class PlaylistDetailsScreenModel(
    private val preferencesRepository: PreferencesRepository,
    private val getPlaylistFromDbUseCase: GetPlaylistFromDbUseCase,
    private val updateSongsInPlaylistUseCase: UpdateSongsInPlaylistUseCase,
    private val deletePlaylistUseCase: DeletePlaylistUseCase,
    private val sharePlaylistUseCase: SharePlaylistUseCase,
    private val importPlaylistUseCase: ImportPlaylistUseCase,
    private val deleteSongFromPlaylist: DeleteSongFromPlaylist
) : StateScreenModel<PlaylistDetailsScreenModel.PlaylistState>(PlaylistState()) {

    data class PlaylistState(
        val playlist: Playlist? = null,
        val preferences: SongBookPreferences = SongBookPreferences(),
        val isImported: Boolean = false,
        val editMode: Boolean = false,
        val tempSongsList: List<Song> = listOf(),
        val importError: Boolean = false,
        val shareCode: String = "",
        val shareCodeDialogVisible: Boolean = false,
        val shareError: Boolean = false,
        val deletedSong: Pair<Song, Int>? = null
    )

    fun init(playlistId: Long?, importCode: String?) {
        screenModelScope.launch {
            preferencesRepository.songBookPreferencesFlow.collect { prefs ->
                mutableState.update { it.copy(preferences = prefs) }
            }
        }
        screenModelScope.launch {
            if (playlistId != null)
                getPlaylistFromDbUseCase.getPlaylist(playlistId).collect { playlist ->
                    mutableState.update {
                        it.copy(
                            playlist = playlist,
                            tempSongsList = playlist?.let { list -> updatedTempList(list) }
                                ?: listOf()
                        )
                    }
                }
            else if (importCode != null) {
                mutableState.update { it.copy(shareCode = importCode, isImported = true) }
                importPlaylistUseCase.getPlaylist(importCode).collect { playlist ->
                    mutableState.update {
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
        mutableState.update { it.copy(editMode = !it.editMode) }
    }

    fun swapItems(index1: Int, index2: Int) {
        mutableState.update { it.copy(tempSongsList = it.tempSongsList.swap(index1, index2)) }
    }

    fun toggleChordsVisibility() {
        screenModelScope.launch {
            val visibility = !state.value.preferences.areChordsVisible
            preferencesRepository.updateChordsVisibility(visibility)
        }
    }

    fun savePlaylist() {
        val playlist = state.value.playlist ?: return
        clearDeletedSong()
        screenModelScope.launch(Dispatchers.IO) {
            updateSongsInPlaylistUseCase(playlist, state.value.tempSongsList)
            toggleEditMode()
        }
    }

    fun deletePlaylist() {
        val playlist = state.value.playlist ?: return
        screenModelScope.launch(Dispatchers.IO) {
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

        screenModelScope.launch(Dispatchers.IO) {
            sharePlaylistUseCase.getShareCode(playlist).collect { code ->
                mutableState.update { it.copy(shareCode = code) }
            }
        }
    }

    fun toggleShareCodeDialogVisibility() {
        mutableState.update { it.copy(shareCodeDialogVisible = !it.shareCodeDialogVisible) }
    }

    fun toggleShareErrorFlag() {
        mutableState.update { it.copy(shareError = !it.shareError) }
    }

    fun toggleImportErrorFlag() {
        mutableState.update { it.copy(importError = !it.importError) }
    }

    fun deleteSong(song: Song) {
        val playlist = state.value.playlist ?: return
        val index = playlist.songs.indexOf(song)
        screenModelScope.launch(Dispatchers.IO) {
            deleteSongFromPlaylist.delete(song, playlist)
            mutableState.update {
                it.copy(deletedSong = song to index)
            }
        }
    }

    fun undeleteSong() {
        val deletedSong = state.value.deletedSong ?: return
        val playlist = state.value.playlist ?: return
        screenModelScope.launch(Dispatchers.IO) {
            deleteSongFromPlaylist.undelete(deletedSong.first, playlist, deletedSong.second)
            clearDeletedSong()
        }
    }

    fun clearDeletedSong() {
        mutableState.update { it.copy(deletedSong = null) }
    }
}