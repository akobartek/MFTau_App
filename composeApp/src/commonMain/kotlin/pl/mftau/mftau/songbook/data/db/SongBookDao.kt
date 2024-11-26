package pl.mftau.mftau.songbook.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import pl.mftau.mftau.songbook.data.db.entities.SongEntity
import pl.mftau.mftau.songbook.data.db.entities.PlaylistEntity
import pl.mftau.mftau.songbook.data.db.entities.PlaylistSongEntity
import pl.mftau.mftau.songbook.data.db.entities.PlaylistWithSongCount

@Dao
interface SongBookDao {
    @Query("SELECT * FROM song ORDER BY song.title;")
    fun getSongs(): Flow<List<SongEntity>>

    @Query("SELECT * FROM song WHERE isOriginallyInSongBook = 0 ORDER BY song.title")
    fun getUserSongs(): Flow<List<SongEntity>>

    @Upsert
    suspend fun upsertSong(song: SongEntity)

    @Query("DELETE FROM song WHERE id = :songId;")
    suspend fun deleteSong(songId: Long)


    @Upsert
    suspend fun upsertSong(playlist: PlaylistEntity)

    @Query("SELECT * from playlist LEFT JOIN playlist_song ON playlist.id = playlist_song.playlistId;")
    fun getPlayLists(): Flow<Map<PlaylistEntity, List<PlaylistSongEntity>>>

    @Query(
        "SELECT p.*, ps.count FROM playlist p " +
                "LEFT JOIN ( " +
                "   SELECT playlistId, COUNT(playlistId) count FROM playlist_song " +
                "   GROUP BY playlistId) ps " +
                "ON p.id = ps.playlistId;"
    )
    fun getPlaylistsWithSongCount(): Flow<List<PlaylistWithSongCount>>

    @Delete
    suspend fun deletePlaylist(playlist: PlaylistEntity)


    @Upsert
    suspend fun insertToPlaylists(vararg playlistSongs: PlaylistSongEntity)

    @Query("DELETE FROM playlist_song WHERE playlistId = :playlistId;")
    suspend fun clearPlaylistSongs(playlistId: Long)

    @Query("DELETE FROM playlist_song WHERE playlistId = :playlistId AND songTitle = :songTitle;")
    suspend fun deleteBySongTitle(playlistId: Long, songTitle: String)

    @Query("DELETE FROM playlist_song WHERE playlistId = :playlistId AND songId = :songId;")
    suspend fun deleteBySongId(playlistId: Long, songId: Long)
}