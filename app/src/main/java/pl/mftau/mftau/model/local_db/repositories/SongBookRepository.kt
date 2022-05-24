package pl.mftau.mftau.model.local_db.repositories

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import pl.mftau.mftau.db.MFTauDatabase
import pl.mftau.mftau.db.daos.SongBookDao
import pl.mftau.mftau.db.entities.SongEntity
import pl.mftau.mftau.db.entities.SongFavouriteEntity
import pl.mftau.mftau.db.entities.SongPlaylistEntity

class SongBookRepository(application: Application) {

    private var mSongBookDao: SongBookDao
    private var mAllSongs: LiveData<List<SongEntity>>

    init {
        val db = MFTauDatabase.getInstance(application)!!
        mSongBookDao = db.songBookDao()
        mAllSongs = mSongBookDao.getAllSongs()
    }


    fun getAllSongs(): LiveData<List<SongEntity>> = mAllSongs

    @WorkerThread
    suspend fun insertSong(song: SongEntity) =
        mSongBookDao.insertSong(song)

    @WorkerThread
    suspend fun updateSong(song: SongEntity) =
        mSongBookDao.updateSong(song)

    @WorkerThread
    suspend fun deleteSong(song: SongEntity) =
        mSongBookDao.deleteSong(song)


    fun getPlaylist(): List<SongPlaylistEntity> = mSongBookDao.getPlaylist()

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


    fun getFavouriteSongs(): List<SongFavouriteEntity> = mSongBookDao.getFavouriteSongs()

    @WorkerThread
    suspend fun insertToFavourites(song: SongFavouriteEntity) =
        mSongBookDao.insertToFavourites(song)

    @WorkerThread
    suspend fun deleteFromFavourites(isInSongBook: Boolean, name: String) =
        mSongBookDao.deleteFromFavourites(isInSongBook, name)
}