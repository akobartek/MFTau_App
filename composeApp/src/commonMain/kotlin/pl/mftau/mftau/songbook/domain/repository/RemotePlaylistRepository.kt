package pl.mftau.mftau.songbook.domain.repository

import kotlinx.coroutines.flow.Flow
import pl.mftau.mftau.songbook.domain.model.Playlist

interface RemotePlaylistRepository {
    suspend fun getPlaylist(playlistId: String): Flow<Playlist?>

    suspend fun getShareCode(playlist: Playlist): String
}