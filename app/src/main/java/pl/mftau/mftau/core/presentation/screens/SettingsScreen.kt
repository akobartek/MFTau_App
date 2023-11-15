package pl.mftau.mftau.core.presentation.screens

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import pl.mftau.mftau.R
import pl.mftau.mftau.core.data.UserPreferences
import pl.mftau.mftau.core.presentation.components.TauTopBar
import pl.mftau.mftau.core.presentation.screenmodels.SettingsScreenModel

class SettingsScreen : Screen {
    @Composable
    override fun Content() {
        SettingsScreenContent(screenModel = getScreenModel())
    }
}

@Composable
fun SettingsScreenContent(screenModel: SettingsScreenModel) {
    val navigator = LocalNavigator.currentOrThrow
    val preferences by screenModel.preferencesFlow
        .collectAsStateWithLifecycle(initialValue = UserPreferences())

    Scaffold(
        topBar = {
            TauTopBar(
                title = stringResource(id = R.string.settings),
                onNavClick = navigator::pop
            )
        }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            SwitchPreferenceRow(
                title = stringResource(id = R.string.night_mode_title),
                summary = stringResource(id = R.string.night_mode_summary),
                checked = preferences.nightMode,
                onCheckedChange = { value ->
                    AppCompatDelegate.setDefaultNightMode(
                        if (value) AppCompatDelegate.MODE_NIGHT_YES
                        else AppCompatDelegate.MODE_NIGHT_NO
                    )
                    screenModel.updateNightMode(value)
                }
            )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
                SwitchPreferenceRow(
                    title = stringResource(id = R.string.dynamic_colors_title),
                    summary = stringResource(id = R.string.dynamic_colors_summary),
                    checked = preferences.dynamicColors,
                    onCheckedChange = screenModel::updateDynamicColors
                )
            SwitchPreferenceRow(
                title = stringResource(id = R.string.repeat_gospel_title),
                summary = stringResource(id = R.string.repeat_gospel_summary),
                checked = preferences.repeatGospel,
                onCheckedChange = screenModel::updateRepeatGospel
            )
            SwitchPreferenceRow(
                title = stringResource(id = R.string.awake_song_book_title),
                summary = stringResource(id = R.string.awake_song_book_summary),
                checked = preferences.keepSongBookAwake,
                onCheckedChange = screenModel::updateKeepSongBookAwake
            )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                LanguagePreferenceRow()
        }
    }
}

@Composable
private fun SwitchPreferenceRow(
    title: String,
    summary: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = summary,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            thumbContent = if (checked) {
                {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize),
                    )
                }
            } else null
        )
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
private fun LanguagePreferenceRow() {
    val localeManager =
        LocalContext.current.getSystemService(Context.LOCALE_SERVICE) as LocaleManager
    var currentLanguage by remember {
        mutableStateOf(localeManager.applicationLocales[0]?.language)
    }
    val languages = stringArrayResource(id = R.array.languages)
    val languageTags = stringArrayResource(id = R.array.languages_codes)

    var dialogVisible by remember { mutableStateOf(false) }
    val (selectedLanguage, onLanguageSelected) = remember {
        val index = languageTags.indexOf(currentLanguage)
        mutableStateOf(languages[if (index != -1) index else 0])
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { dialogVisible = true }
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(id = R.string.language_setting_title),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = stringResource(
                    id = R.string.language_setting_summary,
                    when (currentLanguage) {
                        "pl" -> stringResource(id = R.string.lang_pl)
                        "en" -> stringResource(id = R.string.lang_en)
                        else -> stringResource(id = R.string.lang_system)
                    }
                ),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }

    if (dialogVisible) {
        AlertDialog(
            icon = { Icon(imageVector = Icons.Default.Language, contentDescription = null) },
            title = { Text(text = stringResource(id = R.string.language_setting_dialog_title)) },
            text = {
                Column(Modifier.selectableGroup()) {
                    languages.forEach { lang ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .selectable(
                                    selected = (lang == selectedLanguage),
                                    onClick = { onLanguageSelected(lang) },
                                    role = Role.RadioButton
                                )
                                .padding(horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (lang == selectedLanguage),
                                onClick = null
                            )
                            Text(
                                text = lang,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }
                    }
                }
            },
            onDismissRequest = {},
            confirmButton = {
                TextButton(
                    onClick = {
                        currentLanguage = languageTags[languages.indexOf(selectedLanguage)]
                        dialogVisible = false
                        localeManager.applicationLocales =
                            LocaleList.forLanguageTags(currentLanguage)
                    }
                ) {
                    Text(stringResource(id = R.string.save))
                }
            },
            dismissButton = {
                TextButton(onClick = { dialogVisible = false }) {
                    Text(stringResource(id = R.string.cancel))
                }
            }
        )
    }
}