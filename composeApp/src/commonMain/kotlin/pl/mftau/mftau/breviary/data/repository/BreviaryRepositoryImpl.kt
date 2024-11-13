package pl.mftau.mftau.breviary.data.repository

import androidx.compose.ui.graphics.Color
import pl.mftau.mftau.breviary.data.database.BreviaryEntity
import pl.mftau.mftau.breviary.data.sources.DbBreviarySource
import pl.mftau.mftau.breviary.data.sources.WebBreviarySource
import pl.mftau.mftau.breviary.domain.model.Breviary
import pl.mftau.mftau.breviary.domain.model.BreviaryDay
import pl.mftau.mftau.breviary.domain.model.BreviaryType
import pl.mftau.mftau.breviary.domain.repository.BreviaryRepository

class BreviaryRepositoryImpl(
    private val webBreviarySource: WebBreviarySource,
    private val dbBreviarySource: DbBreviarySource,
) : BreviaryRepository {

    override suspend fun checkIfThereAreMultipleOffices(date: String): Result<Map<String, String>?> {
        var result = webBreviarySource.checkIfThereAreMultipleOffices(date)
        if (result.isFailure && dbBreviarySource.checkIfExists(BreviaryEntity.getDbDate(date)))
            result = Result.success(null)

        return result
    }

    override suspend fun loadBreviary(
        office: String,
        date: String,
        type: BreviaryType,
        onlyHtml: Boolean,
        accentColor: Color
    ): Result<Breviary> {
        return try {
            var result = webBreviarySource.loadBreviary(office, date, type, onlyHtml, accentColor)
            if (result.isFailure) {
                dbBreviarySource.loadBreviary(BreviaryEntity.getDbDate(date), type)?.let { dbHtml ->
                    result = webBreviarySource.loadBreviaryFromHtml(dbHtml, type)
                }
            }
            result
        } catch (exc: Exception) {
            Result.failure(exc)
        }
    }

    override suspend fun saveBreviary(breviaryDay: BreviaryDay): Long =
        dbBreviarySource.upsertBreviary(BreviaryEntity.fromDomainObject(breviaryDay))

    override suspend fun clearBreviaryDb(date: String) =
        dbBreviarySource.clearBreviary(BreviaryEntity.getDbDate(date) - 2) // clear saved objects older than 2 days
}