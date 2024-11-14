package pl.mftau.mftau.songbook.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.mftau.mftau.songbook.domain.db.SongBookDao
import pl.mftau.mftau.songbook.domain.db.entities.PlaylistEntity
import pl.mftau.mftau.songbook.domain.db.entities.PlaylistSongEntity
import pl.mftau.mftau.songbook.domain.db.entities.PlaylistWithSongCount
import pl.mftau.mftau.songbook.domain.model.Song

class DbSongBookRepository(private val songBookDao: SongBookDao) {

    fun getSongs(): Flow<List<Song>> =
        songBookDao.getSongs().map { list -> list.map { it.toDomainObject() } }

    fun getUserSongs(): Flow<List<Song>> =
        songBookDao.getUserSongs().map { list -> list.map { it.toDomainObject() } }

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