package pl.mftau.mftau.songbook.domain.usecase

import kotlinx.coroutines.flow.map
import pl.mftau.mftau.songbook.data.DbSongBookRepository

class GetUserSongsUseCase(dbRepository: DbSongBookRepository) {
    val songs by lazy {
        dbRepository.getUserSongs().map { flowList ->
            flowList.map { entity -> entity.toModelObject() }
        }
    }
}