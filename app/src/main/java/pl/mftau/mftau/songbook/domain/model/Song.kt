package pl.mftau.mftau.songbook.domain.model

import com.google.firebase.firestore.Exclude
import kotlinx.serialization.Serializable
import pl.mftau.mftau.songbook.domain.db.entities.PlaylistSongEntity
import pl.mftau.mftau.songbook.domain.db.entities.SongEntity
import java.text.Normalizer
import kotlin.math.max
import kotlin.math.min

@Serializable
data class Song(
    val title: String = "",
    val text: String = "",
    val chords: String = "",
    @get:Exclude val databaseId: Long = 0L,
    @get:Exclude val topics: Set<SongTopic> = setOf(),
    @get:Exclude val isFavourite: Boolean = false,
    @get:Exclude val isOriginallyInSongBook: Boolean = true
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
        val updatedQuery = query.getStringToSearch()
        val updatedTitle = title.getStringToSearch()
        val updatedText = text.getStringToSearch()
        return updatedTitle.contains(updatedQuery) ||
                updatedText.contains(updatedQuery) ||
                updatedTitle.getSimilarity(updatedQuery) > SIMILARITY_THRESHOLD
    }

    private fun String.getStringToSearch(): String {
        val regex = Regex("[^A-Za-z0-9 ]")
        // TODO() -> iOS Normalizer should be implemented when going multiplatform
        return Normalizer.normalize(this, Normalizer.Form.NFKD)
            .replace("\n", " ")
            .replace(regex, "")
            .lowercase()
    }

    private fun String.getSimilarity(query: CharSequence): Double {
        val maxLength = max(this.length, query.length)
        val levenshteinDistance = levenshteinDistance(this, query)
        return (maxLength * 1.0 - levenshteinDistance) / maxLength * 1.0
    }

    private fun levenshteinDistance(x: CharSequence, y: CharSequence): Int {
        if (x == y) return 0
        if (x.isEmpty()) return y.length
        if (y.isEmpty()) return x.length

        val xLength = x.length + 1
        val yLength = y.length + 1

        var cost = Array(xLength) { it }
        var newCost = Array(xLength) { 0 }

        for (i in 1..<yLength) {
            newCost[0] = i

            for (j in 1..<xLength) {
                val match = if (x[j - 1] == y[i - 1]) 0 else 1

                val costReplace = cost[j - 1] + match
                val costInsert = cost[j] + 1
                val costDelete = newCost[j - 1] + 1

                newCost[j] = min(min(costInsert, costDelete), costReplace)
            }

            val swap = cost
            cost = newCost
            newCost = swap
        }
        return cost[xLength - 1]
    }
}
