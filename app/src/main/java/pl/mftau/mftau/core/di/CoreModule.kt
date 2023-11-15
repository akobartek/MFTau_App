package pl.mftau.mftau.core.di

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import pl.mftau.mftau.auth.data.AuthRepositoryImpl
import pl.mftau.mftau.auth.domain.AuthRepository
import pl.mftau.mftau.core.data.PreferencesRepository
import pl.mftau.mftau.core.data.dataStore
import pl.mftau.mftau.core.presentation.screenmodels.EmailScreenModel
import pl.mftau.mftau.core.presentation.screenmodels.MainScreenModel
import pl.mftau.mftau.core.presentation.screenmodels.SettingsScreenModel

val coreModule = module {
    single { Firebase.auth }
    single { Firebase.firestore }

    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single { PreferencesRepository(androidContext().dataStore) }

    factory { MainScreenModel(get(), get()) }

    factory { SettingsScreenModel(get()) }

    factory { EmailScreenModel() }
}