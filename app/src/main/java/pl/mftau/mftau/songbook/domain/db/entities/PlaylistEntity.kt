package pl.mftau.mftau.songbook.domain.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.mftau.mftau.songbook.domain.model.Playlist

@Entity(tableName = "playlist")
data class PlaylistEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0L,
    @ColumnInfo(name = "name") var name: String
) {
    fun toModelObject() = Playlist(id, name)
}