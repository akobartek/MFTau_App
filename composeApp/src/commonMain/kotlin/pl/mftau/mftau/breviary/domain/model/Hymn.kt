package pl.mftau.mftau.breviary.domain.model

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString

data class Hymn(
    val breviaryPages: String = "",
    val text: AnnotatedString = buildAnnotatedString {  },
    val verses: String = "",
    val version2: AnnotatedString? = null
) {
    fun toBreviaryPart() = BreviaryPart(breviaryPages, text, verses)
}