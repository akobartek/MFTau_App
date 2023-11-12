package pl.mftau.mftau.breviary.domain.repository

import kotlinx.coroutines.flow.Flow
import pl.mftau.mftau.breviary.domain.model.Breviary
import pl.mftau.mftau.breviary.domain.model.BreviaryType

interface WebBreviaryRepository {
    suspend fun checkIfThereAreMultipleOffices(date: String): Flow<Result<Map<String, String>?>>

    suspend fun loadBreviary(office: String, date: String, type: BreviaryType): Flow<Result<Breviary>>
}