package pl.mftau.mftau.leaders.presentation.emaus.composables

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.CopyAll
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.DeleteForever
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.cd_more_options_btn
import mftau.composeapp.generated.resources.copy_draw
import mftau.composeapp.generated.resources.delete_last_draw
import mftau.composeapp.generated.resources.reset_draws
import org.jetbrains.compose.resources.stringResource

@Composable
fun EmausOptionsIcon(
    onCopyDraws: () -> Unit,
    onDeleteLastDraw: () -> Unit,
    onResetDraws: () -> Unit,
) {
    var dropdownExpanded by rememberSaveable { mutableStateOf(false) }
    var deleteLastDrawDialogVisible by rememberSaveable { mutableStateOf(false) }
    var resetDrawsDialogVisible by rememberSaveable { mutableStateOf(false) }

    IconButton(onClick = { dropdownExpanded = !dropdownExpanded }) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = stringResource(Res.string.cd_more_options_btn),
        )
    }
    DropdownMenu(
        expanded = dropdownExpanded,
        onDismissRequest = { dropdownExpanded = false },
        modifier = Modifier.defaultMinSize(minWidth = 200.dp),
    ) {
        DropdownMenuItem(
            text = { Text(text = stringResource(Res.string.copy_draw)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.CopyAll,
                    contentDescription = null,
                )
            },
            onClick = {
                dropdownExpanded = false
                onCopyDraws()
            }
        )
        DropdownMenuItem(
            text = { Text(text = stringResource(Res.string.delete_last_draw)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = null,
                )
            },
            onClick = {
                dropdownExpanded = false
                deleteLastDrawDialogVisible = true
            }
        )
        DropdownMenuItem(
            text = { Text(text = stringResource(Res.string.reset_draws)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.DeleteForever,
                    contentDescription = null,
                )
            },
            onClick = {
                dropdownExpanded = false
                resetDrawsDialogVisible = true
            }
        )
    }

    DeleteLastDrawDialog(
        isVisible = deleteLastDrawDialogVisible,
        onConfirm = onDeleteLastDraw,
        onDismiss = { deleteLastDrawDialogVisible = false },
    )

    DeleteAllDrawsDialog(
        isVisible = resetDrawsDialogVisible,
        onConfirm = onResetDraws,
        onDismiss = { resetDrawsDialogVisible = false },
    )
}