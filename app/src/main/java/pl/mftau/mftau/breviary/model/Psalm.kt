package pl.mftau.mftau.breviary.model

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString

data class Psalm(
    var breviaryPages: String? = null,
    var name: String? = null,
    var title: String? = null,
    var subtitle: String? = null,
    var part: String? = null,
    var antiphon1: AnnotatedString = buildAnnotatedString { },
    var text: AnnotatedString = buildAnnotatedString {  },
    var antiphon2: AnnotatedString = buildAnnotatedString { }
)