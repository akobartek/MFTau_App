package pl.mftau.mftau.songbook.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.mftau.mftau.songbook.domain.model.Song
import pl.mftau.mftau.songbook.domain.model.SongTopic

@Entity(tableName = "song")
data class SongEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "text") val text: String = "",
    @ColumnInfo(name = "chords") val chords: String = "",
    @ColumnInfo(name = "topics") val topics: String = "",
    @ColumnInfo(name = "isFavourite") val isFavourite: Boolean = false,
    @ColumnInfo(name = "isOriginallyInSongBook") val isOriginallyInSongBook: Boolean = false,
) {
    fun toDomainObject() = Song(
        databaseId = id,
        title = title,
        text = text,
        chords = chords,
        topics = topics.split(",").map { SongTopic.fromValue(it.toInt()) }.toSet(),
        isFavourite = isFavourite,
        isOriginallyInSongBook = isOriginallyInSongBook,
    )
}