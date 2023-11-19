package pl.mftau.mftau.songbook.domain.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import pl.mftau.mftau.songbook.domain.db.entities.SongEntity
import pl.mftau.mftau.songbook.domain.db.entities.PlaylistEntity
import pl.mftau.mftau.songbook.domain.db.entities.PlaylistSongEntity
import pl.mftau.mftau.songbook.domain.db.entities.PlaylistWithSongCount

@Dao
interface SongBookDao {
    @Query("SELECT * FROM song;")
    fun getSongs(): Flow<List<SongEntity>>

    @Upsert
    suspend fun upsertSong(song: SongEntity)

    @Delete
    suspend fun deleteSong(song: SongEntity)


    @Query("SELECT * from playlist")
    fun getPlayLists(): Flow<List<PlaylistEntity>>

    @Query(
        "SELECT p.*, ps.count FROM playlist p " +
                "JOIN ( " +
                "   SELECT playlistId, COUNT(playlistId) count FROM playlist_song " +
                "   GROUP BY playlistId) ps " +
                "ON p.id = ps.playlistId"
    )
    fun getPlaylistsWithSongCount(): Flow<List<PlaylistWithSongCount>>

    @Query(
        "SELECT * from playlist " +
                "JOIN playlist_song ON playlist.id = playlist_song.playlistId " +
                "WHERE playlist.id = :playlistId"
    )
    fun getPlayListWithSongs(playlistId: Long): Flow<Map<PlaylistEntity, List<PlaylistSongEntity>>>

    @Delete
    suspend fun deletePlaylist(playlist: PlaylistEntity)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToPlaylist(playlistSong: PlaylistSongEntity)

    @Delete
    suspend fun deleteFromPlaylist(playlistSong: PlaylistSongEntity)
}