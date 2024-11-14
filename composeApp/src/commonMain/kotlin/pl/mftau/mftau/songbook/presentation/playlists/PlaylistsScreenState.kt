package pl.mftau.mftau.songbook.presentation.playlists

import pl.mftau.mftau.songbook.domain.model.Playlist

data class PlaylistsScreenState(
    val playlists: List<Pair<Playlist, Int>> = listOf(),
)