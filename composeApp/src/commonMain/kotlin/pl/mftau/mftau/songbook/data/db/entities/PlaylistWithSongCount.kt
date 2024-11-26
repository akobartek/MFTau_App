package pl.mftau.mftau.songbook.data.db.entities

import androidx.room.Embedded

data class PlaylistWithSongCount(
    @Embedded val playlistEntity: PlaylistEntity,
    val count: Int,
)
