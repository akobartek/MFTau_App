package pl.mftau.mftau.breviary.domain.model

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString

data class Canticle(
    val breviaryPages: String = "",
    val name: String = "",
    val verses: String = "",
    val antiphon1: AnnotatedString = buildAnnotatedString { },
    val text: String = "",
    val antiphon2: AnnotatedString = buildAnnotatedString {  },
)