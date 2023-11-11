package pl.mftau.mftau.breviary.domain.usecase

import pl.mftau.mftau.breviary.domain.model.BreviaryType
import pl.mftau.mftau.breviary.domain.repository.DbBreviaryRepository
import pl.mftau.mftau.breviary.domain.repository.WebBreviaryRepository

class BreviarySaveUseCase(
    private val webRepository: WebBreviaryRepository,
    private val dbRepository: DbBreviaryRepository
) {

    suspend operator fun invoke(office: String, daysFromToday: Int) {
        webRepository.loadBreviary(office, daysFromToday, BreviaryType.LAUDS)
        webRepository.loadBreviary(office, daysFromToday, BreviaryType.VESPERS)
        webRepository.loadBreviary(office, daysFromToday, BreviaryType.COMPLINE)
    }
}