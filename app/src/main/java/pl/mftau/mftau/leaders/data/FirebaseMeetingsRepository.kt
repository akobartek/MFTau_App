package pl.mftau.mftau.leaders.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.dataObjects
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.tasks.await
import pl.mftau.mftau.auth.domain.AuthRepository
import pl.mftau.mftau.leaders.domain.model.InvalidUserException
import pl.mftau.mftau.leaders.domain.model.Meeting
import pl.mftau.mftau.leaders.domain.repository.MeetingsRepository

@OptIn(ExperimentalCoroutinesApi::class)
class FirebaseMeetingsRepository(
    private val firestore: FirebaseFirestore,
    private val auth: AuthRepository
): MeetingsRepository {
    override val meetings: Flow<List<Meeting>>
        get() = auth.currentUser.flatMapLatest { user ->
            val city = user?.email?.split("@")?.get(0) ?: throw InvalidUserException()
            firestore.collection(COLLECTION_CITIES)
                .document(city)
                .collection(COLLECTION_MEETINGS)
                .orderBy(FIELD_DATE, Query.Direction.DESCENDING)
                .dataObjects()
        }

    override suspend fun saveMeeting(meeting: Meeting): Boolean {
        val collection = firestore.collection(COLLECTION_CITIES)
            .document(getCity())
            .collection(COLLECTION_MEETINGS)
        val meetingSave = meeting.copy(
            id = meeting.id.ifBlank { collection.document().id }
        )
        val task = collection
            .document(meetingSave.id)
            .set(meetingSave)
        task.await()
        return task.isSuccessful
    }

    override suspend fun deleteMeeting(meeting: Meeting): Boolean {
        val task = firestore.collection(COLLECTION_CITIES)
            .document(getCity())
            .collection(COLLECTION_MEETINGS)
            .document(meeting.id)
            .delete()
        task.await()
        return task.isSuccessful
    }

    override suspend fun clearMeetings() {
        val snapshot = firestore.collection(COLLECTION_CITIES)
            .document(getCity())
            .collection(COLLECTION_MEETINGS)
            .get()
            .await()
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