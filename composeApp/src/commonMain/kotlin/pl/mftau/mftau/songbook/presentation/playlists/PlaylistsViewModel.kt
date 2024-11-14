package pl.mftau.mftau.songbook.presentation.playlists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.songbook.domain.usecase.GetPlaylistsUseCase
import pl.mftau.mftau.songbook.domain.usecase.SavePlaylistUseCase

class PlaylistsViewModel(
    private val getPlaylistsUseCase: GetPlaylistsUseCase,
    private val savePlaylistUseCase: SavePlaylistUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(PlaylistsScreenState())
    val state: StateFlow<PlaylistsScreenState> = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getPlaylistsUseCase.playlists.collect { list ->
                _state.update { it.copy(playlists = list) }
            }
        }
    }

    fun addNewPlaylist(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            savePlaylistUseCase(name)
        }
    }
}