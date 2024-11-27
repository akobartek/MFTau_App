package pl.mftau.mftau.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowInsetsControllerCompat

@Composable
actual fun SystemMaterialTheme(
    content: @Composable () -> Unit,
    isDark: Boolean,
    dynamicEnabled: Boolean,
) {
    val view = LocalView.current
    val colorScheme = when {
        dynamicEnabled && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (isDark) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        isDark -> DarkColorScheme
        else -> LightColorScheme
    }

    if (!view.isInEditMode)
        SideEffect {
            val window = (view.context as Activity).window
            WindowInsetsControllerCompat(window, window.decorView).apply {
                isAppearanceLightStatusBars = !isDark
                isAppearanceLightNavigationBars = !isDark
            }
        }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
    )
}