package pl.mftau.mftau.leaders.data

import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.orderBy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import pl.mftau.mftau.auth.domain.AuthRepository
import pl.mftau.mftau.leaders.domain.model.InvalidUserException
import pl.mftau.mftau.leaders.domain.model.Person
import pl.mftau.mftau.leaders.domain.repository.PeopleRepository

@OptIn(ExperimentalCoroutinesApi::class)
class FirebasePeopleRepository(
    private val firestore: FirebaseFirestore,
    private val auth: AuthRepository
) : PeopleRepository {

    override fun getPeople(): Flow<List<Person>> =
        auth.currentUser.flatMapLatest { user ->
            val city = user?.email?.split("@")?.get(0) ?: throw InvalidUserException()
            firestore.collection(COLLECTION_CITIES)
                .document(city)
                .collection(COLLECTION_PEOPLE)
                .orderBy(FIELD_NAME)
                .snapshots
                .map { querySnapshot ->
                    querySnapshot.documents.map { it.data() }
                }
        }

    override suspend fun savePerson(person: Person) =
        firestore.collection(COLLECTION_CITIES)
            .document(getCity())
            .collection(COLLECTION_PEOPLE)
            .document(person.id)
            .set(person)

    override suspend fun deletePerson(person: Person) =
        firestore.collection(COLLECTION_CITIES)
            .document(getCity())
            .collection(COLLECTION_PEOPLE)
            .document(person.id)
            .delete()

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