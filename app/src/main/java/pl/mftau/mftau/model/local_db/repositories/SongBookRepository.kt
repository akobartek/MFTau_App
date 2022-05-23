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
    private var mPlaylist: LiveData<List<SongPlaylistEntity>>
    private var mFavouriteSongs: LiveData<List<SongFavouriteEntity>>

    init {
        val db = MFTauDatabase.getInstance(application)!!
        mSongBookDao = db.songBookDao()
        mAllSongs = mSongBookDao.getAllSongs()
        mPlaylist = mSongBookDao.getPlaylist()
        mFavouriteSongs = mSongBookDao.getFavouriteSongs()
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


    fun getPlaylist(): LiveData<List<SongPlaylistEntity>> = mPlaylist

    @WorkerThread
    suspend fun insertToPlaylist(song: SongPlaylistEntity) =
        mSongBookDao.insertToPlaylist(song)

    @WorkerThread
    suspend fun deleteFromPlaylist(song: SongPlaylistEntity) =
        mSongBookDao.deleteFromPlaylist(song)

    @WorkerThread
    suspend fun clearPlaylist() =
        mSongBookDao.clearPlaylist()


    fun getFavouriteSongs(): LiveData<List<SongFavouriteEntity>> = mFavouriteSongs

    @WorkerThread
    suspend fun insertToFavourites(song: SongFavouriteEntity) =
        mSongBookDao.insertToFavourites(song)

    @WorkerThread
    suspend fun deleteFromFavourites(song: SongFavouriteEntity) =
        mSongBookDao.deleteFromFavourites(song)
}