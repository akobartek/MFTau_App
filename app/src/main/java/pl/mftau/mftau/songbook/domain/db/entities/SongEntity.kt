package pl.mftau.mftau.songbook.domain.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.mftau.mftau.songbook.domain.model.Song
import pl.mftau.mftau.songbook.domain.model.SongTopic

@Entity(tableName = "song")
data class SongEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0L,
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "text") var text: String = "",
    @ColumnInfo(name = "chords") var chords: String = "",
    @ColumnInfo(name = "topics") var topics: String = "",
    @ColumnInfo(name = "isFavourite") var isFavourite: Boolean = false,
    @ColumnInfo(name = "isOriginallyInSongBook") var isOriginallyInSongBook: Boolean = false
) {
    fun toModelObject() = Song(
        databaseId = id,
        title = title,
        text = text,
        chords = chords,
        topics = topics.split(",").map { SongTopic.fromValue(it.toInt()) }.toSet(),
        isOriginallyInSongBook = isOriginallyInSongBook,
        isFavourite = isFavourite
    )
}