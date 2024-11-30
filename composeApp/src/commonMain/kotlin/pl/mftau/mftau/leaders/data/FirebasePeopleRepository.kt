package pl.mftau.mftau.leaders.data

import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.orderBy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import pl.mftau.mftau.leaders.domain.model.Person
import pl.mftau.mftau.leaders.domain.repository.PeopleRepository

class FirebasePeopleRepository(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : PeopleRepository {

    override fun getPeople(): Flow<List<Person>> =
        getCity()?.let { city ->
            firestore.collection(COLLECTION_CITIES)
                .document(city)
                .collection(COLLECTION_PEOPLE)
                .orderBy(FIELD_NAME)
                .snapshots
                .map { querySnapshot ->
                    querySnapshot.documents.map { it.data() }
                }
        } ?: flowOf()

    override suspend fun savePerson(person: Person) {
        getCity()?.let { city ->
            firestore.collection(COLLECTION_CITIES)
                .document(city)
                .collection(COLLECTION_PEOPLE)
                .document(person.id)
                .set(person)
        }
    }

    override suspend fun deletePerson(person: Person) {
        getCity()?.let { city ->
            firestore.collection(COLLECTION_CITIES)
                .document(city)
                .collection(COLLECTION_PEOPLE)
                .document(person.id)
                .delete()
        }
    }

    private fun getCity(): String? =
        auth.currentUser?.email?.split("@")?.get(0)

    companion object {
        private const val COLLECTION_CITIES = "cities"
        private const val COLLECTION_PEOPLE = "people"
        private const val FIELD_NAME = "name"
    }
}