package pl.mftau.mftau.songbook.domain.usecase

import kotlinx.coroutines.flow.map
import pl.mftau.mftau.songbook.data.DbSongBookRepository

class GetPlaylistsUseCase(dbRepository: DbSongBookRepository) {
    val playlists by lazy {
        dbRepository.getPlaylistsWithSongCount().map { flowList ->
            flowList.map { entity -> entity.playlistEntity.toModelObject() to entity.count }
        }
    }
}