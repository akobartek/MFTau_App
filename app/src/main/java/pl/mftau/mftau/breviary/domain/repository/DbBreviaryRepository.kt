package pl.mftau.mftau.breviary.domain.repository

import pl.mftau.mftau.breviary.domain.model.BreviaryEntity

interface DbBreviaryRepository {
    suspend fun insertBreviary(entity: BreviaryEntity): Long

    suspend fun loadBreviary(value: String, date: Long): String

    suspend fun clearBreviary(date: Long)
}