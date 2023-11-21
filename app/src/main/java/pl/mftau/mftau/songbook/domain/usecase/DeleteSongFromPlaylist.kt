package pl.mftau.mftau.songbook.domain.usecase

import pl.mftau.mftau.songbook.data.DbSongBookRepository
import pl.mftau.mftau.songbook.domain.model.Playlist
import pl.mftau.mftau.songbook.domain.model.Song

class DeleteSongFromPlaylist(private val dbRepository: DbSongBookRepository) {
    suspend fun delete(song: Song, playlist: Playlist) {
        dbRepository.deleteFromPlaylist(song.toPlaylistSong(playlist))
    }

    suspend fun undelete(song: Song, playlist: Playlist, position: Int) {
        dbRepository.insertToPlaylist(song.toPlaylistSong(playlist, position))
    }
}