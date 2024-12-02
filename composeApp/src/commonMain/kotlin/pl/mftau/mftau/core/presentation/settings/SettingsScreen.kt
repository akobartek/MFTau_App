package pl.mftau.mftau.core.presentation.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.awake_screen_summary
import mftau.composeapp.generated.resources.awake_screen_title
import mftau.composeapp.generated.resources.repeat_gospel_summary
import mftau.composeapp.generated.resources.repeat_gospel_title
import mftau.composeapp.generated.resources.settings
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import pl.mftau.mftau.common.data.UserPreferences
import pl.mftau.mftau.common.presentation.composables.TauCenteredTopBar
import pl.mftau.mftau.common.utils.dynamicColorsAvailable
import pl.mftau.mftau.common.utils.getCurrentLanguage
import pl.mftau.mftau.common.utils.getUpdateLocaleFunction
import pl.mftau.mftau.core.presentation.settings.composables.DynamicColorsPreferenceRow
import pl.mftau.mftau.core.presentation.settings.composables.LanguagePreferenceRow
import pl.mftau.mftau.core.presentation.settings.composables.SwitchPreferenceRow
import pl.mftau.mftau.core.presentation.settings.composables.ThemePreferenceRow

@Composable
fun SettingsScreen(
    navigateUp: () -> Unit,
    viewModel: SettingsViewModel = koinInject(),
) {
    val state by viewModel.preferencesFlow.collectAsStateWithLifecycle(initialValue = UserPreferences())

    SettingsScreenContent(
        navigateUp = navigateUp,
        preferences = state,
        language = getCurrentLanguage(),
        updateNightMode = viewModel::updateNightMode,
        updateDynamicColors = viewModel::updateDynamicColors,
        updateKeepScreenAwake = viewModel::updateKeepScreenAwake,
        updateRepeatGospel = viewModel::updateRepeatGospel,
    )
}

@Composable
fun SettingsScreenContent(
    navigateUp: () -> Unit,
    preferences: UserPreferences,
    language: String?,
    updateNightMode: (String) -> Unit,
    updateDynamicColors: (Boolean) -> Unit,
    updateKeepScreenAwake: (Boolean) -> Unit,
    updateRepeatGospel: (Boolean) -> Unit,
) {
    var currentLanguage by rememberSaveable { mutableStateOf(language) }
    val updateLocaleFunction = getUpdateLocaleFunction()

    Scaffold(
        topBar = {
            TauCenteredTopBar(
                title = stringResource(Res.string.settings),
                onNavClick = navigateUp,
            )
        }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            ThemePreferenceRow(
                currentTheme = preferences.colorTheme,
                onThemeChange = updateNightMode,
            )
            if (dynamicColorsAvailable())
                DynamicColorsPreferenceRow(
                    checked = preferences.dynamicColors,
                    onCheckedChange = updateDynamicColors,
                )
            SwitchPreferenceRow(
                title = stringResource(Res.string.awake_screen_title),
                summary = stringResource(Res.string.awake_screen_summary),
                checked = preferences.keepScreenAwake,
                onCheckedChange = updateKeepScreenAwake,
            )
            SwitchPreferenceRow(
                title = stringResource(Res.string.repeat_gospel_title),
                summary = stringResource(Res.string.repeat_gospel_summary),
                checked = preferences.repeatGospel,
                onCheckedChange = updateRepeatGospel,
            )
            currentLanguage?.let { lang ->
                LanguagePreferenceRow(
                    currentLanguage = lang,
                    onSave = { selectedLanguageCode ->
                        currentLanguage = selectedLanguageCode
                        updateLocaleFunction?.invoke(selectedLanguageCode)
                    }
                )
            }
        }
    }
}