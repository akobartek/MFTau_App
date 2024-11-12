package pl.mftau.mftau

import androidx.compose.ui.window.ComposeUIViewController
import pl.mftau.mftau.common.utils.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) { App() }