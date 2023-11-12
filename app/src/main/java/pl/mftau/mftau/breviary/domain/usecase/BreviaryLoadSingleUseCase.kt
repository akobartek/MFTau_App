package pl.mftau.mftau.breviary.domain.usecase

import pl.mftau.mftau.breviary.domain.repository.WebBreviaryRepository
import pl.mftau.mftau.breviary.domain.model.BreviaryType

class BreviaryLoadSingleUseCase(
    private val webRepository: WebBreviaryRepository
) {

    suspend operator fun invoke(office: String, date: String, type: BreviaryType) =
        webRepository.loadBreviary(office, date, type)
}