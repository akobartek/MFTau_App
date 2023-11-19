package pl.mftau.mftau.songbook.domain.usecase

import kotlinx.coroutines.flow.combine
import pl.mftau.mftau.songbook.data.DbSongBookRepository
import pl.mftau.mftau.songbook.domain.model.SongBook
import pl.mftau.mftau.songbook.domain.repository.TextsSongBookRepository

class GetSongBookUseCase(
    textsSongBookRepository: TextsSongBookRepository,
    dbRepository: DbSongBookRepository
) {
    val songBook = combine(
        dbRepository.getSongs(),
        dbRepository.getPlayLists()
    ) { dbSongs, dbPlaylists ->
        val songs = textsSongBookRepository.getSongs().toMutableList()
        dbSongs.forEach { entity ->
            if (entity.isOriginallyInSongBook) {
                val songBookSong = songs.find { it.title == entity.title }
                songBookSong?.databaseId = entity.id
                songBookSong?.isFavourite = entity.isFavourite
            } else songs.add(entity.toModelObject())
        }
        val playlists = dbPlaylists.map { (playlistEntity, playlistSongsEntities) ->
            val playlistSongs = playlistSongsEntities.map { entity ->
                if (entity.title != null) songs.first { it.title == entity.title }
                else songs.first { it.databaseId == entity.songId }
            }
            playlistEntity.toModelObject(playlistSongs)
        }
        SongBook(songs = songs, playlists = playlists)
    }
}