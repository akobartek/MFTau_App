package pl.mftau.mftau.songbook.domain.model

import kotlinx.serialization.Serializable
import pl.mftau.mftau.common.utils.getSimilarity
import pl.mftau.mftau.common.utils.normalizeMultiplatform
import pl.mftau.mftau.songbook.domain.db.entities.PlaylistSongEntity
import pl.mftau.mftau.songbook.domain.db.entities.SongEntity

@Serializable
data class Song(
    val title: String = "",
    val text: String = "",
    val chords: String = "",
    val databaseId: Long = 0L,
    val topics: Set<SongTopic> = setOf(),
    val isFavourite: Boolean = false,
    val isOriginallyInSongBook: Boolean = true,
) {
    companion object {
        private const val SIMILARITY_THRESHOLD = 0.33
    }

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

    fun isMatchingQuery(query: String): Boolean {
        val updatedQuery = query.normalizeMultiplatform()
        val updatedTitle = title.normalizeMultiplatform()
        val updatedText = text.normalizeMultiplatform()
        return updatedTitle.contains(updatedQuery) ||
                updatedText.contains(updatedQuery) ||
                updatedTitle.getSimilarity(updatedQuery) > SIMILARITY_THRESHOLD
    }
}
