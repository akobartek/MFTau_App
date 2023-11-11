package pl.mftau.mftau.breviary.domain.usecase

import pl.mftau.mftau.breviary.domain.repository.WebBreviaryRepository
import pl.mftau.mftau.breviary.domain.model.BreviaryType

class LoadSingleBreviaryUseCase(
    private val webRepository: WebBreviaryRepository
) {

    suspend operator fun invoke(office: String, daysFromToday: Int, type: BreviaryType) =
        webRepository.loadBreviary(office, daysFromToday, type)
}