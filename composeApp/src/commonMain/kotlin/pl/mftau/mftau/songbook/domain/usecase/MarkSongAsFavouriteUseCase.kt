package pl.mftau.mftau.songbook.domain.usecase

import pl.mftau.mftau.songbook.data.DbSongBookRepository
import pl.mftau.mftau.songbook.domain.model.Song

class MarkSongAsFavouriteUseCase(private val dbRepository: DbSongBookRepository) {
    suspend operator fun invoke(song: Song) {
        val updatedSong = song.copy(isFavourite = !song.isFavourite)
        if (updatedSong.isFavourite || !updatedSong.isOriginallyInSongBook)
            dbRepository.upsertSong(updatedSong)
        else
            dbRepository.deleteSong(updatedSong)
    }
}