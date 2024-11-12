package pl.mftau.mftau.readings.domain

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString

abstract class PrayersRepository : ReadingsRepository() {
    fun getPrayers(accentColor: Color) =
        prayersNames.zip(buildPrayersBook(accentColor)) { name, prayer ->
            name to buildAnnotatedString { append(prayer) }
        }

    protected abstract val prayersNames: Array<String>

    protected abstract fun buildPrayersBook(accentColor: Color): Array<CharSequence>
}