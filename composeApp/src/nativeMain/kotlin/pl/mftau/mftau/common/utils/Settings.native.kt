package pl.mftau.mftau.common.utils

import androidx.compose.runtime.Composable
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
actual fun SetKeepScreenAwakeWindowFlag(keepAwake: Boolean) {
    UIApplication.sharedApplication.setIdleTimerDisabled(keepAwake)
}