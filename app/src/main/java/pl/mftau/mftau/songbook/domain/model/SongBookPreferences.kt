package pl.mftau.mftau.songbook.domain.model

data class SongBookPreferences(
    val keepSongBookAwake: Boolean = false,
    val showCords: Boolean = false,
    val fontSize: Int = 18,
)