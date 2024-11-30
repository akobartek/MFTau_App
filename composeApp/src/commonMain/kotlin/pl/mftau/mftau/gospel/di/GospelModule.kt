package pl.mftau.mftau.gospel.di

import org.koin.dsl.module
import pl.mftau.mftau.gospel.data.GospelRepositoryImpl
import pl.mftau.mftau.gospel.domain.GospelRepository
import pl.mftau.mftau.gospel.presentation.GospelViewModel

val gospelModule = module {
    single<GospelRepository> { GospelRepositoryImpl() }

    factory { GospelViewModel(get(), get()) }
}