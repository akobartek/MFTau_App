package pl.mftau.mftau.leaders.presentation.meetings.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.runtime.Composable
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.cancel
import mftau.composeapp.generated.resources.clear_meetings
import mftau.composeapp.generated.resources.clear_meetings_dialog_msg
import mftau.composeapp.generated.resources.delete
import pl.mftau.mftau.common.presentation.composables.TauAlertDialog

@Composable
fun ClearMeetingsDialog(
    isVisible: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    TauAlertDialog(
        isVisible = isVisible,
        imageVector = Icons.Default.DeleteForever,
        dialogTitleId = Res.string.clear_meetings,
        dialogTextId = Res.string.clear_meetings_dialog_msg,
        confirmBtnTextId = Res.string.delete,
        onConfirm = {
            onDismiss()
            onConfirm()
        },
        dismissible = true,
        onDismissRequest = onDismiss,
        dismissBtnTextId = Res.string.cancel,
    )
}