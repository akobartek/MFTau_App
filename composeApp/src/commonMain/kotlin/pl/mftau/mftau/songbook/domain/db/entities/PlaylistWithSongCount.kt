package pl.mftau.mftau.songbook.domain.db.entities

import androidx.room.Embedded

data class PlaylistWithSongCount(
    @Embedded val playlistEntity: PlaylistEntity,
    val count: Int,
)
