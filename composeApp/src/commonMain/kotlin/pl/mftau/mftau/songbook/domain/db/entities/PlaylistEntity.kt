package pl.mftau.mftau.songbook.domain.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import pl.mftau.mftau.songbook.domain.model.Playlist
import pl.mftau.mftau.songbook.domain.model.Song

@Entity(tableName = "playlist")
data class PlaylistEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "createdAt") val createdAt: Long = Clock.System.now().toEpochMilliseconds(),
) {
    fun toModelObject(songs: List<Song> = listOf()) =
        Playlist(
            id,
            name,
            Instant.fromEpochMilliseconds(createdAt).toLocalDateTime(TimeZone.currentSystemDefault()),
            songs,
        )
}