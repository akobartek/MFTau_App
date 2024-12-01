package pl.mftau.mftau.common.utils

import androidx.compose.runtime.Composable
import pl.mftau.mftau.ui.theme.ColorTheme

@Composable
expect fun SetColorTheme(colorTheme: ColorTheme)

@Composable
expect fun SetKeepScreenAwakeWindowFlag(keepAwake: Boolean)