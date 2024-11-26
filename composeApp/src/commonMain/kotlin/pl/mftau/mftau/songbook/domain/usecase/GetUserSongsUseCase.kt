package pl.mftau.mftau.songbook.domain.usecase

import kotlinx.coroutines.flow.map
import pl.mftau.mftau.songbook.data.DbSongBookRepository

class GetUserSongsUseCase(private val dbRepository: DbSongBookRepository) {
    operator fun invoke() =
        dbRepository.getUserSongs().map { list -> list.map { it.toDomainObject() } }
}