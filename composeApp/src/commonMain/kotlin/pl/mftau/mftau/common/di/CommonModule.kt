package pl.mftau.mftau.common.di

import org.koin.dsl.module
import pl.mftau.mftau.common.data.PreferencesRepository

val commonModule = module {
    single { PreferencesRepository(get()) }
}