package pl.mftau.mftau.common.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import pl.mftau.mftau.ui.theme.ColorTheme
import platform.UIKit.UIApplication
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun getScreenHeight(): Dp = LocalWindowInfo.current.containerSize.height.pxToPoint().dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun getScreenWidth(): Dp = LocalWindowInfo.current.containerSize.width.pxToPoint().dp

private fun Int.pxToPoint(): Double = this.toDouble() / UIScreen.mainScreen.scale

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