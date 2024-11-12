package pl.mftau.mftau.leaders.domain.usecase

import pl.mftau.mftau.leaders.data.DbEmausRepository

class GetLastDrawUseCase(repository: DbEmausRepository) {
    val lastDraw by lazy {
        repository.getLastDraw()
    }
}