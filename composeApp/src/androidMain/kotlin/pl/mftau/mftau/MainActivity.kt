package pl.mftau.mftau

import android.app.UiModeManager
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.mftau.mftau.core.presentation.AppViewModel
import pl.mftau.mftau.ui.theme.ColorTheme
import pl.mftau.mftau.ui.theme.ColorTheme.DARK
import pl.mftau.mftau.ui.theme.ColorTheme.LIGHT

class MainActivity : ComponentActivity() {

    private val viewModel: AppViewModel by viewModel()
    private var activityInit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val shortcut = intent.getStringExtra("shortcut")
        intent.putExtra("shortcut", "")

        setContent {
            val preferences by viewModel.preferences.collectAsStateWithLifecycle()

            LaunchedEffect(preferences) {
                setKeepScreenAwakeWindowFlag(preferences.keepScreenAwake)
            }
            LaunchedEffect(preferences.colorTheme) {
                setupColorTheme(preferences.colorTheme)
                if (activityInit)
                    this@MainActivity.recreate()
                activityInit = true
            }

            App(
                startDestination = when (shortcut) {
                    "songBook" -> Screen.SongBook
                    "breviary" -> Screen.BreviarySelect
                    else -> Screen.Home
                },
            )
        }
    }

    private fun setKeepScreenAwakeWindowFlag(keepAwake: Boolean) {
        if (keepAwake) window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        else window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    private fun setupColorTheme(theme: ColorTheme) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S)
            AppCompatDelegate.setDefaultNightMode(
                when (theme) {
                    DARK -> AppCompatDelegate.MODE_NIGHT_YES
                    LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
                    else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                }
            )
        else
            (getSystemService(UI_MODE_SERVICE) as UiModeManager).setApplicationNightMode(
                when (theme) {
                    DARK -> UiModeManager.MODE_NIGHT_YES
                    LIGHT -> UiModeManager.MODE_NIGHT_NO
                    else -> UiModeManager.MODE_NIGHT_AUTO
                }
            )
    }
}