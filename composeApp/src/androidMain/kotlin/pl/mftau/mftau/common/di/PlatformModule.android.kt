package pl.mftau.mftau.common.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import pl.mftau.mftau.common.data.MFTauDatabase
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
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
}