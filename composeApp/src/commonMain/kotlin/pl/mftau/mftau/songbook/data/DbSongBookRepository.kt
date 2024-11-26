package pl.mftau.mftau.songbook.data

import kotlinx.coroutines.flow.Flow
import pl.mftau.mftau.songbook.data.db.SongBookDao
import pl.mftau.mftau.songbook.data.db.entities.PlaylistEntity
import pl.mftau.mftau.songbook.data.db.entities.PlaylistSongEntity
import pl.mftau.mftau.songbook.data.db.entities.PlaylistWithSongCount
import pl.mftau.mftau.songbook.data.db.entities.SongEntity
import pl.mftau.mftau.songbook.domain.model.Song

class DbSongBookRepository(private val songBookDao: SongBookDao) {

    fun getSongs(): Flow<List<SongEntity>> = songBookDao.getSongs()

    fun getUserSongs(): Flow<List<SongEntity>> = songBookDao.getUserSongs()

    suspend fun upsertSong(song: Song) = songBookDao.upsertSong(song.toDbEntity())

    suspend fun deleteSong(song: Song) = songBookDao.deleteSong(song.databaseId)


    suspend fun upsertPlaylist(playlist: PlaylistEntity) = songBookDao.upsertSong(playlist)

    fun getPlayLists(): Flow<Map<PlaylistEntity, List<PlaylistSongEntity>>> =
        songBookDao.getPlayLists()

    fun getPlaylistsWithSongCount(): Flow<List<PlaylistWithSongCount>> =
        songBookDao.getPlaylistsWithSongCount()

    suspend fun deletePlaylist(playlist: PlaylistEntity) = songBookDao.deletePlaylist(playlist)

    suspend fun insertToPlaylist(vararg playlistSongs: PlaylistSongEntity) =
        songBookDao.insertToPlaylists(*playlistSongs)

    suspend fun clearPlaylistSongs(playlistId: Long) = songBookDao.clearPlaylistSongs(playlistId)

    suspend fun deleteFromPlaylist(entities: List<PlaylistSongEntity>) =
        entities.forEach { deleteFromPlaylist(it) }

    suspend fun deleteFromPlaylist(entity: PlaylistSongEntity) =
        if (entity.songTitle == null) songBookDao.deleteBySongId(entity.playlistId, entity.songId!!)
        else songBookDao.deleteBySongTitle(entity.playlistId, entity.songTitle)
}