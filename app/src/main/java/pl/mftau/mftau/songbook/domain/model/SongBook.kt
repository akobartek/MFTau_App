package pl.mftau.mftau.songbook.domain.model

data class SongBook(
    val songs: List<Song> = listOf(),
    val playlists: List<Playlist> = listOf(),
    val filteredSongs: List<Song> = songs
)
