package pl.mftau.mftau.common.utils

import android.app.Activity.UI_MODE_SERVICE
import android.app.UiModeManager
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import pl.mftau.mftau.ui.theme.ColorTheme
import pl.mftau.mftau.ui.theme.ColorTheme.DARK
import pl.mftau.mftau.ui.theme.ColorTheme.LIGHT

@Composable
actual fun getScreenHeight(): Dp = LocalConfiguration.current.screenHeightDp.dp

@Composable
actual fun getScreenWidth(): Dp = LocalConfiguration.current.screenWidthDp.dp

@Composable
actual fun SetColorTheme(colorTheme: ColorTheme) {
    val context = LocalContext.current
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S)
        AppCompatDelegate.setDefaultNightMode(
            when (colorTheme) {
                DARK -> AppCompatDelegate.MODE_NIGHT_YES
                LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
                else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            }
        )
    else
        (context.getSystemService(UI_MODE_SERVICE) as UiModeManager).setApplicationNightMode(
            when (colorTheme) {
                DARK -> UiModeManager.MODE_NIGHT_YES
                LIGHT -> UiModeManager.MODE_NIGHT_NO
                else -> UiModeManager.MODE_NIGHT_AUTO
            }
        )
}

@Composable
actual fun SetKeepScreenAwakeWindowFlag(keepAwake: Boolean) {
    LocalContext.current.getActivity()?.window?.let { window ->
        if (keepAwake) window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        else window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
}

private fun Context.getActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}