package pl.mftau.mftau.core.di

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.mftau.mftau.core.presentation.home.HomeViewModel
import pl.mftau.mftau.core.presentation.settings.SettingsScreenModel

val coreModule = module {
    single { Firebase.auth }
    single { Firebase.firestore }

    single { HomeViewModel(get(), get()) }
    viewModel { SettingsScreenModel(get()) }
}