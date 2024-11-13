package pl.mftau.mftau.core.presentation.settings.composables

import android.annotation.SuppressLint
import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.dynamic_colors_summary
import mftau.composeapp.generated.resources.dynamic_colors_title
import org.jetbrains.compose.resources.stringResource

@Composable
actual fun DynamicColorsPreferenceRow(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
        SwitchPreferenceRow(
            title = stringResource(Res.string.dynamic_colors_title),
            summary = stringResource(Res.string.dynamic_colors_summary),
            checked = checked,
            onCheckedChange = onCheckedChange,
        )
}

@Composable
actual fun getCurrentLanguage(): String? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val localeManager =
            LocalContext.current.getSystemService(Context.LOCALE_SERVICE) as LocaleManager
        localeManager.applicationLocales[0]?.language
    } else null

@SuppressLint("ComposableNaming")
@Composable
actual fun getUpdateLocaleFunction(): ((String) -> Unit)? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val localeManager =
            LocalContext.current.getSystemService(Context.LOCALE_SERVICE) as LocaleManager
        { newLanguage ->
            localeManager.applicationLocales = LocaleList.forLanguageTags(newLanguage)
        }
    } else null