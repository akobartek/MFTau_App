package pl.mftau.mftau.songbook.data.model

import kotlinx.serialization.Serializable
import pl.mftau.mftau.songbook.domain.model.Song

@Serializable
data class FirestoreSong(
    val title: String = "",
    val text: String = "",
    val chords: String = "",
) {
    companion object {
        fun Song.toFirestoreObject() = FirestoreSong(
            title = title,
            text = text,
            chords = chords,
        )
    }
}