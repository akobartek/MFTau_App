package pl.mftau.mftau.songbook.domain.usecase

import pl.mftau.mftau.songbook.data.DbSongBookRepository
import pl.mftau.mftau.songbook.domain.model.Playlist
import pl.mftau.mftau.songbook.domain.model.Song

class SaveSongsInPlaylistUseCase(private val dbRepository: DbSongBookRepository) {
    suspend operator fun invoke(
        song: Song?,
        allPlaylists: List<Playlist>,
        selectedPlaylists: List<Playlist>
    ) {
        if (song == null) return

        // ADD ENTITIES TO DATABASE
        selectedPlaylists.filter { playlist ->
            !allPlaylists.first { it.id == playlist.id }.songs.contains(song)
        }.map { playlist -> song.toPlaylistSong(playlist) }
            .let { listToAdd -> dbRepository.insertToPlaylist(*listToAdd.toTypedArray()) }

        // REMOVE FROM DATABASE IF PLAYLIST WAS UNSELECTED
        allPlaylists.filter { playlist ->
            playlist.songs.contains(song) && !selectedPlaylists.contains(playlist)
        }.map { playlist -> song.toPlaylistSong(playlist) }
            .let { entities -> dbRepository.deleteFromPlaylist(entities) }
    }
}