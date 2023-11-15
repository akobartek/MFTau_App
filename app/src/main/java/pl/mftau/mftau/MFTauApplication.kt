package pl.mftau.mftau

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import pl.mftau.mftau.auth.di.authModule
import pl.mftau.mftau.core.di.coreModule
import pl.mftau.mftau.breviary.di.breviaryModule
import pl.mftau.mftau.gospel.di.gospelModule

class MFTauApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@MFTauApplication)
            modules(coreModule, authModule, breviaryModule, gospelModule)
        }
    }
}