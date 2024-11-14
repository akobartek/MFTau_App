package pl.mftau.mftau.ui.theme

enum class ColorTheme(val value: String) {
    SYSTEM("SYSTEM"), DARK("DARK"), LIGHT("LIGHT");

    companion object {
        fun fromValue(value: String?) = when (value) {
            "DARK" -> DARK
            "LIGHT" -> LIGHT
            else -> SYSTEM
        }
    }
}

expect fun setupAppCompatDelegate(theme: ColorTheme)