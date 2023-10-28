package pl.mftau.mftau

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import cafe.adriel.voyager.navigator.Navigator
import pl.mftau.mftau.core.presentation.screens.MainScreen
import pl.mftau.mftau.ui.CustomTransition
import pl.mftau.mftau.ui.theme.MFTauTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO() -> handle shortcuts

        setContent {
            MFTauTheme(
                darkTheme = true,
                dynamicColor = false
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