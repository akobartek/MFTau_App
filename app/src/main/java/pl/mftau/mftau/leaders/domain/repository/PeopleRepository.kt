package pl.mftau.mftau.leaders.domain.repository

import kotlinx.coroutines.flow.Flow
import pl.mftau.mftau.leaders.domain.model.Person

interface PeopleRepository {
    val people: Flow<List<Person>>
    suspend fun savePerson(person: Person): Boolean
    suspend fun deletePerson(person: Person): Boolean
}