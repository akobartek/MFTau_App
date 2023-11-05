package pl.mftau.mftau.breviary.model

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString

data class Psalm(
    var breviaryPages: String? = null,
    var name: String = "",
    var title: String = "",
    var subtitle: String = "",
    var antiphon1: AnnotatedString = buildAnnotatedString { },
    var text: AnnotatedString = buildAnnotatedString {  },
    var antiphon2: AnnotatedString = buildAnnotatedString { }
)