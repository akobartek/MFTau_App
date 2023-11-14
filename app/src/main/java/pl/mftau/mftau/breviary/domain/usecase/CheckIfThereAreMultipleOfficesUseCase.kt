package pl.mftau.mftau.breviary.domain.usecase

import pl.mftau.mftau.breviary.domain.repository.WebBreviaryRepository

class CheckIfThereAreMultipleOfficesUseCase(private val webRepository: WebBreviaryRepository) {
    suspend operator fun invoke(date: String) = webRepository.checkIfThereAreMultipleOffices(date)
}