package pl.mftau.mftau.breviary.di

import org.koin.dsl.module
import pl.mftau.mftau.breviary.domain.db.BreviaryDao
import pl.mftau.mftau.core.data.MFTauDatabase
import pl.mftau.mftau.breviary.data.DbBreviaryRepositoryImpl
import pl.mftau.mftau.breviary.data.WebBreviaryRepositoryImpl
import pl.mftau.mftau.breviary.domain.repository.DbBreviaryRepository
import pl.mftau.mftau.breviary.domain.repository.WebBreviaryRepository
import pl.mftau.mftau.breviary.domain.usecase.CheckIfThereAreMultipleOfficesUseCase
import pl.mftau.mftau.breviary.domain.usecase.ClearBreviaryDbUseCase
import pl.mftau.mftau.breviary.domain.usecase.LoadAndSaveBreviaryUseCase
import pl.mftau.mftau.breviary.domain.usecase.LoadFromDbBreviaryUseCase
import pl.mftau.mftau.breviary.domain.usecase.LoadSingleBreviaryUseCase
import pl.mftau.mftau.breviary.presentation.BreviarySaveScreenModel
import pl.mftau.mftau.breviary.presentation.BreviarySelectScreenModel
import pl.mftau.mftau.breviary.presentation.BreviaryTextScreenModel

val breviaryModule = module {
    single<BreviaryDao> {
        val db = get<MFTauDatabase>()
        db.breviaryDao()
    }

    single<WebBreviaryRepository> { WebBreviaryRepositoryImpl(get()) }

    single<DbBreviaryRepository> { DbBreviaryRepositoryImpl(get()) }

    single { CheckIfThereAreMultipleOfficesUseCase(get()) }

    single { LoadSingleBreviaryUseCase(get()) }

    single { LoadAndSaveBreviaryUseCase(get(), get()) }

    single { LoadFromDbBreviaryUseCase(get()) }

    single { ClearBreviaryDbUseCase(get()) }

    factory { BreviaryTextScreenModel(get(), get(), get()) }

    factory { BreviarySelectScreenModel(get()) }

    factory { BreviarySaveScreenModel(get(), get()) }
}