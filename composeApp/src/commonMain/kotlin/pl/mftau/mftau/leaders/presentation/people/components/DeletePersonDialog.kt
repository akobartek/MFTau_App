package pl.mftau.mftau.leaders.presentation.people.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.cancel
import mftau.composeapp.generated.resources.delete
import mftau.composeapp.generated.resources.delete_person
import mftau.composeapp.generated.resources.delete_person_dialog_msg
import pl.mftau.mftau.common.presentation.composables.TauAlertDialog

@Composable
fun DeletePersonDialog(
    isVisible: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    TauAlertDialog(
        isVisible = isVisible,
        imageVector = Icons.Default.Delete,
        dialogTitleId = Res.string.delete_person,
        dialogTextId = Res.string.delete_person_dialog_msg,
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