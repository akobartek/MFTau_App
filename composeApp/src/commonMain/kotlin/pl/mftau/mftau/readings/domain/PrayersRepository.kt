package pl.mftau.mftau.readings.domain

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import pl.mftau.mftau.readings.domain.model.Reading

abstract class PrayersRepository : ReadingsRepository() {

    override val readingId: String = "prayer"

    fun getPrayers(accentColor: Color) =
        prayersNames.zip(buildPrayersBook(accentColor)) { name, prayer ->
            Reading(
                id = "${readingId}${prayersNames.indexOf(name)}",
                name = name,
                content = AnnotatedString(prayer.toString()),
            )
        }

    protected abstract val prayersNames: Array<String>

    protected abstract fun buildPrayersBook(accentColor: Color): Array<CharSequence>
}