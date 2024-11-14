package pl.mftau.mftau.leaders.data

import dev.gitlive.firebase.firestore.Direction
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.orderBy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import pl.mftau.mftau.auth.domain.AuthRepository
import pl.mftau.mftau.leaders.domain.model.InvalidUserException
import pl.mftau.mftau.leaders.domain.model.Meeting
import pl.mftau.mftau.leaders.domain.repository.MeetingsRepository

@OptIn(ExperimentalCoroutinesApi::class)
class FirebaseMeetingsRepository(
    private val firestore: FirebaseFirestore,
    private val auth: AuthRepository
) : MeetingsRepository {

    override fun getMeetings(): Flow<List<Meeting>> =
        auth.currentUser.flatMapLatest { user ->
            val city = user?.email?.split("@")?.get(0) ?: throw InvalidUserException()
            firestore.collection(COLLECTION_CITIES)
                .document(city)
                .collection(COLLECTION_MEETINGS)
                .orderBy(FIELD_DATE, Direction.DESCENDING)
                .snapshots
                .map { querySnapshot ->
                    querySnapshot.documents.map { it.data() }
                }
        }

    override suspend fun saveMeeting(meeting: Meeting) =
        firestore.collection(COLLECTION_CITIES)
            .document(getCity())
            .collection(COLLECTION_MEETINGS)
            .document(meeting.id)
            .set(meeting)

    override suspend fun deleteMeeting(meeting: Meeting) =
        firestore.collection(COLLECTION_CITIES)
            .document(getCity())
            .collection(COLLECTION_MEETINGS)
            .document(meeting.id)
            .delete()

    override suspend fun clearMeetings() {
        val snapshot = firestore.collection(COLLECTION_CITIES)
            .document(getCity())
            .collection(COLLECTION_MEETINGS)
            .get()
        snapshot.documents.forEach { it.reference.delete() }
    }

    private fun getCity(): String {
        val city = auth.currentUserEmail.split("@")[0]
        return city.ifBlank { throw InvalidUserException() }
    }

    companion object {
        private const val COLLECTION_CITIES = "cities"
        private const val COLLECTION_MEETINGS = "meetings"
        private const val FIELD_DATE = "date"
    }
}