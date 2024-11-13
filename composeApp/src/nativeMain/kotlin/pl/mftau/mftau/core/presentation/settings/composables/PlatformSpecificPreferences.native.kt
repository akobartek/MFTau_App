package pl.mftau.mftau.core.presentation.settings.composables

import androidx.compose.runtime.Composable

@Composable
actual fun DynamicColorsPreferenceRow(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    // not available on iOS
}

@Composable
actual fun getCurrentLanguage(): String? = null

@Composable
actual fun getUpdateLocaleFunction(): ((String) -> Unit)? = null