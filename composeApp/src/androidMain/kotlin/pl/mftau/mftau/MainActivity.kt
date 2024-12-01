package pl.mftau.mftau

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val shortcut = intent.getStringExtra("shortcut")
        intent.putExtra("shortcut", "")

        setContent {
            App(
                startDestination = when (shortcut) {
                    "songBook" -> Screen.SongBook
                    "breviary" -> Screen.BreviarySelect
                    else -> Screen.Home
                },
            )
        }
    }
}