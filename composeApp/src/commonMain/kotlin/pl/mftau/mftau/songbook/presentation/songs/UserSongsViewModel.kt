package pl.mftau.mftau.songbook.presentation.songs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.common.presentation.snackbars.SnackbarController
import pl.mftau.mftau.common.presentation.snackbars.SnackbarEvent
import pl.mftau.mftau.songbook.domain.model.Song
import pl.mftau.mftau.songbook.domain.usecase.DeleteSongUseCase
import pl.mftau.mftau.songbook.domain.usecase.GetUserSongsUseCase
import pl.mftau.mftau.songbook.domain.usecase.SaveSongUseCase

class UserSongsViewModel(
    private val getUserSongsUseCase: GetUserSongsUseCase,
    private val saveSongUseCase: SaveSongUseCase,
    private val deleteSongUseCase: DeleteSongUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(UserSongsScreenState())
    val state: StateFlow<UserSongsScreenState> = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getUserSongsUseCase().collect { list ->
                _state.update { it.copy(songs = list) }
            }
        }
    }

    fun saveSong(song: Song) {
        viewModelScope.launch(Dispatchers.IO) {
            saveSongUseCase(song)
            SnackbarController.sendEvent(SnackbarEvent.SongSaved)
        }
    }

    fun toggleSongEditorVisibility(song: Song? = null) {
        _state.update {
            it.copy(
                songEditorVisible = !it.songEditorVisible,
                songToEdit = song
            )
        }
    }

    fun deleteSong(song: Song?) {
        if (song == null) return
        viewModelScope.launch(Dispatchers.IO) {
            deleteSongUseCase(song)
        }
    }
}