package pl.mftau.mftau.core.data

data class UserPreferences(
    val colorTheme: ColorTheme = ColorTheme.SYSTEM,
    val dynamicColors: Boolean = false,
    val repeatGospel: Boolean = false,
    val keepSongBookAwake: Boolean = false
)