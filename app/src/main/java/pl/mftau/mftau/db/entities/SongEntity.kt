package pl.mftau.mftau.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class SongEntity(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "text") var text: String = "",
    @ColumnInfo(name = "chords") var chords: String = "",
    @ColumnInfo(name = "topics") var topics: String = ""
)