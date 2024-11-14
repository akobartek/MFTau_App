package pl.mftau.mftau.songbook.presentation.playlists

import pl.mftau.mftau.common.data.SongBookPreferences
import pl.mftau.mftau.songbook.domain.model.Playlist
import pl.mftau.mftau.songbook.domain.model.Song

data class PlaylistDetailsScreenState(
    val playlist: Playlist? = null,
    val preferences: SongBookPreferences = SongBookPreferences(),
    val isImported: Boolean = false,
    val editMode: Boolean = false,
    val tempSongsList: List<Song> = listOf(),
    val importError: Boolean = false,
    val shareCode: String = "",
    val shareCodeDialogVisible: Boolean = false,
    val shareError: Boolean = false,
)