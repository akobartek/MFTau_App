package pl.mftau.mftau.songbook.presentation.songs

import pl.mftau.mftau.songbook.domain.model.Song

data class UserSongsScreenState(
    val songs: List<Song>? = null,
    val songEditorVisible: Boolean = false,
    val songToEdit: Song? = null,
)