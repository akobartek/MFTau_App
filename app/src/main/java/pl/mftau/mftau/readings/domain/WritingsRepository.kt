package pl.mftau.mftau.readings.domain

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString

abstract class WritingsRepository : ReadingsRepository() {
    fun getWritings(accentColor: Color) =
        writingsNames.zip(buildWritingsBook(accentColor)) { name, writingItem -> name to writingItem }

    protected abstract val writingsNames: Array<String>

    protected abstract fun buildWritingsBook(accentColor: Color): Array<AnnotatedString>
}