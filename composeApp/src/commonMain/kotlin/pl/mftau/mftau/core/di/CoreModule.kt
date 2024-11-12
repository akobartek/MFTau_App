package pl.mftau.mftau.core.di

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.mftau.mftau.android.MainViewModel
import pl.mftau.mftau.core.presentation.screenmodels.EmailScreenModel
import pl.mftau.mftau.core.presentation.screenmodels.MainScreenModel
import pl.mftau.mftau.core.presentation.screenmodels.SettingsScreenModel

val coreModule = module {
    single { Firebase.auth }
    single { Firebase.firestore }

    factory { MainScreenModel(get(), get()) }

    factory { SettingsScreenModel(get()) }

    factory { EmailScreenModel() }

    viewModel { MainViewModel(get()) }
}