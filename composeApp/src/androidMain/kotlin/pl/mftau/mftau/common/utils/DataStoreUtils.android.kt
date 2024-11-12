package pl.mftau.mftau.common.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import pl.mftau.mftau.common.data.PreferencesRepository.Companion.DATA_STORE_NAME

fun dataStore(context: Context): DataStore<Preferences> =
    createDataStore(
        producePath = { context.filesDir.resolve(DATA_STORE_NAME).absolutePath },
    )