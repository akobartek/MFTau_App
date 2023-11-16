package pl.mftau.mftau.breviary.di

import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module
import pl.mftau.mftau.breviary.data.db.BreviaryDao
import pl.mftau.mftau.breviary.data.db.BreviaryDatabase
import pl.mftau.mftau.breviary.domain.repository.DbBreviaryRepository
import pl.mftau.mftau.breviary.data.repository.DbBreviaryRepositoryImpl
import pl.mftau.mftau.breviary.domain.repository.WebBreviaryRepository
import pl.mftau.mftau.breviary.data.repository.WebBreviaryRepositoryImpl
import pl.mftau.mftau.breviary.domain.usecase.LoadFromDbBreviaryUseCase
import pl.mftau.mftau.breviary.domain.usecase.LoadAndSaveBreviaryUseCase
import pl.mftau.mftau.breviary.domain.usecase.CheckIfThereAreMultipleOfficesUseCase
import pl.mftau.mftau.breviary.domain.usecase.LoadSingleBreviaryUseCase
import pl.mftau.mftau.breviary.domain.usecase.ClearBreviaryDbUseCase
import pl.mftau.mftau.breviary.presentation.BreviarySaveScreenModel
import pl.mftau.mftau.breviary.presentation.BreviarySelectScreenModel
import pl.mftau.mftau.breviary.presentation.BreviaryTextScreenModel

val breviaryModule = module {
    single(named("breviary_db")) {
        Room.databaseBuilder(
            androidApplication(),
            BreviaryDatabase::class.java,
            "breviary_database.db"
        ).build()
    }

    single<BreviaryDao> {
        val db = get<BreviaryDatabase>(named("breviary_db"))
        db.breviaryDao()
    }

    single<WebBreviaryRepository> {
        WebBreviaryRepositoryImpl(get())
    }

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