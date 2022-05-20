package pl.mftau.mftau.utils

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import pl.mftau.mftau.MFTauApplication

object PreferencesManager {

    private const val NIGHT_MODE = "night_mode"
    private const val REPEAT_GOSPEL = "repeat_gospel_key"
    private const val AWAKE_SONG_BOOK = "awake_song_book_key"
    private const val OPEN_SONG_BOOK_AS_PDF = "open_song_book_as_pdf_key"
    private const val SONG_BOOK_SHOW_CORDS = "song_book_show_cords_key"

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

    fun getAwakeSongBook() = sharedPref.getBoolean(AWAKE_SONG_BOOK, false)
    fun setAwakeSongBook(newValue: Boolean) {
        sharedPref.edit()
            .putBoolean(AWAKE_SONG_BOOK, newValue)
            .apply()
    }

    fun getOpenSongBookAsPdf() = sharedPref.getBoolean(OPEN_SONG_BOOK_AS_PDF, false)
    fun setOpenSongBookAsPdf(newValue: Boolean) {
        sharedPref.edit()
            .putBoolean(OPEN_SONG_BOOK_AS_PDF, newValue)
            .apply()
    }

    fun getSongBookShowCords() = sharedPref.getBoolean(SONG_BOOK_SHOW_CORDS, false)
    fun setSongBookShowCords(newValue: Boolean) {
        sharedPref.edit()
            .putBoolean(SONG_BOOK_SHOW_CORDS, newValue)
            .apply()
    }
}