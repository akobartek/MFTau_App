package pl.mftau.mftau

import android.app.Application
import org.koin.android.ext.koin.androidContext
import pl.mftau.mftau.common.utils.initKoin

class MFTauApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MFTauApplication)
        }
    }
}