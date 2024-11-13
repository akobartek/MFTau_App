package pl.mftau.mftau.core.presentation.settings.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Language
import androidx.compose.runtime.Composable
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.language_setting_dialog_title
import mftau.composeapp.generated.resources.language_setting_title
import mftau.composeapp.generated.resources.languages
import mftau.composeapp.generated.resources.languages_codes
import org.jetbrains.compose.resources.stringArrayResource


@Composable
fun LanguagePreferenceRow(
    currentLanguage: String,
    onSave: (String) -> Unit,
) {
    val values = stringArrayResource(Res.array.languages)
    val codes = stringArrayResource(Res.array.languages_codes)

    SelectionPreferenceRow(
        titleId = Res.string.language_setting_title,
        currentValue = currentLanguage,
        values = values,
        codes = codes,
        dialogImageVector = Icons.Outlined.Language,
        dialogTitleId = Res.string.language_setting_dialog_title,
        onSave = onSave,
    )
}