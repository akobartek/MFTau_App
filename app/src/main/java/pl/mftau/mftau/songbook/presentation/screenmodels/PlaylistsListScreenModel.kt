package pl.mftau.mftau.songbook.presentation.screenmodels

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.songbook.domain.model.Playlist
import pl.mftau.mftau.songbook.domain.usecase.GetPlaylistsUseCase
import pl.mftau.mftau.songbook.domain.usecase.SavePlaylistUseCase

class PlaylistsListScreenModel(
    private val getPlaylistsUseCase: GetPlaylistsUseCase,
    private val savePlaylistUseCase: SavePlaylistUseCase,
) : StateScreenModel<PlaylistsListScreenModel.PlaylistsListState>(PlaylistsListState()) {

    data class PlaylistsListState(val playlists: List<Pair<Playlist, Int>> = listOf()) {
        override fun equals(other: Any?): Boolean {
            if (other is PlaylistsListState && (playlists !== other.playlists))
                return false
            return super.equals(other)
        }

        override fun hashCode(): Int {
            return playlists.hashCode()
        }
    }

    init {
        screenModelScope.launch(Dispatchers.IO) {
            getPlaylistsUseCase.playlists.collect { list ->
                mutableState.update { it.copy(playlists = list) }
            }
        }
    }

    fun addNewPlaylist(name: String) {
        screenModelScope.launch(Dispatchers.IO) {
            savePlaylistUseCase(name)
        }
    }
}