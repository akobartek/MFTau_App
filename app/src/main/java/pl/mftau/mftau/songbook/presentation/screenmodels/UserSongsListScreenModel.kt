package pl.mftau.mftau.songbook.presentation.screenmodels

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.songbook.domain.model.Song
import pl.mftau.mftau.songbook.domain.usecase.GetUserSongsUseCase

class UserSongsListScreenModel(
    private val getUserSongsUseCase: GetUserSongsUseCase
) : StateScreenModel<UserSongsListScreenModel.UserSongsScreenState>(UserSongsScreenState()) {

    data class UserSongsScreenState(
        val songs: List<Song>? = null
    )

    init {
        screenModelScope.launch(Dispatchers.IO) {
            getUserSongsUseCase.songs.collect { list ->
                mutableState.update { it.copy(songs = list) }
            }
        }
    }
}