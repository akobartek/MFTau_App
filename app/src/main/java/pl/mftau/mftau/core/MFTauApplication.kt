package pl.mftau.mftau.core

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import pl.mftau.mftau.auth.di.authModule
import pl.mftau.mftau.breviary.di.breviaryModule
import pl.mftau.mftau.common.di.commonModule
import pl.mftau.mftau.core.di.coreModule
import pl.mftau.mftau.gospel.di.gospelModule
import pl.mftau.mftau.readings.di.readingsModule
import pl.mftau.mftau.songbook.di.songBookModule

class MFTauApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@MFTauApplication)
            modules(
                commonModule,
                coreModule,
                authModule,
                songBookModule,
                breviaryModule,
                gospelModule,
                readingsModule
            )
        }
    }
}