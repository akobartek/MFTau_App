package pl.mftau.mftau.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = TauPrimaryDark,
    secondary = TauSecondaryDark,
    tertiary = TauTertiaryDark,
    onPrimary = OnTauPrimaryDark,
    primaryContainer = TauPrimaryContainerDark,
    onPrimaryContainer = OnTauPrimaryContainerDark,
    onSecondary = OnTauSecondaryDark,
    secondaryContainer = TauSecondaryContainerDark,
    onSecondaryContainer = OnTauSecondaryContainerDark,
    onTertiary = OnTauTertiaryDark,
    onTertiaryContainer = OnTauTertiaryContainerDark,
    tertiaryContainer = TauTertiaryContainerDark,
    background = BackgroundDark,
    onBackground = OnBackgroundDark,
    surface = SurfaceDark,
    onSurface = OnSurfaceDark,
    surfaceVariant = SurfaceVariantDark,
    onSurfaceVariant = OnSurfaceVariantDark,
    error = ErrorDark,
    onError = OnErrorDark,
    errorContainer = ErrorContainerDark,
    onErrorContainer = OnErrorContainerDark,
    outline = OutlineDark,
)

private val LightColorScheme = lightColorScheme(
    primary = TauPrimaryLight,
    secondary = TauSecondaryLight,
    tertiary = TauTertiaryLight,
    onPrimary = OnTauPrimaryLight,
    primaryContainer = TauPrimaryContainerLight,
    onPrimaryContainer = OnTauPrimaryContainerLight,
    onSecondary = OnTauSecondaryLight,
    secondaryContainer = TauSecondaryContainerLight,
    onSecondaryContainer = OnTauSecondaryContainerLight,
    onTertiary = OnTauTertiaryLight,
    onTertiaryContainer = OnTauTertiaryContainerLight,
    tertiaryContainer = TauTertiaryContainerLight,
    background = BackgroundLight,
    onBackground = OnBackgroundLight,
    surface = SurfaceLight,
    onSurface = OnSurfaceLight,
    surfaceVariant = SurfaceVariantLight,
    onSurfaceVariant = OnSurfaceVariantLight,
    error = ErrorLight,
    onError = OnErrorLight,
    errorContainer = ErrorContainerLight,
    onErrorContainer = OnErrorContainerLight,
    outline = OutlineLight,
)

@Composable
fun MFTauTheme(
    darkTheme: Boolean = true,
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}