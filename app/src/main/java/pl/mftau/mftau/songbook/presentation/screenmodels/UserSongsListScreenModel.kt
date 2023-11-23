package pl.mftau.mftau.songbook.presentation.screenmodels

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.songbook.domain.model.Song
import pl.mftau.mftau.songbook.domain.usecase.DeleteSongUseCase
import pl.mftau.mftau.songbook.domain.usecase.GetUserSongsUseCase
import pl.mftau.mftau.songbook.domain.usecase.SaveSongUseCase

class UserSongsListScreenModel(
    private val getUserSongsUseCase: GetUserSongsUseCase,
    private val saveSongUseCase: SaveSongUseCase,
    private val deleteSongUseCase: DeleteSongUseCase
) : StateScreenModel<UserSongsListScreenModel.UserSongsScreenState>(UserSongsScreenState()) {

    data class UserSongsScreenState(
        val songs: List<Song>? = null,
        val songEditorVisible: Boolean = false,
        val songToEdit: Song? = null,
        val songSavedInfoVisible: Boolean = false
    )

    init {
        screenModelScope.launch(Dispatchers.IO) {
            getUserSongsUseCase.songs.collect { list ->
                mutableState.update { it.copy(songs = list) }
            }
        }
    }

    fun saveSong(song: Song) {
        screenModelScope.launch(Dispatchers.IO) {
            saveSongUseCase(song)
            toggleSongSavedInfoVisibility()
        }
    }

    fun toggleSongEditorVisibility(song: Song? = null) {
        mutableState.update {
            it.copy(
                songEditorVisible = !it.songEditorVisible,
                songToEdit = song
            )
        }
    }

    fun toggleSongSavedInfoVisibility() {
        mutableState.update { it.copy(songSavedInfoVisible = !it.songSavedInfoVisible) }
    }

    fun deleteSong(song: Song?) {
        if (song == null) return
        screenModelScope.launch(Dispatchers.IO) {
            deleteSongUseCase(song)
        }
    }
}