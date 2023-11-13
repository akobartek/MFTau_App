package pl.mftau.mftau.breviary.domain.repository

import pl.mftau.mftau.breviary.domain.model.Breviary.BreviaryHtml
import pl.mftau.mftau.breviary.domain.model.BreviaryEntity
import pl.mftau.mftau.breviary.domain.model.BreviaryType

interface DbBreviaryRepository {
    suspend fun insertBreviary(entity: BreviaryEntity): Long

    suspend fun loadBreviary(type: BreviaryType, date: Long): BreviaryHtml?

    suspend fun clearBreviary(date: Long)
}