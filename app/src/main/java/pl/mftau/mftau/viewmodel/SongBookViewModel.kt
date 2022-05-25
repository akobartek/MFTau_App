package pl.mftau.mftau.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import pl.mftau.mftau.db.entities.SongEntity
import pl.mftau.mftau.db.entities.SongFavouriteEntity
import pl.mftau.mftau.db.entities.SongPlaylistEntity
import pl.mftau.mftau.model.local_db.Song
import pl.mftau.mftau.model.local_db.repositories.SongBookRepository
import pl.mftau.mftau.utils.SongBookUtils
import pl.mftau.mftau.utils.runDBOperation

class SongBookViewModel(val app: Application) : AndroidViewModel(app) {

    private val mSongBookRepository = SongBookRepository(app)

    val bottomSheetSong = MutableLiveData<Song?>()
    val searchQuery = MutableLiveData<String>(null)

    val userSongs = mSongBookRepository.getAllSongs()


    fun insertNewSong(songEntity: SongEntity) =
        runDBOperation { mSongBookRepository.insertSong(songEntity) }

    fun updateSong(songEntity: SongEntity) =
        runDBOperation { mSongBookRepository.updateSong(songEntity) }

    fun deleteSong(song: Song) =
        runDBOperation {
            val songEntity = SongEntity(
                id = song.databaseId,
                title = song.title,
                text = song.text,
                chords = song.chords,
                topics = song.databaseTopics
            )
            mSongBookRepository.deleteSong(songEntity)
        }


    fun addToPlaylist(song: Song) =
        runDBOperation {
            val songPlaylistEntity = SongPlaylistEntity(
                isInSongBook = song.isOriginallyInSongBook,
                name = if (song.isOriginallyInSongBook) song.title else song.databaseId.toString(),
                place = mSongBookRepository.getNumberOfSongsInPlaylist()
            )
            mSongBookRepository.insertToPlaylist(songPlaylistEntity)
        }

    fun removeFromPlaylist(song: Song) =
        runDBOperation {
            val name = if (song.isOriginallyInSongBook) song.title else song.databaseId.toString()
            mSongBookRepository.deleteFromPlaylist(song.isOriginallyInSongBook, name)
        }

    fun clearPlaylist() =
        runDBOperation { mSongBookRepository.clearPlaylist() }

    fun copyPlaylistToClipboard() {
        // TODO()
    }


    fun addToFavourites(song: Song) =
        runDBOperation {
            val songFavouriteEntity = SongFavouriteEntity(
                isInSongBook = song.isOriginallyInSongBook,
                name = if (song.isOriginallyInSongBook) song.title else song.databaseId.toString()
            )
            mSongBookRepository.insertToFavourites(songFavouriteEntity)
        }

    fun removeFromFavourites(song: Song) =
        runDBOperation {
            val name = if (song.isOriginallyInSongBook) song.title else song.databaseId.toString()
            mSongBookRepository.deleteFromFavourites(song.isOriginallyInSongBook, name)
        }


    fun getSongsToShow(topic: SongBookUtils.Topic = SongBookUtils.Topic.ALL): ArrayList<Song> {
        val songs = arrayListOf<Song>()
        val playlist = mSongBookRepository.getPlaylist()
        val favouriteSongs = mSongBookRepository.getFavouriteSongs()

        if (topic != SongBookUtils.Topic.FAVOURITES) {
            userSongs.value?.forEach {
                if (it.topics.contains(topic.toString()))
                    songs.add(
                        Song(
                            it.title, it.text, it.chords, false,
                            playlist.any { song ->
                                !song.isInSongBook && song.name == it.id!!.toString()
                            },
                            favouriteSongs.any { song ->
                                !song.isInSongBook && song.name == it.id!!.toString()
                            },
                            databaseId = it.id!!, databaseTopics = it.topics
                        )
                    )
            }
            songs.addAll(SongBookUtils.topics[topic]?.map {
                Song(
                    SongBookUtils.songTitles[it - 1],
                    SongBookUtils.songs[it - 1],
                    SongBookUtils.chords[it - 1],
                    true,
                    playlist.any { song ->
                        song.isInSongBook && song.name == SongBookUtils.songTitles[it - 1]
                    },
                    favouriteSongs.any { song ->
                        song.isInSongBook && song.name == SongBookUtils.songTitles[it - 1]
                    },
                )
            } ?: listOf())
        } else {
            favouriteSongs.forEach { fav ->
                if (fav.isInSongBook) {
                    val index = SongBookUtils.songTitles.indexOf(fav.name)
                    songs.add(
                        Song(
                            fav.name,
                            SongBookUtils.songs[index],
                            SongBookUtils.chords[index],
                            true,
                            playlist.any { song -> song.isInSongBook && song.name == fav.name },
                            true
                        )
                    )
                } else {
                    val userSong =
                        userSongs.value!!.first { it.id.toString() == fav.name }
                    songs.add(
                        Song(
                            userSong.title,
                            userSong.text,
                            userSong.chords,
                            false,
                            playlist.any { song -> !song.isInSongBook && song.name == fav.name },
                            true,
                            userSong.id!!,
                            userSong.topics
                        )
                    )
                }
            }
        }

        return ArrayList(songs.sortedWith(compareBy { it.isOriginallyInSongBook }))
    }
}