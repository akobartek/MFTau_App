package pl.mftau.mftau.readings.di

import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.mftau.mftau.readings.data.PolishPrayersRepository
import pl.mftau.mftau.readings.data.PolishWritingsRepository
import pl.mftau.mftau.readings.domain.PrayersRepository
import pl.mftau.mftau.readings.domain.WritingsRepository
import pl.mftau.mftau.readings.presentation.ReadingsViewModel

val readingsModule = module {
    factory<PrayersRepository> { PolishPrayersRepository() }
    factory<WritingsRepository> { PolishWritingsRepository() }
    viewModel { ReadingsViewModel(get(), get()) }
}