package pl.mftau.mftau.songbook.domain.model

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import pl.mftau.mftau.songbook.data.db.entities.PlaylistEntity

data class Playlist(
    val id: Long = 0L,
    val name: String = "",
    val createdAt: LocalDateTime =
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
    val songs: List<Song> = listOf(),
) {
    fun toEntityObject() =
        PlaylistEntity(
            id,
            name,
            createdAt.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds(),
        )
}