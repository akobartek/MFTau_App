package pl.mftau.mftau

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var shortcut = intent.getStringExtra("shortcut")
        intent.putExtra("shortcut", "")

        setContent {
            App()
        }
    }

    private fun setKeepScreenAwakeWindowFlag(keepAwake: Boolean) {
        if (keepAwake) window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        else window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
}