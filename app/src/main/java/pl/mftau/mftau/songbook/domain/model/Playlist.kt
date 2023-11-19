package pl.mftau.mftau.songbook.domain.model

import pl.mftau.mftau.songbook.domain.db.entities.PlaylistEntity

data class Playlist(
    val id: Long,
    val name: String,
    val songs: List<Song> = listOf()
) {
    fun toEntityObject() = PlaylistEntity(id, name)
}