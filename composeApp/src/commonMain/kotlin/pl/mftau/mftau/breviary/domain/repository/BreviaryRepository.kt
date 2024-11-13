package pl.mftau.mftau.breviary.domain.repository

import androidx.compose.ui.graphics.Color
import pl.mftau.mftau.breviary.domain.model.Breviary
import pl.mftau.mftau.breviary.domain.model.BreviaryDay
import pl.mftau.mftau.breviary.domain.model.BreviaryType

interface BreviaryRepository {
    suspend fun checkIfThereAreMultipleOffices(date: String): Result<Map<String, String>?>

    suspend fun loadBreviary(
        office: String,
        date: String,
        type: BreviaryType,
        onlyHtml: Boolean = false,
        accentColor: Color,
    ): Result<Breviary>

    suspend fun saveBreviary(breviaryDay: BreviaryDay): Long

    suspend fun clearBreviaryDb(date: String)
}