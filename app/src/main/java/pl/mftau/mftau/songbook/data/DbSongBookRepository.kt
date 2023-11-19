package pl.mftau.mftau.songbook.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import pl.mftau.mftau.songbook.domain.db.SongBookDao
import pl.mftau.mftau.songbook.domain.db.entities.PlaylistEntity
import pl.mftau.mftau.songbook.domain.db.entities.PlaylistSongEntity
import pl.mftau.mftau.songbook.domain.db.entities.PlaylistWithSongCount
import pl.mftau.mftau.songbook.domain.db.entities.SongEntity

@WorkerThread
class DbSongBookRepository(private val songBookDao: SongBookDao) {
    fun getSongs(): Flow<List<SongEntity>> = songBookDao.getSongs()

    suspend fun upsertSong(song: SongEntity) = songBookDao.upsertSong(song)

    suspend fun deleteSong(song: SongEntity) = songBookDao.deleteSong(song)

    fun getPlayLists(): Flow<Map<PlaylistEntity, List<PlaylistSongEntity>>> =
        songBookDao.getPlayLists()

    fun getPlaylistsWithSongCount(): Flow<List<PlaylistWithSongCount>> =
        songBookDao.getPlaylistsWithSongCount()

    fun getSinglePlaylist(playlistId: Long): Flow<Map<PlaylistEntity, List<PlaylistSongEntity>>> =
        songBookDao.getSinglePlaylist(playlistId)

    suspend fun deletePlaylist(playlist: PlaylistEntity) = songBookDao.deletePlaylist(playlist)

    suspend fun insertToPlaylist(playlistSong: PlaylistSongEntity) =
        songBookDao.insertToPlaylist(playlistSong)

    suspend fun deleteFromPlaylist(playlistSong: PlaylistSongEntity) =
        songBookDao.deleteFromPlaylist(playlistSong)
}