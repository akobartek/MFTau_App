package pl.mftau.mftau.common.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import pl.mftau.mftau.common.data.PreferencesRepository.Companion.DATA_STORE_NAME

fun dataStore(): DataStore<Preferences> = createDataStore(
    producePath = {
        "${fileDirectory()}/$DATA_STORE_NAME"
    }
)