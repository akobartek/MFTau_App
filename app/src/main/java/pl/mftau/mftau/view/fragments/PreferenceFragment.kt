package pl.mftau.mftau.view.fragments

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceFragmentCompat
import pl.mftau.mftau.R
import pl.mftau.mftau.viewmodel.MainViewModel

class PreferenceFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)

        preferenceManager.findPreference(getString(R.string.night_mode_key)).setOnPreferenceChangeListener { _, newValue ->
            fragmentManager?.beginTransaction()?.detach(this@PreferenceFragment)
                    ?.attach(this@PreferenceFragment)?.commit()
            activity?.let {
                ViewModelProviders.of(it).get(MainViewModel::class.java).isNightMode = newValue as Boolean
                it.recreate()
            }
            true
        }
    }
}
