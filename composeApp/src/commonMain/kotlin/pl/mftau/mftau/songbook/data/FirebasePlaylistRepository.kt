package pl.mftau.mftau.songbook.data

import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import pl.mftau.mftau.common.utils.getFirestoreDocument
import pl.mftau.mftau.common.utils.randomUUID
import pl.mftau.mftau.songbook.data.model.FirestoreSong.Companion.toFirestoreObject
import pl.mftau.mftau.songbook.domain.model.Playlist
import pl.mftau.mftau.songbook.domain.repository.RemotePlaylistRepository

class FirebasePlaylistRepository(
    private val firestore: FirebaseFirestore,
) : RemotePlaylistRepository {

    override suspend fun getPlaylist(playlistId: String): Flow<Playlist?> =
        firestore.getFirestoreDocument(
            collectionName = COLLECTION_PLAYLISTS,
            documentId = playlistId,
        )

    override suspend fun getShareCode(playlist: Playlist): String =
        playlist.songs
            .map { song -> song.toFirestoreObject() }
            .let { songs ->
                val docId = randomUUID()
                firestore.collection(COLLECTION_PLAYLISTS)
                    .document(docId)
                    .set(mapOf(FIELD_SONGS to songs))
                "fs:$docId"
            }

    companion object {
        private const val COLLECTION_PLAYLISTS = "playlists"
        private const val FIELD_SONGS = "songs"
    }
}