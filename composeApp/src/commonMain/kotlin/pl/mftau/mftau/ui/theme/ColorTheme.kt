package pl.mftau.mftau.ui.theme

import androidx.appcompat.app.AppCompatDelegate

enum class ColorTheme(val value: String) {
    SYSTEM("SYSTEM"), DARK("DARK"), LIGHT("LIGHT");

    fun setupAppCompatDelegate() {
        AppCompatDelegate.setDefaultNightMode(
            when (this) {
                DARK -> AppCompatDelegate.MODE_NIGHT_YES
                LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
                else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            }
        )
    }

    companion object {
        fun fromValue(value: String?) = when (value) {
            "DARK" -> DARK
            "LIGHT" -> LIGHT
            else -> SYSTEM
        }
    }
}