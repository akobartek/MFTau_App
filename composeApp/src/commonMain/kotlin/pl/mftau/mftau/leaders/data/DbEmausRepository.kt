package pl.mftau.mftau.leaders.data

import kotlinx.coroutines.flow.Flow
import pl.mftau.mftau.leaders.domain.db.EmausDao
import pl.mftau.mftau.leaders.domain.model.Emaus

class DbEmausRepository(private val dao: EmausDao) {

    suspend fun getAllDraws() = dao.getAllDraws()
    fun getLastDraw(): Flow<List<Emaus>> = dao.getLastDraw()

    suspend fun insertDraws(emauses: List<Emaus>) = dao.insertDraws(emauses)

    suspend fun deleteLastDraw(): Int = dao.deleteLastDraw()

    suspend fun deleteAllDraws(): Int = dao.deleteAllDraws()
}