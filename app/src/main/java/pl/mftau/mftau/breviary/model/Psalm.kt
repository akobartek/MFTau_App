package pl.mftau.mftau.breviary.model

import android.text.SpannableString

data class Psalm(
    val antiphon: SpannableString,
    val number: String,
    val title: String,
    val subtitle: String,
    val text: String
)