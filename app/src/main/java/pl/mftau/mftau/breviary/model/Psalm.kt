package pl.mftau.mftau.breviary.model

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString

data class Psalm(
    var antiphon: AnnotatedString = buildAnnotatedString { },
    var number: String = "",
    var title: String = "",
    var subtitle: String = "",
    var text: AnnotatedString = buildAnnotatedString {  },
    var breviaryPages: String? = null
)