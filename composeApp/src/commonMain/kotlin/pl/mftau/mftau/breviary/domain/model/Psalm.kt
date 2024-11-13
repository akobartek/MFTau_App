package pl.mftau.mftau.breviary.domain.model

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString

data class Psalm(
    val breviaryPages: String? = null,
    val name: String? = null,
    val title: String? = null,
    val subtitle: String? = null,
    val part: String? = null,
    val antiphon1: AnnotatedString = buildAnnotatedString { },
    val text: AnnotatedString = buildAnnotatedString {  },
    val antiphon2: AnnotatedString = buildAnnotatedString { },
)