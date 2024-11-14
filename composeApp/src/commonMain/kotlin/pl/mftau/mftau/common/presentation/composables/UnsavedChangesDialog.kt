package pl.mftau.mftau.common.presentation.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.cancel
import mftau.composeapp.generated.resources.discard
import mftau.composeapp.generated.resources.unsaved_changes_dialog_msg
import mftau.composeapp.generated.resources.unsaved_changes_dialog_title

@Composable
fun UnsavedChangesDialog(
    isVisible: Boolean,
    onDiscard: () -> Unit,
    onDismiss: () -> Unit,
) {
    TauAlertDialog(
        isVisible = isVisible,
        imageVector = Icons.Default.Edit,
        dialogTitleId = Res.string.unsaved_changes_dialog_title,
        dialogTextId = Res.string.unsaved_changes_dialog_msg,
        confirmBtnTextId = Res.string.discard,
        onConfirm = {
            onDismiss()
            onDiscard()
        },
        dismissBtnTextId = Res.string.cancel,
        dismissible = false,
        onDismissRequest = onDismiss,
    )
}