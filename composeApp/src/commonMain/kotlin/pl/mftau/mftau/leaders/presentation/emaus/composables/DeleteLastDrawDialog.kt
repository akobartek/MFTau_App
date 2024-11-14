package pl.mftau.mftau.leaders.presentation.emaus.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.cancel
import mftau.composeapp.generated.resources.delete
import mftau.composeapp.generated.resources.delete_last_draw
import mftau.composeapp.generated.resources.delete_last_draw_msg
import pl.mftau.mftau.common.presentation.composables.TauAlertDialog

@Composable
fun DeleteLastDrawDialog(
    isVisible: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    TauAlertDialog(
        isVisible = isVisible,
        imageVector = Icons.Default.Delete,
        dialogTitleId = Res.string.delete_last_draw,
        dialogTextId = Res.string.delete_last_draw_msg,
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