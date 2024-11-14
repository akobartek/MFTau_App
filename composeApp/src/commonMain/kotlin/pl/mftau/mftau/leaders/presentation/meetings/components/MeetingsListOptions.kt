package pl.mftau.mftau.leaders.presentation.meetings.components

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.DeleteForever
import androidx.compose.material.icons.outlined.QueryStats
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
import mftau.composeapp.generated.resources.clear_meetings
import mftau.composeapp.generated.resources.show_presence
import org.jetbrains.compose.resources.stringResource

@Composable
fun MeetingsOptionsIcon(
    showPresenceVisible: Boolean,
    onShowPresence: () -> Unit,
    onClearMeetings: () -> Unit,
) {
    var dropdownExpanded by rememberSaveable { mutableStateOf(false) }
    var clearMeetingsDialogVisible by rememberSaveable { mutableStateOf(false) }

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
        if (showPresenceVisible)
            DropdownMenuItem(
                text = { Text(text = stringResource(Res.string.show_presence)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.QueryStats,
                        contentDescription = null,
                    )
                },
                onClick = {
                    dropdownExpanded = false
                    onShowPresence()
                }
            )
        DropdownMenuItem(
            text = { Text(text = stringResource(Res.string.clear_meetings)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.DeleteForever,
                    contentDescription = null,
                )
            },
            onClick = {
                dropdownExpanded = false
                clearMeetingsDialogVisible = true
            }
        )
    }

    ClearMeetingsDialog(
        isVisible = clearMeetingsDialogVisible,
        onConfirm = onClearMeetings,
        onDismiss = { clearMeetingsDialogVisible = false },
    )
}