package pl.mftau.mftau.songbook.domain.usecase

import pl.mftau.mftau.songbook.data.DbSongBookRepository

class GetUserSongsUseCase(dbRepository: DbSongBookRepository) {
    val songs by lazy {
        dbRepository.getUserSongs()
    }
}