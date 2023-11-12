package pl.mftau.mftau.breviary.data.repository

import pl.mftau.mftau.breviary.data.db.BreviaryDao
import pl.mftau.mftau.breviary.domain.model.BreviaryEntity
import pl.mftau.mftau.breviary.domain.repository.DbBreviaryRepository

class DbBreviaryRepositoryImpl(private val dao: BreviaryDao) : DbBreviaryRepository {
    override suspend fun insertBreviary(entity: BreviaryEntity): Long =
        dao.insertBreviary(entity)

    override suspend fun loadBreviary(value: String, date: Long): String =
        dao.loadBreviary(value, date)

    override suspend fun clearBreviary(date: Long) =
        dao.clearBreviary(date)
}