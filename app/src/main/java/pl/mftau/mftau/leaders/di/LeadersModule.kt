package pl.mftau.mftau.leaders.di

import org.koin.dsl.module
import pl.mftau.mftau.common.data.MFTauDatabase
import pl.mftau.mftau.leaders.data.DbEmausRepository
import pl.mftau.mftau.leaders.data.FirebasePeopleRepository
import pl.mftau.mftau.leaders.domain.db.EmausDao
import pl.mftau.mftau.leaders.domain.repository.PeopleRepository
import pl.mftau.mftau.leaders.domain.usecase.DeleteDrawsUseCase
import pl.mftau.mftau.leaders.domain.usecase.DrawNewEmausesUseCase
import pl.mftau.mftau.leaders.domain.usecase.GetLastDrawUseCase
import pl.mftau.mftau.leaders.presentation.people.screenmodels.EmausScreenModel
import pl.mftau.mftau.leaders.presentation.people.screenmodels.PeopleListScreenModel

val leadersModule = module {
    single<EmausDao> {
        val db = get<MFTauDatabase>()
        db.emausDao()
    }

    single<PeopleRepository> { FirebasePeopleRepository(get(), get()) }

    single { DbEmausRepository(get()) }

    single { GetLastDrawUseCase(get()) }

    single { DrawNewEmausesUseCase(get()) }

    single { DeleteDrawsUseCase(get()) }

    factory { PeopleListScreenModel(get()) }

    factory { EmausScreenModel(get(), get(), get(), get()) }
}