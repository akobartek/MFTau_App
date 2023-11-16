package pl.mftau.mftau.readings.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString

class WritingsRepository {

    fun getWritings(accentColor: Color) =
        writingsNames.zip(buildWritingsBook(accentColor)) { name, writingItem -> name to writingItem }

    private val writingsNames = arrayOf(
        "List do wiernych",
        "Napomnienia",
        "Reguła niezatwierdzona",
        "Reguła zatwierdzona",
        "Testament",
        "Testament sieneński",
    )

    private fun buildWritingsBook(accentColor: Color) = arrayOf(
        buildAnnotatedString { },
        buildAnnotatedString { },
        buildAnnotatedString { },
        buildAnnotatedString { },
        buildAnnotatedString { },
        buildAnnotatedString { },
    )
}