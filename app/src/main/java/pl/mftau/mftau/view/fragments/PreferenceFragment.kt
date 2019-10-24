package pl.mftau.mftau.view.fragments

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import pl.mftau.mftau.R
import pl.mftau.mftau.viewmodel.MainViewModel

class PreferenceFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)

        preferenceManager.findPreference<Preference>(getString(R.string.night_mode_key))
            ?.setOnPreferenceChangeListener { _, newValue ->
                parentFragmentManager.beginTransaction().detach(this@PreferenceFragment)
                    .attach(this@PreferenceFragment).commit()
                activity?.let {
                    ViewModelProvider(it).get(MainViewModel::class.java).isNightMode = newValue as Boolean
                    it.recreate()
                }
                true
            }
    }
}
