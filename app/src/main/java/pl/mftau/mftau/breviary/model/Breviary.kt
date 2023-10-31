package pl.mftau.mftau.breviary.model

import android.text.SpannableString

abstract class Breviary

data class Invitatory(
    val beginning: SpannableString,
    val psalm: Psalm,
    val ending: SpannableString
) : Breviary()