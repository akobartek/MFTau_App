package pl.mftau.mftau.model.local_db.repositories

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.room.withTransaction
import kotlinx.coroutines.flow.Flow
import pl.mftau.mftau.db.MFTauDatabase
import pl.mftau.mftau.db.daos.SongBookDao
import pl.mftau.mftau.db.entities.SongEntity
import pl.mftau.mftau.db.entities.SongFavouriteEntity
import pl.mftau.mftau.db.entities.SongPlaylistEntity

class SongBookRepository(application: Application) {

    private val db: MFTauDatabase
    private var mSongBookDao: SongBookDao

    init {
        db = MFTauDatabase.getInstance(application)!!
        mSongBookDao = db.songBookDao()
    }

    fun getAllSongs(): Flow<List<SongEntity>> = mSongBookDao.getAllSongs()

    @WorkerThread
    suspend fun insertSong(song: SongEntity) =
        mSongBookDao.insertSong(song)

    @WorkerThread
    suspend fun updateSong(song: SongEntity) =
        mSongBookDao.updateSong(song)

    @WorkerThread
    suspend fun deleteSong(song: SongEntity) {
        db.withTransaction {
            mSongBookDao.deleteSong(song)
            mSongBookDao.deleteFromPlaylist(false, song.id.toString())
            mSongBookDao.deleteFromFavourites(false, song.id.toString())
        }
    }


    fun getPlaylist(): Flow<List<SongPlaylistEntity>> = mSongBookDao.getPlaylist()

    fun getNumberOfSongsInPlaylist(): Int = mSongBookDao.getNumberOfSongsInPlaylist()

    @WorkerThread
    suspend fun insertToPlaylist(song: SongPlaylistEntity) =
        mSongBookDao.insertToPlaylist(song)

    @WorkerThread
    suspend fun deleteFromPlaylist(isInSongBook: Boolean, name: String) =
        mSongBookDao.deleteFromPlaylist(isInSongBook, name)

    @WorkerThread
    suspend fun clearPlaylist() =
        mSongBookDao.clearPlaylist()


    fun getFavouriteSongs(): Flow<List<SongFavouriteEntity>> = mSongBookDao.getFavouriteSongs()

    @WorkerThread
    suspend fun insertToFavourites(song: SongFavouriteEntity) =
        mSongBookDao.insertToFavourites(song)

    @WorkerThread
    suspend fun deleteFromFavourites(isInSongBook: Boolean, name: String) =
        mSongBookDao.deleteFromFavourites(isInSongBook, name)
}