package pl.mftau.mftau.core.presentation.settings.composables

import androidx.compose.runtime.Composable
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.dynamic_colors_summary
import mftau.composeapp.generated.resources.dynamic_colors_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun DynamicColorsPreferenceRow(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    SwitchPreferenceRow(
        title = stringResource(Res.string.dynamic_colors_title),
        summary = stringResource(Res.string.dynamic_colors_summary),
        checked = checked,
        onCheckedChange = onCheckedChange,
    )
}