package pl.mftau.mftau.breviary.domain.repository

import pl.mftau.mftau.breviary.domain.model.Breviary
import pl.mftau.mftau.breviary.domain.model.BreviaryType

interface WebBreviaryRepository {
    suspend fun checkIfThereAreMultipleOffices(date: String): Result<Map<String, String>?>

    suspend fun loadBreviary(
        office: String,
        date: String,
        type: BreviaryType,
        onlyHtml: Boolean = false
    ): Result<Breviary>
}