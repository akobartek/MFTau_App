package pl.mftau.mftau.common.data

import pl.mftau.mftau.ui.theme.ColorTheme

data class UserPreferences(
    val notificationsAsked: Boolean = false,
    val colorTheme: ColorTheme = ColorTheme.SYSTEM,
    val dynamicColors: Boolean = false,
    val repeatGospel: Boolean = false,
    val keepScreenAwake: Boolean = false
)