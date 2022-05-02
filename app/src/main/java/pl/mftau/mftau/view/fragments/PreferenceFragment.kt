package pl.mftau.mftau.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import pl.mftau.mftau.R
import pl.mftau.mftau.utils.PreferencesManager

class PreferenceFragment : PreferenceFragmentCompat() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val root = super.onCreateView(inflater, container, savedInstanceState)
        root.findViewById<Toolbar>(R.id.preferencesToolbar)
            ?.setNavigationOnClickListener { findNavController().navigateUp() }
        return root
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)

        preferenceManager.findPreference<Preference>(getString(R.string.night_mode_key))
            ?.setOnPreferenceChangeListener { _, newValue ->
                AppCompatDelegate.setDefaultNightMode(
                    if (newValue as Boolean) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
                )
                PreferencesManager.setNightMode(newValue)
                true
            }
        preferenceManager.findPreference<Preference>(getString(R.string.repeat_gospel_key))
            ?.setOnPreferenceChangeListener { _, newValue ->
                PreferencesManager.setRepeatGospel(newValue as Boolean)
                true
            }
        preferenceManager.findPreference<Preference>(getString(R.string.awake_songbook_key))
            ?.setOnPreferenceChangeListener { _, newValue ->
                PreferencesManager.setAwakeSongbook(newValue as Boolean)
                true
            }
    }
}
