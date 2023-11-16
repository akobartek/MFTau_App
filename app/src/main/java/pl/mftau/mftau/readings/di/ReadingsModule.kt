package pl.mftau.mftau.readings.di

import org.koin.dsl.module
import pl.mftau.mftau.readings.data.PrayersRepository
import pl.mftau.mftau.readings.data.WritingsRepository
import pl.mftau.mftau.readings.presentation.ReadingsListScreenModel

val readingsModule = module {
    single { PrayersRepository() }
    single { WritingsRepository() }
    factory { ReadingsListScreenModel(get(), get(), get()) }
}