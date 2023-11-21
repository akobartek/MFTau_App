package pl.mftau.mftau.songbook.domain.usecase

import kotlinx.coroutines.flow.flow
import pl.mftau.mftau.songbook.domain.model.Playlist
import pl.mftau.mftau.songbook.domain.repository.RemotePlaylistRepository
import pl.mftau.mftau.songbook.domain.repository.TextsSongBookRepository

class ImportPlaylistUseCase(
    private val textsSongBookRepository: TextsSongBookRepository,
    private val remotePlaylistRepository: RemotePlaylistRepository
) {
    suspend fun getPlaylist(importCode: String) =
        if (importCode.startsWith("fs:"))
            remotePlaylistRepository.getPlaylist(importCode.split(":")[1])
        else flow {
            val songs = textsSongBookRepository.getSongs().toMutableList()
            val playlistSongs =
                importCode.split(":")[1].split("~").map { songNumber ->
                    songs[songNumber.toInt() - 1]
                }
            emit(Playlist(songs = playlistSongs))
        }
}