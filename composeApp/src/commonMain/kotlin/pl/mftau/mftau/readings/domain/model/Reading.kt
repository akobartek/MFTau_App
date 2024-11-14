package pl.mftau.mftau.readings.domain.model

import androidx.compose.ui.text.AnnotatedString

data class Reading(
    val id: String,
    val name: String,
    val content: AnnotatedString,
)
