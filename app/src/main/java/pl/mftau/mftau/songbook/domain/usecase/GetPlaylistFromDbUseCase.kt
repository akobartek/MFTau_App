package pl.mftau.mftau.songbook.domain.usecase

import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import pl.mftau.mftau.songbook.data.DbSongBookRepository
import pl.mftau.mftau.songbook.domain.repository.TextsSongBookRepository

class GetPlaylistFromDbUseCase(
    textsSongBookRepository: TextsSongBookRepository,
    private val dbRepository: DbSongBookRepository
) {
    private val playlists by lazy {
        combine(
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
                val playlistSongs = playlistSongsEntities
                    .sortedWith(compareBy { it.position })
                    .map { entity ->
                        if (entity.songTitle != null) songs.first { it.title == entity.songTitle }
                        else songs.first { it.databaseId == entity.songId }
                    }
                playlistEntity.toModelObject(playlistSongs)
            }
            playlists
        }
    }

    fun getPlaylist(playlistId: Long) =
        playlists.map { list -> list.firstOrNull { it.id == playlistId } }
}