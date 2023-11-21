package pl.mftau.mftau.songbook.domain.usecase

import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext
import pl.mftau.mftau.songbook.data.DbSongBookRepository
import pl.mftau.mftau.songbook.domain.model.Playlist
import pl.mftau.mftau.songbook.domain.model.Song

class UpdateSongsInPlaylistUseCase(private val dbRepository: DbSongBookRepository) {
    suspend operator fun invoke(
        playlist: Playlist,
        songs: List<Song>
    ) {
        dbRepository.clearPlaylistSongs(playlist.id)
        withContext(NonCancellable) {
            songs.mapIndexed { index, song ->
                song.toPlaylistSong(playlist, index)
            }.let { list ->
                dbRepository.insertToPlaylist(*list.toTypedArray())
            }
        }
    }
}