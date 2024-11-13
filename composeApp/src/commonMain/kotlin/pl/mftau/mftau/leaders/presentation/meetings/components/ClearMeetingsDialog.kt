package pl.mftau.mftau.leaders.presentation.meetings.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.runtime.Composable
import pl.mftau.mftau.R
import pl.mftau.mftau.common.presentation.composables.TauAlertDialog

@Composable
fun ClearMeetingsDialog(
    isVisible: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    TauAlertDialog(
        isVisible = isVisible,
        imageVector = Icons.Default.DeleteForever,
        dialogTitleId = R.string.clear_meetings,
        dialogTextId = R.string.clear_meetings_dialog_msg,
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