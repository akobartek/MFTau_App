package pl.mftau.mftau.leaders.presentation.people.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import pl.mftau.mftau.R
import pl.mftau.mftau.common.presentation.composables.TauAlertDialog

@Composable
fun DeleteLastDrawDialog(
    isVisible: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    TauAlertDialog(
        isVisible = isVisible,
        imageVector = Icons.Default.Delete,
        dialogTitleId = R.string.delete_last_draw,
        dialogTextId = R.string.delete_last_draw_msg,
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