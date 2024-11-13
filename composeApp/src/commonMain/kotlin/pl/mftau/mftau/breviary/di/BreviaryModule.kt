package pl.mftau.mftau.breviary.di

import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.mftau.mftau.breviary.data.database.BreviaryDao
import pl.mftau.mftau.breviary.data.repository.BreviaryRepositoryImpl
import pl.mftau.mftau.breviary.data.sources.DbBreviarySource
import pl.mftau.mftau.breviary.data.sources.WebBreviarySource
import pl.mftau.mftau.breviary.domain.repository.BreviaryRepository
import pl.mftau.mftau.breviary.domain.usecases.CheckOfficesUseCase
import pl.mftau.mftau.breviary.domain.usecases.ClearBreviaryDbUseCase
import pl.mftau.mftau.breviary.domain.usecases.LoadBreviaryUseCase
import pl.mftau.mftau.breviary.domain.usecases.SaveBreviaryUseCase
import pl.mftau.mftau.breviary.presentation.BreviarySaveViewModel
import pl.mftau.mftau.breviary.presentation.BreviarySelectViewModel
import pl.mftau.mftau.breviary.presentation.BreviaryTextViewModel
import pl.mftau.mftau.common.data.MFTauDatabase

val breviaryModule = module {
    single<BreviaryDao> {
        get<MFTauDatabase>().breviaryDao()
    }

    single { WebBreviarySource() }
    single { DbBreviarySource(get()) }
    single<BreviaryRepository> { BreviaryRepositoryImpl(get(), get()) }
    factory { CheckOfficesUseCase(get()) }
    factory { LoadBreviaryUseCase(get()) }
    factory { SaveBreviaryUseCase(get()) }
    factory { ClearBreviaryDbUseCase(get()) }

    viewModel { BreviarySelectViewModel(get()) }
    viewModel { BreviaryTextViewModel(get(), get()) }
    viewModel { BreviarySaveViewModel(get(), get()) }
}