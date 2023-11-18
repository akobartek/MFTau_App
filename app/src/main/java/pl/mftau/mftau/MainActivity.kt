package pl.mftau.mftau

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.navigator.Navigator
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.compose.KoinContext
import pl.mftau.mftau.core.presentation.screens.MainScreen
import pl.mftau.mftau.ui.CustomTransition
import pl.mftau.mftau.ui.theme.MFTauTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setOnExitAnimationListener { screen ->
                val zoomX = ObjectAnimator.ofFloat(screen.iconView, View.SCALE_X, 0.5f, 0.0f)
                zoomX.duration = 400L
                zoomX.doOnEnd {
                    screen.remove()
                    viewModel.splashScreenEnded()
                }

                val zoomY = ObjectAnimator.ofFloat(screen.iconView, View.SCALE_Y, 0.5f, 0.0f)
                zoomY.duration = 400L
                zoomY.doOnEnd {
                    screen.remove()
                    viewModel.splashScreenEnded()
                }

                zoomX.start()
                zoomY.start()
            }
        }

        // TODO() -> handle shortcuts

        setContent {
            val preferences by viewModel.preferences.collectAsStateWithLifecycle()
            val splashScreenEnded by viewModel.splashScreenEnded.collectAsStateWithLifecycle()

            KoinContext {
                MFTauTheme(
                    colorTheme = preferences.colorTheme,
                    dynamicColor = preferences.dynamicColors,
                    splashScreenEnded = splashScreenEnded
                ) {
                    val color = MaterialTheme.colorScheme.primary
                    LaunchedEffect(key1 = preferences) {
                        viewModel.updateAccentColor(color)
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