package pl.mftau.mftau

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.compose.KoinContext
import pl.mftau.mftau.core.data.PreferencesRepository
import pl.mftau.mftau.core.data.UserPreferences
import pl.mftau.mftau.core.data.dataStore
import pl.mftau.mftau.core.presentation.screens.MainScreen
import pl.mftau.mftau.ui.CustomTransition
import pl.mftau.mftau.ui.theme.MFTauTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            runBlocking {
                AppCompatDelegate.setDefaultNightMode(
                    if (PreferencesRepository(dataStore).getNightMode())
                        AppCompatDelegate.MODE_NIGHT_YES
                    else AppCompatDelegate.MODE_NIGHT_NO
                )
            }
        }
        // TODO() -> handle shortcuts

        setContent {
            val context = LocalContext.current
            val preferences = PreferencesRepository(context.dataStore).preferencesFlow
                .collectAsStateWithLifecycle(
                    initialValue = UserPreferences(
                        nightMode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
                    )
                )

            KoinContext {
                MFTauTheme(
                    darkTheme = preferences.value.nightMode,
                    dynamicColor = preferences.value.dynamicColors
                ) {
                    Surface(color = MaterialTheme.colorScheme.background) {
                        Navigator(screen = MainScreen()) { navigator ->
                            CustomTransition(navigator = navigator)
                        }
                    }
                }
            }
        }
    }
}