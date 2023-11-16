package pl.mftau.mftau

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.get
import org.koin.compose.KoinContext
import pl.mftau.mftau.core.data.ColorTheme
import pl.mftau.mftau.core.data.PreferencesRepository
import pl.mftau.mftau.core.data.UserPreferences
import pl.mftau.mftau.core.presentation.screens.MainScreen
import pl.mftau.mftau.ui.CustomTransition
import pl.mftau.mftau.ui.theme.MFTauTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferencesRepository = get<PreferencesRepository>()
        var currentTheme = ColorTheme.SYSTEM
        lifecycleScope.launch {
            runBlocking {
                currentTheme = preferencesRepository.getTheme()
                currentTheme.setupAppCompatDelegate()
            }
        }
        // TODO() -> handle shortcuts

        setContent {
            val preferences by preferencesRepository.preferencesFlow
                .collectAsStateWithLifecycle(initialValue = UserPreferences(colorTheme = currentTheme))
            KoinContext {
                MFTauTheme(
                    colorTheme = preferences.colorTheme,
                    dynamicColor = preferences.dynamicColors
                ) {
                    val color = MaterialTheme.colorScheme.primary
                    LaunchedEffect(key1 = preferences) {
                        preferencesRepository.updateAccentColor(color.toArgb())
                    }

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