package pl.mftau.mftau.utils

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import pl.mftau.mftau.MFTauApplication

object PreferencesManager {

    private const val NIGHT_MODE = "night_mode"
    private const val REPEAT_GOSPEL = "repeat_gospel_key"
    private const val AWAKE_SONGBOOK = "awake_songbook_key"

    private val sharedPref: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(MFTauApplication.instance)

    fun getNightMode() = sharedPref.getBoolean(NIGHT_MODE, false)
    fun setNightMode(newValue: Boolean) {
        sharedPref.edit()
            .putBoolean(NIGHT_MODE, newValue)
            .apply()
    }

    fun getRepeatGospel() = sharedPref.getBoolean(REPEAT_GOSPEL, false)
    fun setRepeatGospel(newValue: Boolean) {
        sharedPref.edit()
            .putBoolean(REPEAT_GOSPEL, newValue)
            .apply()
    }

    fun getAwakeSongbook() = sharedPref.getBoolean(AWAKE_SONGBOOK, false)
    fun setAwakeSongbook(newValue: Boolean) {
        sharedPref.edit()
            .putBoolean(AWAKE_SONGBOOK, newValue)
            .apply()
    }
}