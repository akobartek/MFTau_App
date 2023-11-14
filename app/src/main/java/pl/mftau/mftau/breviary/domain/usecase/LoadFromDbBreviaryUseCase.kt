package pl.mftau.mftau.breviary.domain.usecase

import pl.mftau.mftau.breviary.domain.model.Breviary.BreviaryHtml
import pl.mftau.mftau.breviary.domain.model.BreviaryType
import pl.mftau.mftau.breviary.domain.repository.DbBreviaryRepository

class LoadFromDbBreviaryUseCase(private val dbRepository: DbBreviaryRepository) {
    suspend operator fun invoke(date: String, type: BreviaryType): Result<BreviaryHtml> {
        val dateNumber = date.split(".").reversed().joinToString("").toLong()
        val dbBreviary = dbRepository.loadBreviary(type, dateNumber)
        return dbBreviary?.let { Result.success(dbBreviary) } ?: Result.failure(Exception())
    }
}