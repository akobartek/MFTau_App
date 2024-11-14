package pl.mftau.mftau.leaders.domain.repository

import kotlinx.coroutines.flow.Flow
import pl.mftau.mftau.leaders.domain.model.Person

interface PeopleRepository {
    fun getPeople(): Flow<List<Person>>
    suspend fun savePerson(person: Person)
    suspend fun deletePerson(person: Person)
}