package pl.mftau.mftau.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "playlist_songs")
data class SongPlaylistEntity(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "isInSongBook") var isInSongBook: Boolean,
    @ColumnInfo(name = "name") var name: String
)
