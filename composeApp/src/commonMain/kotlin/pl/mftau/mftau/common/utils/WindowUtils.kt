package pl.mftau.mftau.common.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

@Composable
expect fun getScreenHeight(): Dp

@Composable
expect fun getScreenWidth(): Dp