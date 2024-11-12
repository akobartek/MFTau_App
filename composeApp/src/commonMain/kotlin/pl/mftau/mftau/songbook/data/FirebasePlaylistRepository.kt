package pl.mftau.mftau.songbook.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import pl.mftau.mftau.songbook.domain.model.Playlist
import pl.mftau.mftau.songbook.domain.repository.RemotePlaylistRepository

class FirebasePlaylistRepository(
    private val firestore: FirebaseFirestore
) : RemotePlaylistRepository {
    override suspend fun getPlaylist(playlistId: String): Flow<Playlist?> =
        firestore.collection(COLLECTION_PLAYLISTS)
            .document(playlistId)
            .snapshots()
            .map { snapshot -> snapshot.toObject() }

    override suspend fun getShareCode(playlist: Playlist): Flow<String> = flow {
        playlist.songs
            .let { songs ->
                val collection = firestore.collection(COLLECTION_PLAYLISTS)
                val docId = collection.document().id
                collection.document(docId)
                    .set(mapOf(FIELD_SONGS to songs))
                    .await()
                emit("fs:$docId")
            }
    }

    companion object {
        private const val COLLECTION_PLAYLISTS = "playlists"
        private const val FIELD_SONGS = "songs"
    }
}