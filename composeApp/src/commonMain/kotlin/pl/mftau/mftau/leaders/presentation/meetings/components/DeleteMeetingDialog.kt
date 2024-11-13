package pl.mftau.mftau.leaders.presentation.meetings.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import pl.mftau.mftau.R
import pl.mftau.mftau.common.presentation.composables.TauAlertDialog

@Composable
fun DeleteMeetingDialog(
    isVisible: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    TauAlertDialog(
        isVisible = isVisible,
        imageVector = Icons.Default.Delete,
        dialogTitleId = R.string.delete_meeting,
        dialogTextId = R.string.meeting_delete_dialog_msg,
        confirmBtnTextId = R.string.delete,
        onConfirm = {
            onDismiss()
            onConfirm()
        },
        dismissible = true,
        onDismissRequest = onDismiss,
        dismissBtnTextId = R.string.cancel
    )
}