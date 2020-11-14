package pl.mftau.mftau

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class MFTauApplication : Application() {

    init {
        instance = this@MFTauApplication
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: Context
    }
}