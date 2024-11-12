package pl.mftau.mftau.songbook.domain.usecase

import kotlinx.coroutines.flow.flow
import pl.mftau.mftau.songbook.domain.model.Playlist
import pl.mftau.mftau.songbook.domain.repository.RemotePlaylistRepository

class SharePlaylistUseCase(
    private val remotePlaylistRepository: RemotePlaylistRepository
) {
    suspend fun getShareCode(playlist: Playlist) =
        if (!playlist.songs.all { it.isOriginallyInSongBook })
            remotePlaylistRepository.getShareCode(playlist)
        else flow {
            playlist.songs
                .joinToString("~", "sb:") { it.title.split(".")[0] }
                .let { code -> emit(code) }
        }
}