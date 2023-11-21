package pl.mftau.mftau.songbook.domain.model

import com.google.firebase.firestore.Exclude
import kotlinx.serialization.Serializable
import pl.mftau.mftau.songbook.domain.db.entities.PlaylistSongEntity
import pl.mftau.mftau.songbook.domain.db.entities.SongEntity

@Serializable
data class Song(
    @get:Exclude var databaseId: Long = 0L,
    var title: String = "",
    var text: String = "",
    var chords: String = "",
    @get:Exclude var topics: Set<SongTopic> = setOf(),
    @get:Exclude var isFavourite: Boolean = false,
    @get:Exclude var isOriginallyInSongBook: Boolean = true
) {
    fun toDbEntity() = SongEntity(
        id = databaseId,
        title = title,
        text = text,
        chords = chords,
        topics = topics.map { it.value }.joinToString(","),
        isFavourite = isFavourite,
        isOriginallyInSongBook = isOriginallyInSongBook
    )

    fun toPlaylistSong(playlist: Playlist, position: Int? = null) = PlaylistSongEntity(
        playlistId = playlist.id,
        songTitle = if (isOriginallyInSongBook) title else null,
        songId = if (isOriginallyInSongBook) null else databaseId,
        position = position ?: (playlist.songs.size + 1)
    )
}
