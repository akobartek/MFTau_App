package pl.mftau.mftau.db.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "playlist_songs")
data class SongPlaylistEntity(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "isInSongbook") @NonNull var isInSongbook: Boolean,
    @ColumnInfo(name = "name") @NonNull var name: String
)
