package pl.mftau.mftau.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import pl.mftau.mftau.db.entities.SongEntity
import pl.mftau.mftau.db.entities.SongFavouriteEntity
import pl.mftau.mftau.db.entities.SongPlaylistEntity

@Dao
interface SongBookDao {

    @Query("SELECT * FROM songs;")
    fun getAllSongsAsLiveData(): LiveData<List<SongEntity>>

    @Query("SELECT * FROM songs;")
    suspend fun getAllSongs(): List<SongEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSong(song: SongEntity)

    @Update
    suspend fun updateSong(song: SongEntity)

    @Delete
    suspend fun deleteSong(song: SongEntity)


    @Query("SELECT * FROM playlist_songs;")
    fun getPlaylist(): List<SongPlaylistEntity>

    @Query("SELECT * FROM playlist_songs;")
    fun getPlaylistAsLiveData(): LiveData<List<SongPlaylistEntity>>

    @Query("SELECT COUNT(*) FROM playlist_songs;")
    fun getNumberOfSongsInPlaylist(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToPlaylist(song: SongPlaylistEntity)

    @Query("DELETE FROM playlist_songs WHERE isInSongBook = :isInSongBook AND name = :name")
    suspend fun deleteFromPlaylist(isInSongBook: Boolean, name: String)

    @Query("DELETE FROM playlist_songs")
    suspend fun clearPlaylist()


    @Query("SELECT * FROM favourite_songs;")
    fun getFavouriteSongs(): List<SongFavouriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToFavourites(song: SongFavouriteEntity)

    @Query("DELETE FROM favourite_songs WHERE isInSongBook = :isInSongBook AND name = :name")
    suspend fun deleteFromFavourites(isInSongBook: Boolean, name: String)
}