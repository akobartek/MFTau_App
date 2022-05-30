package pl.mftau.mftau.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import pl.mftau.mftau.db.entities.SongEntity
import pl.mftau.mftau.db.entities.SongPlaylistEntity
import pl.mftau.mftau.model.local_db.Playlist
import pl.mftau.mftau.model.local_db.Song
import pl.mftau.mftau.model.local_db.repositories.FirebaseRepository
import pl.mftau.mftau.model.local_db.repositories.SongBookRepository
import pl.mftau.mftau.utils.FirestoreUtils
import pl.mftau.mftau.utils.SongBookUtils
import pl.mftau.mftau.utils.runDBOperation

class PlaylistViewModel(val app: Application) : AndroidViewModel(app) {

    private val mFirebaseRepository = FirebaseRepository(app)
    private val mSongBookRepository = SongBookRepository(app)

    val playlist = mSongBookRepository.getLivePlaylist()

    fun removeFromPlaylist(song: Song) =
        runDBOperation {
            val name = if (song.isOriginallyInSongBook) song.title else song.databaseId.toString()
            mSongBookRepository.deleteFromPlaylist(song.isOriginallyInSongBook, name)
        }

    fun clearPlaylist() =
        runDBOperation { mSongBookRepository.clearPlaylist() }

    fun getPlaylistShareCode(playlist: List<Song>): LiveData<String> {
        return if (playlist.all { it.isOriginallyInSongBook })
            MutableLiveData(playlist.joinToString("~", "sb ") { it.title.split(".")[0] })
        else {
            val firestoreList = playlist.map {
                SongEntity(title = it.title, text = it.text, chords = it.chords)
            }
            val hashMap = hashMapOf<String, Any>(FirestoreUtils.firestoreKeySongs to firestoreList)
            mFirebaseRepository.addPlaylist(hashMap)
        }
    }


    fun getPlaylistAsSongs(entities: List<SongPlaylistEntity>): List<Song> {
        val result = arrayListOf<Song>()
        var userSongs = listOf<SongEntity>()
        viewModelScope.launch {
            coroutineScope {
                if (entities.any { !it.isInSongBook })
                    userSongs = mSongBookRepository.getAllSongs()
            }
            result.addAll(entities.map { entity ->
                if (entity.isInSongBook) {
                    val index = SongBookUtils.songTitles.indexOf(entity.name)
                    Song(
                        entity.name,
                        SongBookUtils.songs[index],
                        SongBookUtils.chords[index],
                        isOriginallyInSongBook = true,
                        isOnPlaylist = true
                    )
                } else {
                    val userSong = userSongs.first { it.id.toString() == entity.name }
                    Song(
                        userSong.title,
                        userSong.text,
                        userSong.chords,
                        isOriginallyInSongBook = false,
                        isOnPlaylist = true,
                        databaseId = userSong.id!!,
                        databaseTopics = userSong.topics
                    )
                }
            })
        }
        return result
    }

    fun getImportedPlaylistFromSongbook(code: String): List<Song> =
        code.split("~").map {
            val songIndex = Integer.valueOf(it) - 1
            Song(
                SongBookUtils.songTitles[songIndex],
                SongBookUtils.songs[songIndex],
                SongBookUtils.chords[songIndex]
            )
        }

    fun getImportedFirestorePlaylist(playlistId: String): LiveData<Playlist?> =
        mFirebaseRepository.getPlaylistById(playlistId)

    fun convertPlaylistToSongList(playlist: Playlist?): List<Song> =
        playlist?.songs?.map { Song(it.title, it.text, it.chords) } ?: listOf()
}