package pl.mftau.mftau.auth.di

import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.mftau.mftau.auth.data.FirebaseAuthRepository
import pl.mftau.mftau.auth.domain.AuthRepository
import pl.mftau.mftau.auth.presentation.AuthViewModel

val authModule = module {
    single<AuthRepository> { FirebaseAuthRepository(get(), get()) }

    viewModel { AuthViewModel(get(), get()) }
}