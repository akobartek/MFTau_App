package pl.mftau.mftau.songbook.domain.usecase

import pl.mftau.mftau.songbook.data.DbSongBookRepository
import pl.mftau.mftau.songbook.domain.model.Playlist

class DeletePlaylistUseCase(private val dbRepository: DbSongBookRepository) {
    suspend operator fun invoke(playlist: Playlist) {
        dbRepository.deletePlaylist(playlist.toEntityObject())
    }
}