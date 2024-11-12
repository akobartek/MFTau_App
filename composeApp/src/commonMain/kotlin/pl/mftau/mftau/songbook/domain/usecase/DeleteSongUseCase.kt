package pl.mftau.mftau.songbook.domain.usecase

import pl.mftau.mftau.songbook.data.DbSongBookRepository
import pl.mftau.mftau.songbook.domain.model.Song

class DeleteSongUseCase(private val dbRepository: DbSongBookRepository) {
    suspend operator fun invoke(song: Song) {
        dbRepository.deleteSong(song.toDbEntity())
    }
}