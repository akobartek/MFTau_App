package pl.mftau.mftau.common.utils

import androidx.compose.runtime.Composable

@Composable
expect fun BackHandler(onBack: () -> Unit)