package pl.mftau.mftau.songbook.domain.model

import pl.mftau.mftau.songbook.domain.db.entities.SongEntity

data class Song(
    var databaseId: Long = 0L,
    var title: String,
    var text: String,
    var chords: String,
    var topics: Set<SongTopic> = setOf(),
    var isOriginallyInSongBook: Boolean = true,
    var isFavourite: Boolean = false,
) {
    fun toDbEntity() = SongEntity(
        id = databaseId,
        title = title,
        text = text,
        chords = chords,
        topics = topics.map { it.value }.joinToString(","),
        isFavourite = isOriginallyInSongBook,
        isOriginallyInSongBook = isFavourite
    )
}
