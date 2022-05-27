package pl.mftau.mftau.model.local_db

import pl.mftau.mftau.db.entities.SongEntity

data class Playlist(
    val songs: List<SongEntity> = listOf()
)