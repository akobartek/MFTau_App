package pl.mftau.mftau.breviary.domain.usecase

import pl.mftau.mftau.breviary.domain.repository.DbBreviaryRepository

class ClearBreviaryDbUseCase(private val dbRepository: DbBreviaryRepository) {
    suspend operator fun invoke(date: String) {
        val dateNumber = date.split(".").reversed().joinToString("").toLong()
        dbRepository.clearBreviary(dateNumber - 2)
    }
}