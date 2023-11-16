package pl.mftau.mftau.readings.di

import org.koin.dsl.module
import pl.mftau.mftau.readings.presentation.ReadingsListScreenModel

val readingsModule = module {
    factory { ReadingsListScreenModel() }
}