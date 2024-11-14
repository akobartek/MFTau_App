package pl.mftau.mftau.readings.domain

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import pl.mftau.mftau.readings.domain.model.Reading

abstract class WritingsRepository : ReadingsRepository() {

    override val readingId: String = "writing"

    fun getWritings(accentColor: Color) =
        writingsNames.zip(buildWritingsBook(accentColor)) { name, writingItem ->
            Reading(
                id = "${readingId}${writingsNames.indexOf(name)}",
                name = name,
                content = writingItem,
            )
        }

    protected abstract val writingsNames: Array<String>

    protected abstract fun buildWritingsBook(accentColor: Color): Array<AnnotatedString>
}