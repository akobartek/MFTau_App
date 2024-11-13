package pl.mftau.mftau.core.presentation.settings.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.runtime.Composable
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.theme_dialog_title
import mftau.composeapp.generated.resources.theme_title
import mftau.composeapp.generated.resources.themes
import mftau.composeapp.generated.resources.themes_codes
import org.jetbrains.compose.resources.stringArrayResource
import pl.mftau.mftau.ui.theme.ColorTheme

@Composable
fun ThemePreferenceRow(currentTheme: ColorTheme, onThemeChange: (String) -> Unit) {
    val values = stringArrayResource(Res.array.themes)
    val codes = stringArrayResource(Res.array.themes_codes)

    SelectionPreferenceRow(
        titleId = Res.string.theme_title,
        currentValue = currentTheme.value,
        values = values,
        codes = codes,
        dialogImageVector = Icons.Outlined.DarkMode,
        dialogTitleId = Res.string.theme_dialog_title,
        onSave = onThemeChange,
    )
}