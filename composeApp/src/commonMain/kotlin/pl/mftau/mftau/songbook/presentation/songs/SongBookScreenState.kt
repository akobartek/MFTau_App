package pl.mftau.mftau.songbook.presentation.songs

import pl.mftau.mftau.common.data.SongBookPreferences
import pl.mftau.mftau.songbook.domain.model.Playlist
import pl.mftau.mftau.songbook.domain.model.Song

data class SongBookScreenState(
    val songs: List<Song> = listOf(),
    val playlists: List<Playlist> = listOf(),
    val preferences: SongBookPreferences = SongBookPreferences(),
    val isLoading: Boolean = true,
    val songSelectedToPlaylists: Song? = null,
    val songEditorVisible: Boolean = false,
)