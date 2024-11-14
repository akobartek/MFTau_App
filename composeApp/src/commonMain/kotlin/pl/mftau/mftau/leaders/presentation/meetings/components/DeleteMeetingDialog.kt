package pl.mftau.mftau.leaders.presentation.meetings.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.cancel
import mftau.composeapp.generated.resources.delete
import mftau.composeapp.generated.resources.delete_meeting
import mftau.composeapp.generated.resources.meeting_delete_dialog_msg
import pl.mftau.mftau.common.presentation.composables.TauAlertDialog

@Composable
fun DeleteMeetingDialog(
    isVisible: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    TauAlertDialog(
        isVisible = isVisible,
        imageVector = Icons.Default.Delete,
        dialogTitleId = Res.string.delete_meeting,
        dialogTextId = Res.string.meeting_delete_dialog_msg,
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