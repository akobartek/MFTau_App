package pl.mftau.mftau.songbook.domain.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "playlist_song",
    foreignKeys = [
        ForeignKey(
            onDelete = ForeignKey.CASCADE,
            entity = PlaylistEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("playlistId")
        ), ForeignKey(
            onDelete = ForeignKey.CASCADE,
            entity = SongEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("songId")
        )
    ]
)
data class PlaylistSongEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0L,
    @ColumnInfo(name = "playlistId", index = true) var playlistId: Long = 0L,
    @ColumnInfo(name = "songTitle") var title: String? = null,
    @ColumnInfo(name = "songId", index = true) var songId: Long? = null
)