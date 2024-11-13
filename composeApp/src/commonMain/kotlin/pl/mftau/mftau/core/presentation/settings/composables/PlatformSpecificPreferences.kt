package pl.mftau.mftau.core.presentation.settings.composables

import androidx.compose.runtime.Composable

@Composable
expect fun DynamicColorsPreferenceRow(checked: Boolean, onCheckedChange: (Boolean) -> Unit)

@Composable
expect fun getCurrentLanguage(): String?

@Composable
expect fun getUpdateLocaleFunction(): ((String) -> Unit)?