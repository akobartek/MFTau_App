package pl.mftau.mftau.breviary.domain.usecase

import pl.mftau.mftau.breviary.domain.model.BreviaryType
import pl.mftau.mftau.breviary.domain.repository.DbBreviaryRepository
import pl.mftau.mftau.breviary.domain.repository.WebBreviaryRepository

class BreviaryLoadAndSaveUseCase(
    private val webRepository: WebBreviaryRepository,
    private val dbRepository: DbBreviaryRepository
) {

    suspend operator fun invoke(office: String, date: String) {
        webRepository.loadBreviary(office, date, BreviaryType.LAUDS)
        webRepository.loadBreviary(office, date, BreviaryType.VESPERS)
        webRepository.loadBreviary(office, date, BreviaryType.COMPLINE)
    }
}