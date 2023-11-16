package pl.mftau.mftau.readings.di

import org.koin.dsl.module
import pl.mftau.mftau.readings.data.PolishPrayersRepository
import pl.mftau.mftau.readings.data.PolishWritingsRepository
import pl.mftau.mftau.readings.domain.PrayersRepository
import pl.mftau.mftau.readings.domain.WritingsRepository
import pl.mftau.mftau.readings.presentation.ReadingsListScreenModel

val readingsModule = module {
    single<PrayersRepository> { PolishPrayersRepository() }
    single<WritingsRepository> { PolishWritingsRepository() }
    factory { ReadingsListScreenModel(get(), get(), get()) }
}