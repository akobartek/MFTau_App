package pl.mftau.mftau.leaders.domain.usecase

import pl.mftau.mftau.leaders.data.DbEmausRepository

class DeleteDrawsUseCase(private val repository: DbEmausRepository) {
    suspend operator fun invoke(clearAll: Boolean): Boolean =
        (if (clearAll) repository.deleteAllDraws() else repository.deleteLastDraw()) > 0
}