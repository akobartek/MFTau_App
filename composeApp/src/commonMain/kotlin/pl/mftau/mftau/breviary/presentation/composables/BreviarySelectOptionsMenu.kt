package pl.mftau.mftau.breviary.presentation.composables

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.breviary_today
import mftau.composeapp.generated.resources.breviary_tomorrow
import mftau.composeapp.generated.resources.breviary_yesterday
import mftau.composeapp.generated.resources.saving_breviary
import org.jetbrains.compose.resources.stringResource

@Composable
fun BreviarySelectOptionsMenu(
    expanded: Boolean,
    daysFromToday: Int,
    onDismissRequest: () -> Unit,
    onSaveBreviary: () -> Unit,
    onUpdateDays: (Int) -> Unit,
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = Modifier.defaultMinSize(minWidth = 200.dp)
    ) {
        DropdownMenuItem(
            text = {
                Text(text = stringResource(Res.string.saving_breviary))
            },
            onClick = {
                onDismissRequest()
                onSaveBreviary()
            }
        )
        if (daysFromToday != -1)
            DropdownMenuItem(
                text = {
                    Text(text = stringResource(Res.string.breviary_yesterday))
                },
                onClick = {
                    onDismissRequest()
                    onUpdateDays(-1)
                }
            )
        if (daysFromToday != 0)
            DropdownMenuItem(
                text = {
                    Text(text = stringResource(Res.string.breviary_today))
                },
                onClick = {
                    onDismissRequest()
                    onUpdateDays(0)
                }
            )
        if (daysFromToday != 1)
            DropdownMenuItem(
                text = {
                    Text(text = stringResource(Res.string.breviary_tomorrow))
                },
                onClick = {
                    onDismissRequest()
                    onUpdateDays(1)
                }
            )
    }
}