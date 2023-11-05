package pl.mftau.mftau.breviary.model

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString

abstract class Breviary

data class Invitatory(
    val opening: AnnotatedString = buildAnnotatedString { },
    val psalm: Psalm = Psalm(),
    val ending: AnnotatedString = buildAnnotatedString { }
) : Breviary()

data class MajorHour(
    val opening: AnnotatedString = buildAnnotatedString { },
    val hymn: BreviaryPart = BreviaryPart(),
    val psalmody: Psalmody = Psalmody(),
    val reading: BreviaryPart = BreviaryPart(),
    val responsory: BreviaryPart = BreviaryPart(),
    val canticle: Canticle = Canticle(),
    val intercessions: BreviaryPart = BreviaryPart(),
    val lordsPrayer: AnnotatedString = buildAnnotatedString {  },
    val prayer: BreviaryPart = BreviaryPart(),
    val ending: AnnotatedString = buildAnnotatedString {  }
) : Breviary()

// TODO()
//data class MinorHour() : Breviary()