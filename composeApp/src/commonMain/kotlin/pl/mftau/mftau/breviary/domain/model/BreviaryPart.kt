package pl.mftau.mftau.breviary.domain.model

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString

data class BreviaryPart(
    val breviaryPages: String = "",
    val text: AnnotatedString = buildAnnotatedString {  },
    val verses: String = ""
)