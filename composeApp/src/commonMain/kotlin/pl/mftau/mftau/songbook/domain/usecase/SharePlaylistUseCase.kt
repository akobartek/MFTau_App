package pl.mftau.mftau.songbook.domain.usecase

import pl.mftau.mftau.songbook.domain.model.Playlist
import pl.mftau.mftau.songbook.domain.repository.RemotePlaylistRepository

class SharePlaylistUseCase(
    private val remotePlaylistRepository: RemotePlaylistRepository
) {
    suspend fun getShareCode(playlist: Playlist) =
        if (!playlist.songs.all { it.isOriginallyInSongBook })
            remotePlaylistRepository.getShareCode(playlist)
        else
            playlist.songs.joinToString("~", "sb:") { it.title.split(".")[0] }
}