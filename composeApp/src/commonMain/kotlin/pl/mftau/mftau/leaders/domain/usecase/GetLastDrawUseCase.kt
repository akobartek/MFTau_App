package pl.mftau.mftau.leaders.domain.usecase

import pl.mftau.mftau.leaders.data.DbEmausRepository

class GetLastDrawUseCase(val repository: DbEmausRepository) {
    operator fun invoke() = repository.getLastDraw()
}