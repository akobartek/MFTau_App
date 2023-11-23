package pl.mftau.mftau.core.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import pl.mftau.mftau.R

@Composable
fun UnsavedChangesDialog(
    isVisible: Boolean,
    onDiscard: () -> Unit,
    onDismiss: () -> Unit
) {
    TauAlertDialog(
        isVisible = isVisible,
        imageVector = Icons.Default.Edit,
        dialogTitleId = R.string.unsaved_changes_dialog_title,
        dialogTextId = R.string.unsaved_changes_dialog_msg,
        confirmBtnTextId = R.string.discard,
        onConfirm = {
            onDismiss()
            onDiscard()
        },
        dismissBtnTextId = R.string.cancel,
        dismissible = false,
        onDismissRequest = onDismiss
    )
}