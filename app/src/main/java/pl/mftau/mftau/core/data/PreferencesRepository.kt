package pl.mftau.mftau.core.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

data class UserPreferences(
    val colorTheme: ColorTheme = ColorTheme.SYSTEM,
    val dynamicColors: Boolean = false,
    val repeatGospel: Boolean = false,
    val keepSongBookAwake: Boolean = false
)

const val DATA_STORE_NAME = "user_preferences"
private const val THEME_KEY = "theme"
private const val DYNAMIC_COLORS_KEY = "dynamic_colors"
private const val REPEAT_GOSPEL_KEY = "repeat_gospel"
private const val KEEP_SONG_BOOK_AWAKE_KEY = "keep_song_book_awake"
private const val LAST_USED_EMAIL_KEY = "last_used_email"
private const val ACCENT_COLOR_KEY = "accent_color"

val Context.dataStore by preferencesDataStore(name = DATA_STORE_NAME)

class PreferencesRepository(private val dataStore: DataStore<Preferences>) {

    private object PreferencesKeys {
        val THEME = stringPreferencesKey(THEME_KEY)
        val DYNAMIC_COLORS = booleanPreferencesKey(DYNAMIC_COLORS_KEY)
        val REPEAT_GOSPEL = booleanPreferencesKey(REPEAT_GOSPEL_KEY)
        val KEEP_SONG_BOOK_AWAKE = booleanPreferencesKey(KEEP_SONG_BOOK_AWAKE_KEY)
        val LAST_USED_EMAIL = stringPreferencesKey(LAST_USED_EMAIL_KEY)
        val ACCENT_COLOR = intPreferencesKey(ACCENT_COLOR_KEY)
    }

    val preferencesFlow: Flow<UserPreferences> = dataStore.data
        .catch { exc ->
            Log.e(TAG, "Error occurred while reading preferences:", exc)
            emit(emptyPreferences())
        }.map { preferences ->
            mapUserPreferences(preferences)
        }

    suspend fun getTheme() =
        ColorTheme.fromValue(dataStore.data.firstOrNull()?.get(PreferencesKeys.THEME))

    suspend fun getRepeatGospel() =
        dataStore.data.firstOrNull()?.get(PreferencesKeys.REPEAT_GOSPEL) ?: false

    suspend fun getKeepSongBookAwake() =
        dataStore.data.firstOrNull()?.get(PreferencesKeys.KEEP_SONG_BOOK_AWAKE) ?: false

    suspend fun getLastUsedEmail() =
        dataStore.data.firstOrNull()?.get(PreferencesKeys.LAST_USED_EMAIL) ?: ""

    suspend fun getAccentColor() =
        dataStore.data.firstOrNull()?.get(PreferencesKeys.ACCENT_COLOR) ?: 0

    suspend fun updateTheme(colorTheme: ColorTheme) {
        colorTheme.setupAppCompatDelegate()
        updatePreference(colorTheme.value, PreferencesKeys.THEME)
    }

    suspend fun updateDynamicColors(dynamicColors: Boolean) {
        updatePreference(dynamicColors, PreferencesKeys.DYNAMIC_COLORS)
    }

    suspend fun updateRepeatGospel(repeatGospel: Boolean) {
        updatePreference(repeatGospel, PreferencesKeys.REPEAT_GOSPEL)
    }

    suspend fun updateKeepSongBookAwake(keepSongBookAwake: Boolean) {
        updatePreference(keepSongBookAwake, PreferencesKeys.KEEP_SONG_BOOK_AWAKE)
    }

    suspend fun updateLastUsedEmail(email: String) {
        updatePreference(email, PreferencesKeys.LAST_USED_EMAIL)
    }

    suspend fun updateAccentColor(color: Int) {
        updatePreference(color, PreferencesKeys.ACCENT_COLOR)
    }

    private suspend fun <T> updatePreference(value: T, key: Preferences.Key<T>) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    private fun mapUserPreferences(preferences: Preferences): UserPreferences {
        val colorTheme = ColorTheme.fromValue(preferences[PreferencesKeys.THEME])
        val dynamicColors = preferences[PreferencesKeys.DYNAMIC_COLORS] ?: false
        val repeatGospel = preferences[PreferencesKeys.REPEAT_GOSPEL] ?: false
        val keepSongBookAwake = preferences[PreferencesKeys.KEEP_SONG_BOOK_AWAKE] ?: false
        return UserPreferences(colorTheme, dynamicColors, repeatGospel, keepSongBookAwake)
    }

    companion object {
        private const val TAG = "PreferencesRepository"
    }
}