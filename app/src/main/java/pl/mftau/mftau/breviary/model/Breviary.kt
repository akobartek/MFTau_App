package pl.mftau.mftau.breviary.model

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString

abstract class Breviary

data class Invitatory(
    val beginning: AnnotatedString = buildAnnotatedString { },
    val psalm: Psalm = Psalm(),
    val ending: AnnotatedString = buildAnnotatedString { }
) : Breviary()