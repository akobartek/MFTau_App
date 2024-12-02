package pl.mftau.mftau

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

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