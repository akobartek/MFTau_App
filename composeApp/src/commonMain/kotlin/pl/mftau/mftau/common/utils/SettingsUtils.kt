package pl.mftau.mftau.common.utils

import androidx.compose.runtime.Composable
import pl.mftau.mftau.ui.theme.ColorTheme

@Composable
expect fun SetColorTheme(colorTheme: ColorTheme)

@Composable
expect fun dynamicColorsAvailable(): Boolean

@Composable
expect fun SetKeepScreenAwakeWindowFlag(keepAwake: Boolean)

@Composable
expect fun getCurrentLanguage(): String

@Composable
expect fun getUpdateLanguageAction(): UpdateLanguageAction

sealed class UpdateLanguageAction {
    data class OpenSettings(val settingsUri: String) : UpdateLanguageAction()
    data class RunAction(val action: (String) -> Unit) : UpdateLanguageAction()
}