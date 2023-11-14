package pl.mftau.mftau.auth.di

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import org.koin.dsl.module
import pl.mftau.mftau.auth.data.AuthRepositoryImpl
import pl.mftau.mftau.auth.domain.AuthRepository
import pl.mftau.mftau.auth.presentation.AuthScreenModel

val authModule = module {
    single { Firebase.auth }
    single { Firebase.firestore }

    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }

    factory { AuthScreenModel(get()) }
}