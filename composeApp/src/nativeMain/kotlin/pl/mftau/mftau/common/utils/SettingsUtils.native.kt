package pl.mftau.mftau.common.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.intl.Locale
import pl.mftau.mftau.ui.theme.ColorTheme
import platform.UIKit.UIApplication
import platform.UIKit.UIUserInterfaceStyle

@Composable
actual fun SetColorTheme(colorTheme: ColorTheme) {
    UIApplication.sharedApplication.keyWindow?.setOverrideUserInterfaceStyle(
        when (colorTheme) {
            ColorTheme.DARK -> UIUserInterfaceStyle.UIUserInterfaceStyleDark

            ColorTheme.LIGHT -> UIUserInterfaceStyle.UIUserInterfaceStyleLight

            ColorTheme.SYSTEM -> UIUserInterfaceStyle.UIUserInterfaceStyleUnspecified
        }
    )
}

@Composable
actual fun dynamicColorsAvailable(): Boolean = false

@Composable
actual fun SetKeepScreenAwakeWindowFlag(keepAwake: Boolean) {
    UIApplication.sharedApplication.setIdleTimerDisabled(keepAwake)
}

@Composable
actual fun getCurrentLanguage(): String? = Locale.current.language

@Composable
actual fun getUpdateLocaleFunction(): ((String) -> Unit)? = null