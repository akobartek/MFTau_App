package pl.mftau.mftau.leaders.data

import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.firestore.Direction
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.orderBy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import pl.mftau.mftau.leaders.domain.model.Meeting
import pl.mftau.mftau.leaders.domain.repository.MeetingsRepository

class FirebaseMeetingsRepository(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : MeetingsRepository {

    override fun getMeetings(): Flow<List<Meeting>> =
        getCity()?.let { city ->
            firestore.collection(COLLECTION_CITIES)
                .document(city)
                .collection(COLLECTION_MEETINGS)
                .orderBy(FIELD_DATE, Direction.DESCENDING)
                .snapshots
                .map { querySnapshot ->
                    querySnapshot.documents.map { it.data() }
                }
        } ?: flowOf()

    override suspend fun saveMeeting(meeting: Meeting) {
        getCity()?.let { city ->
            firestore.collection(COLLECTION_CITIES)
                .document(city)
                .collection(COLLECTION_MEETINGS)
                .document(meeting.id)
                .set(meeting)
        }
    }

    override suspend fun deleteMeeting(meeting: Meeting) {
        getCity()?.let { city ->
            firestore.collection(COLLECTION_CITIES)
                .document(city)
                .collection(COLLECTION_MEETINGS)
                .document(meeting.id)
                .delete()
        }
    }

    override suspend fun clearMeetings() {
        getCity()?.let { city ->
            val snapshot = firestore.collection(COLLECTION_CITIES)
                .document(city)
                .collection(COLLECTION_MEETINGS)
                .get()
            snapshot.documents.forEach { it.reference.delete() }
        }
    }

    private fun getCity(): String? =
        auth.currentUser?.email?.split("@")?.get(0)

    companion object {
        private const val COLLECTION_CITIES = "cities"
        private const val COLLECTION_MEETINGS = "meetings"
        private const val FIELD_DATE = "date"
    }
}