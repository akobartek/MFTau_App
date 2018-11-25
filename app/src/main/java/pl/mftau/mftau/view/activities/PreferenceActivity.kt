package pl.mftau.mftau.view.activities

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.app.NavUtils
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_preference.*
import pl.mftau.mftau.R
import pl.mftau.mftau.viewmodel.PreferenceViewModel

class PreferenceActivity : AppCompatActivity() {

    private lateinit var mPreferenceViewModel: PreferenceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        if (PreferenceManager.getDefaultSharedPreferences(this@PreferenceActivity)
                        .getBoolean(getString(R.string.night_mode_key), false)) {
            setTheme(R.style.PreferenceFragmentDarkStyle)
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                window.statusBarColor = Color.WHITE
            }
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference)
        setSupportActionBar(preferencesToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        mPreferenceViewModel = ViewModelProviders.of(this@PreferenceActivity).get(PreferenceViewModel::class.java)

        supportFragmentManager.beginTransaction().replace(R.id.preferencesFragment, MyPreferenceFragment()).commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this@PreferenceActivity)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    class MyPreferenceFragment : PreferenceFragmentCompat() {

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            addPreferencesFromResource(R.xml.preferences)

            preferenceManager.findPreference(getString(R.string.night_mode_key)).setOnPreferenceChangeListener { _, newValue ->
                if (newValue as Boolean) {
                    context!!.setTheme(R.style.PreferenceFragmentDarkStyle)
                    activity!!.recreate()
                } else {
                    context!!.setTheme(R.style.AppTheme_Light)
                    activity!!.recreate()
                }
                true
            }
        }
    }
}
