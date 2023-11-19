package pl.mftau.mftau.songbook.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import pl.mftau.mftau.songbook.data.DbSongBookRepository
import pl.mftau.mftau.songbook.domain.model.SongBook
import pl.mftau.mftau.songbook.domain.repository.TextsSongBookRepository

class GetSongBookUseCase(
    textsSongBookRepository: TextsSongBookRepository,
    private val dbRepository: DbSongBookRepository
) {
    private val songsFromSongBook = textsSongBookRepository.getSongs()

    suspend operator fun invoke(): Flow<SongBook> =
        combine(dbRepository.getSong(), dbRepository.getPlayLists()) { dbSongs, dbPlaylists ->
            val songs = ArrayList(songsFromSongBook)
            dbSongs.forEach { entity ->
                if (entity.isOriginallyInSongBook)
                    songs.find { it.title == entity.title }?.isFavourite = entity.isFavourite
                else
                    songs.add(entity.toModelObject())
            }
            SongBook(songs, dbPlaylists.map { it.toModelObject() })
        }.catch { emit(SongBook(songsFromSongBook)) }.flowOn(Dispatchers.IO)
}