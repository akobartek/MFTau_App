package pl.mftau.mftau.common.di

import androidx.room.Room
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import pl.mftau.mftau.common.data.MFTauDatabase
import pl.mftau.mftau.common.data.PreferencesRepository
import pl.mftau.mftau.common.utils.dataStore


val commonModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            MFTauDatabase::class.java,
            "mftau_database.db"
        ).build()
    }

    single { Firebase.auth }
    single { Firebase.firestore }
    single { PreferencesRepository(androidContext().dataStore) }
}