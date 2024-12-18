package pl.mftau.mftau.common.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import pl.mftau.mftau.common.data.MFTauDatabase
import pl.mftau.mftau.common.presentation.AndroidPdfOpener
import pl.mftau.mftau.common.presentation.PdfOpener
import pl.mftau.mftau.common.utils.AndroidSpeech
import pl.mftau.mftau.common.utils.MFTauSpeech
import pl.mftau.mftau.common.utils.dataStore

actual val platformModule: Module = module {
    single { dataStore(get()) }

    single<MFTauDatabase> {
        val context = androidContext().applicationContext
        val dbFile = context.getDatabasePath(MFTauDatabase.DATABASE_NAME)
        Room.databaseBuilder<MFTauDatabase>(
            context = context,
            name = dbFile.absolutePath,
        )
            .fallbackToDestructiveMigration(true)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

    factory<PdfOpener> { AndroidPdfOpener(androidContext()) }

    factory<MFTauSpeech> { AndroidSpeech(androidContext()) }
}