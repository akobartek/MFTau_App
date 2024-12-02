package pl.mftau.mftau.songbook.domain.usecase

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import pl.mftau.mftau.songbook.domain.model.Playlist
import pl.mftau.mftau.songbook.domain.repository.RemotePlaylistRepository
import pl.mftau.mftau.songbook.domain.repository.TextsSongBookRepository

class ImportPlaylistUseCase(
    private val textsSongBookRepository: TextsSongBookRepository,
    private val remotePlaylistRepository: RemotePlaylistRepository
) {
    suspend fun getPlaylist(importCode: String) = when {
        importCode.split(":").getOrNull(1) == null ->
            flowOf(null)

        importCode.startsWith("fs:") ->
            importCode.split(":")[1].let { id ->
                remotePlaylistRepository.getPlaylist(id)
            }

        else -> flow {
            val songs = textsSongBookRepository.getSongs().toMutableList()
            val playlistSongs =
                importCode.split(":")[1].split("~")
                    .filter { it.toIntOrNull() != null }
                    .map { songNumber ->
                        songs[songNumber.toInt() - 1]
                    }
            emit(Playlist(songs = playlistSongs))
        }
    }
}