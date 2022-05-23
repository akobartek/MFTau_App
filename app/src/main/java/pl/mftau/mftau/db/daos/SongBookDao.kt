package pl.mftau.mftau.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import pl.mftau.mftau.db.entities.SongEntity
import pl.mftau.mftau.db.entities.SongFavouriteEntity
import pl.mftau.mftau.db.entities.SongPlaylistEntity

@Dao
interface SongBookDao {

    @Query("SELECT * FROM songs;")
    fun getAllSongs(): LiveData<List<SongEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSong(song: SongEntity)

    @Update
    suspend fun updateSong(song: SongEntity)

    @Delete
    suspend fun deleteSong(song: SongEntity)


    @Query("SELECT * FROM playlist_songs;")
    fun getPlaylist(): LiveData<List<SongPlaylistEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToPlaylist(song: SongPlaylistEntity)

    @Delete
    suspend fun deleteFromPlaylist(song: SongPlaylistEntity)

    @Query("DELETE FROM playlist_songs")
    suspend fun clearPlaylist()


    @Query("SELECT * FROM favourite_songs;")
    fun getFavouriteSongs(): LiveData<List<SongFavouriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToFavourites(song: SongFavouriteEntity)

    @Delete
    suspend fun deleteFromFavourites(song: SongFavouriteEntity)
}