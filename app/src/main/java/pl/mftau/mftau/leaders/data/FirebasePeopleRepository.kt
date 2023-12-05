package pl.mftau.mftau.leaders.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.tasks.await
import pl.mftau.mftau.auth.domain.AuthRepository
import pl.mftau.mftau.leaders.domain.model.InvalidUserException
import pl.mftau.mftau.leaders.domain.model.Person
import pl.mftau.mftau.leaders.domain.repository.PeopleRepository

@OptIn(ExperimentalCoroutinesApi::class)
class FirebasePeopleRepository(
    private val firestore: FirebaseFirestore,
    private val auth: AuthRepository
) : PeopleRepository {
    override val people: Flow<List<Person>>
        get() = auth.currentUser.flatMapLatest { user ->
            val city = user?.email?.split("@")?.get(0) ?: throw InvalidUserException()
            firestore.collection(COLLECTION_CITIES)
                .document(city)
                .collection(COLLECTION_PEOPLE)
                .orderBy(FIELD_NAME)
                .dataObjects()
        }

    override suspend fun savePerson(person: Person): Boolean {
        val collection = firestore.collection(COLLECTION_CITIES)
            .document(getCity())
            .collection(COLLECTION_PEOPLE)
        val personSave = person.copy(
            id = person.id.ifBlank { collection.document().id }
        )
        val task = collection
            .document(personSave.id)
            .set(personSave)
        task.await()
        return task.isSuccessful
    }

    override suspend fun deletePerson(person: Person): Boolean {
        val task = firestore.collection(COLLECTION_CITIES)
            .document(getCity())
            .collection(COLLECTION_PEOPLE)
            .document(person.id)
            .delete()
        task.await()
        return task.isSuccessful
    }

    private fun getCity(): String {
        val city = auth.currentUserEmail.split("@")[0]
        return city.ifBlank { throw InvalidUserException() }
    }

    companion object {
        private const val COLLECTION_CITIES = "cities"
        private const val COLLECTION_PEOPLE = "people"
        private const val FIELD_NAME = "name"
    }
}