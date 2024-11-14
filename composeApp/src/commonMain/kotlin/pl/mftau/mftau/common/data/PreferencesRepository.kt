package pl.mftau.mftau.common.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import pl.mftau.mftau.songbook.domain.model.SongBookPreferences
import pl.mftau.mftau.songbook.domain.model.SongBookPreferences.Companion.DEFAULT_FONT_SIZE
import pl.mftau.mftau.ui.theme.ColorTheme
import pl.mftau.mftau.ui.theme.setupAppCompatDelegate

class PreferencesRepository(private val dataStore: DataStore<Preferences>) {
    private object PreferencesKeys {
        val THEME = stringPreferencesKey(THEME_KEY)
        val DYNAMIC_COLORS = booleanPreferencesKey(DYNAMIC_COLORS_KEY)
        val REPEAT_GOSPEL = booleanPreferencesKey(REPEAT_GOSPEL_KEY)
        val KEEP_SCREEN_AWAKE = booleanPreferencesKey(KEEP_SCREEN_AWAKE_KEY)
        val SONG_BOOK_ARE_CHORDS_VISIBLE = booleanPreferencesKey(SONG_BOOK_ARE_CHORDS_VISIBLE_KEY)
        val SONG_BOOK_FONT_SIZE = intPreferencesKey(SONG_BOOK_FONT_SIZE_KEY)
        val LAST_USED_EMAIL = stringPreferencesKey(LAST_USED_EMAIL_KEY)
        val PRESENCE_SHOW_JUSTIFIED = booleanPreferencesKey(PRESENCE_SHOW_JUSTIFIED_KEY)
    }

    val userPreferencesFlow: Flow<UserPreferences> = dataStore.data
        .catch {
            emit(emptyPreferences())
        }.map { preferences ->
            mapUserPreferences(preferences)
        }

    val songBookPreferencesFlow: Flow<SongBookPreferences> = dataStore.data
        .catch {
            emit(emptyPreferences())
        }.map { preferences ->
            mapSongBookPreferences(preferences)
        }

    suspend fun getRepeatGospel() =
        dataStore.data.firstOrNull()?.get(PreferencesKeys.REPEAT_GOSPEL) ?: false

    suspend fun getLastUsedEmail() =
        dataStore.data.firstOrNull()?.get(PreferencesKeys.LAST_USED_EMAIL) ?: ""

    fun getShowJustified() =
        dataStore.data.map { it[PreferencesKeys.PRESENCE_SHOW_JUSTIFIED] ?: false }

    suspend fun updateTheme(colorTheme: ColorTheme) {
        setupAppCompatDelegate(colorTheme)
        updatePreference(colorTheme.value, PreferencesKeys.THEME)
    }

    suspend fun updateDynamicColors(dynamicColors: Boolean) {
        updatePreference(dynamicColors, PreferencesKeys.DYNAMIC_COLORS)
    }

    suspend fun updateRepeatGospel(repeatGospel: Boolean) {
        updatePreference(repeatGospel, PreferencesKeys.REPEAT_GOSPEL)
    }

    suspend fun updateKeepScreenAwake(keepScreenAwake: Boolean) {
        updatePreference(keepScreenAwake, PreferencesKeys.KEEP_SCREEN_AWAKE)
    }

    suspend fun updateLastUsedEmail(email: String) {
        updatePreference(email, PreferencesKeys.LAST_USED_EMAIL)
    }

    suspend fun updateChordsVisibility(visibility: Boolean) {
        updatePreference(visibility, PreferencesKeys.SONG_BOOK_ARE_CHORDS_VISIBLE)
    }

    suspend fun updateSongBookFontSize(fontSize: Int) {
        updatePreference(fontSize, PreferencesKeys.SONG_BOOK_FONT_SIZE)
    }

    suspend fun updatePresenceShowJustified(showJustified: Boolean) {
        updatePreference(showJustified, PreferencesKeys.PRESENCE_SHOW_JUSTIFIED)
    }

    private suspend fun <T> updatePreference(value: T, key: Preferences.Key<T>) {
        dataStore.edit { preferences -> preferences[key] = value }
    }

    private fun mapUserPreferences(preferences: Preferences): UserPreferences {
        val colorTheme = ColorTheme.fromValue(preferences[PreferencesKeys.THEME])
        val dynamicColors = preferences[PreferencesKeys.DYNAMIC_COLORS] ?: false
        val repeatGospel = preferences[PreferencesKeys.REPEAT_GOSPEL] ?: false
        val keepScreenAwake = preferences[PreferencesKeys.KEEP_SCREEN_AWAKE] ?: false

        return UserPreferences(
            colorTheme = colorTheme,
            dynamicColors = dynamicColors,
            repeatGospel = repeatGospel,
            keepScreenAwake = keepScreenAwake,
        )
    }

    private fun mapSongBookPreferences(preferences: Preferences): SongBookPreferences {
        val areChordsVisible = preferences[PreferencesKeys.SONG_BOOK_ARE_CHORDS_VISIBLE] ?: false
        val fontSize = preferences[PreferencesKeys.SONG_BOOK_FONT_SIZE] ?: DEFAULT_FONT_SIZE
        return SongBookPreferences(areChordsVisible, fontSize)
    }

    companion object {
        const val DATA_STORE_NAME = "user_preferences.preferences_db"
        private const val THEME_KEY = "theme"
        private const val DYNAMIC_COLORS_KEY = "dynamic_colors"
        private const val REPEAT_GOSPEL_KEY = "repeat_gospel"
        private const val KEEP_SCREEN_AWAKE_KEY = "keep_screen_awake"
        private const val SONG_BOOK_ARE_CHORDS_VISIBLE_KEY = "song_book_are_chords_visible"
        private const val SONG_BOOK_FONT_SIZE_KEY = "song_book_font_size"
        private const val LAST_USED_EMAIL_KEY = "last_used_email"
        private const val PRESENCE_SHOW_JUSTIFIED_KEY = "presence_show_justified"
    }
}