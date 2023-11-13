package pl.mftau.mftau.breviary.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import pl.mftau.mftau.breviary.domain.model.BreviaryType
import pl.mftau.mftau.breviary.domain.repository.DbBreviaryRepository

class BreviaryDbLoadUseCase(
    private val dbRepository: DbBreviaryRepository
) {
    suspend operator fun invoke(date: String, type: BreviaryType) = flow {
        val dateNumber = date.split(".").reversed().joinToString("").toLong()
        val dbBreviary = dbRepository.loadBreviary(type, dateNumber)
        emit(dbBreviary?.let { Result.success(dbBreviary) } ?: Result.failure(Exception()))
    }.flowOn(Dispatchers.IO)
}