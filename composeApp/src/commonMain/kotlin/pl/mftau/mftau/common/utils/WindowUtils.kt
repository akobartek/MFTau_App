package pl.mftau.mftau.common.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import pl.mftau.mftau.ui.theme.ColorTheme

@Composable
expect fun getScreenHeight(): Dp

@Composable
expect fun getScreenWidth(): Dp

@Composable
expect fun SetColorTheme(colorTheme: ColorTheme)

@Composable
expect fun SetKeepScreenAwakeWindowFlag(keepAwake: Boolean)