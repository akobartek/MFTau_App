package pl.mftau.mftau.core.di

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.mftau.mftau.core.presentation.AppViewModel
import pl.mftau.mftau.core.presentation.home.HomeViewModel
import pl.mftau.mftau.core.presentation.settings.SettingsViewModel

val coreModule = module {
    single { Firebase.auth }
    single { Firebase.firestore }

    viewModel { AppViewModel(get()) }
    single { HomeViewModel(get(), get()) }
    viewModel { SettingsViewModel(get()) }
}