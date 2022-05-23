package pl.mftau.mftau.db.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class SongEntity(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "title") @NonNull var title: String,
    @ColumnInfo(name = "text") @NonNull var text: String,
    @ColumnInfo(name = "chords") @NonNull var chords: String,
    @ColumnInfo(name = "topics") @NonNull var topics: String
)