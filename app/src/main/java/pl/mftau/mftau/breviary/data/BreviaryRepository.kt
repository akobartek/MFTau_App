package pl.mftau.mftau.breviary.data

import kotlinx.coroutines.flow.Flow
import pl.mftau.mftau.breviary.model.Breviary

interface BreviaryRepository {
    fun checkIfThereAreMultipleOffices(daysFromToday: Int): Flow<Result<Map<String, String>?>>

    fun loadBreviary(office: String, daysFromToday: Int, type: Int): Flow<Result<Breviary>>
}