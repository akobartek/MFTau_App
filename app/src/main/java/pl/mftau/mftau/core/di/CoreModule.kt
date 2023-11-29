package pl.mftau.mftau.core.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.mftau.mftau.android.MainViewModel
import pl.mftau.mftau.core.presentation.screenmodels.EmailScreenModel
import pl.mftau.mftau.core.presentation.screenmodels.MainScreenModel
import pl.mftau.mftau.core.presentation.screenmodels.SettingsScreenModel

val coreModule = module {
    factory { MainScreenModel(get(), get()) }

    factory { SettingsScreenModel(get()) }

    factory { EmailScreenModel() }

    viewModel { MainViewModel(get()) }
}