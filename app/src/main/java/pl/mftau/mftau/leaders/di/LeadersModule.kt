package pl.mftau.mftau.leaders.di

import org.koin.dsl.module
import pl.mftau.mftau.leaders.data.FirebasePeopleRepository
import pl.mftau.mftau.leaders.domain.repository.PeopleRepository
import pl.mftau.mftau.leaders.presentation.screenmodels.PeopleListScreenModel

val leadersModule = module {
    single<PeopleRepository> { FirebasePeopleRepository(get(), get()) }

    factory { PeopleListScreenModel(get()) }
}