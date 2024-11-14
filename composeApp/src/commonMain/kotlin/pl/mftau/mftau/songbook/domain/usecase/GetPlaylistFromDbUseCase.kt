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
                    val index = songs.indexOfFirst { it.title == entity.title }
                    if (index > -1) {
                        val song = songs[index].copy(
                            databaseId = entity.id,
                            isFavourite = entity.isFavourite
                        )
                        songs[index] = song
                    }
                } else songs.add(entity.toDomainObject())
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