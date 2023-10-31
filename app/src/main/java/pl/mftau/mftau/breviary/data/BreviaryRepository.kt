package pl.mftau.mftau.breviary.data

import kotlinx.coroutines.flow.Flow
import pl.mftau.mftau.breviary.model.Breviary
import pl.mftau.mftau.breviary.model.BreviaryType

interface BreviaryRepository {
    fun checkIfThereAreMultipleOffices(daysFromToday: Int): Flow<Result<Map<String, String>?>>

    fun loadBreviary(office: String, daysFromToday: Int, type: BreviaryType): Flow<Result<Breviary>>
}