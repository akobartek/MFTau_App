package pl.mftau.mftau.breviary.domain.usecases

import pl.mftau.mftau.breviary.domain.repository.BreviaryRepository

class ClearBreviaryDbUseCase(private val breviaryRepository: BreviaryRepository) {
    suspend operator fun invoke(date: String) = breviaryRepository.clearBreviaryDb(date)
}