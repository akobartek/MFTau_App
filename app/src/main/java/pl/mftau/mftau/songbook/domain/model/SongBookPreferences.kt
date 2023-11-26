package pl.mftau.mftau.songbook.domain.model

data class SongBookPreferences(
    val areChordsVisible: Boolean = false,
    val fontSize: Int = DEFAULT_FONT_SIZE,
) {
    companion object {
        const val DEFAULT_FONT_SIZE = 15
    }
}