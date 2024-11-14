package pl.mftau.mftau.common.data

data class SongBookPreferences(
    val areChordsVisible: Boolean = false,
    val fontSize: Int = DEFAULT_FONT_SIZE,
) {
    companion object {
        const val DEFAULT_FONT_SIZE = 15
    }
}