package pl.mftau.mftau.core.presentation.settings.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Language
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalUriHandler
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.language_setting_dialog_title
import mftau.composeapp.generated.resources.language_setting_title
import mftau.composeapp.generated.resources.languages
import mftau.composeapp.generated.resources.languages_codes
import org.jetbrains.compose.resources.stringArrayResource
import pl.mftau.mftau.common.utils.UpdateLanguageAction
import pl.mftau.mftau.common.utils.getCurrentLanguage
import pl.mftau.mftau.common.utils.getUpdateLanguageAction


@Composable
fun LanguagePreferenceRow(language: String = getCurrentLanguage()) {
    val uriHandler = LocalUriHandler.current

    val values = stringArrayResource(Res.array.languages)
    val codes = stringArrayResource(Res.array.languages_codes)
    var currentLanguage by rememberSaveable { mutableStateOf(language) }

    val updateLanguageAction = getUpdateLanguageAction()
    val openUri = { uri: String -> uriHandler.openUri(uri) }

    SelectionPreferenceRow(
        titleId = Res.string.language_setting_title,
        currentValue = currentLanguage,
        values = values,
        codes = codes,
        dialogImageVector = Icons.Outlined.Language,
        dialogTitleId = Res.string.language_setting_dialog_title,
        onSave = { newLanguage ->
            currentLanguage = newLanguage
            if (updateLanguageAction is UpdateLanguageAction.RunAction)
                updateLanguageAction.action(newLanguage)
        },
        onClick = when (updateLanguageAction) {
            is UpdateLanguageAction.OpenSettings -> {
                { openUri(updateLanguageAction.settingsUri) }
            }
            is UpdateLanguageAction.RunAction -> null
        },
    )
}