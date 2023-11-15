package pl.mftau.mftau.auth.di

import org.koin.dsl.module
import pl.mftau.mftau.auth.presentation.AuthScreenModel

val authModule = module {
    factory { AuthScreenModel(get(), get()) }
}