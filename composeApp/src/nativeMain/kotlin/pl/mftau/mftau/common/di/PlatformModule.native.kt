package pl.mftau.mftau.common.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.dsl.module
import pl.mftau.mftau.common.data.MFTauDatabase
import pl.mftau.mftau.common.presentation.ApplePdfOpener
import pl.mftau.mftau.common.presentation.PdfOpener
import pl.mftau.mftau.common.utils.AppleSpeech
import pl.mftau.mftau.common.utils.MFTauSpeech
import pl.mftau.mftau.common.utils.dataStore
import pl.mftau.mftau.common.utils.fileDirectory

actual val platformModule: Module = module {
    single { dataStore() }

    single<MFTauDatabase> {
        val dbFilePath = "${fileDirectory()}/${MFTauDatabase.DATABASE_NAME}"
        Room.databaseBuilder<MFTauDatabase>(name = dbFilePath)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

    factory<PdfOpener> { ApplePdfOpener() }

    factory<MFTauSpeech> { AppleSpeech() }
}