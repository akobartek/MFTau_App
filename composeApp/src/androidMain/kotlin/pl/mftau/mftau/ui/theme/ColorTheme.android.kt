package pl.mftau.mftau.ui.theme

import androidx.appcompat.app.AppCompatDelegate
import pl.mftau.mftau.ui.theme.ColorTheme.DARK
import pl.mftau.mftau.ui.theme.ColorTheme.LIGHT

actual fun setupAppCompatDelegate(theme: ColorTheme) {
    AppCompatDelegate.setDefaultNightMode(
        when (theme) {
            DARK -> AppCompatDelegate.MODE_NIGHT_YES
            LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
            else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        }
    )
}