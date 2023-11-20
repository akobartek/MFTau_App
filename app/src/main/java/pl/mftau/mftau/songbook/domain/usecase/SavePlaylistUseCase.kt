package pl.mftau.mftau.songbook.domain.usecase

import pl.mftau.mftau.songbook.data.DbSongBookRepository
import pl.mftau.mftau.songbook.domain.db.entities.PlaylistEntity

class SavePlaylistUseCase(private val dbRepository: DbSongBookRepository) {
    suspend operator fun invoke(name: String) {
        dbRepository.upsertPlaylist(PlaylistEntity(name = name))
    }
}