package pl.mftau.mftau.core.di

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.mftau.mftau.MainViewModel
import pl.mftau.mftau.auth.data.AuthRepositoryImpl
import pl.mftau.mftau.auth.domain.AuthRepository
import pl.mftau.mftau.core.data.PreferencesRepository
import pl.mftau.mftau.core.presentation.screenmodels.EmailScreenModel
import pl.mftau.mftau.core.presentation.screenmodels.MainScreenModel
import pl.mftau.mftau.core.presentation.screenmodels.SettingsScreenModel
import pl.mftau.mftau.core.utils.dataStore

val coreModule = module {
    single { Firebase.auth }
    single { Firebase.firestore }

    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single { PreferencesRepository(androidContext().dataStore) }

    factory { MainScreenModel(get(), get()) }

    factory { SettingsScreenModel(get()) }

    factory { EmailScreenModel() }

    viewModel { MainViewModel(get()) }
}