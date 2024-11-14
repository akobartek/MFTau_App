package pl.mftau.mftau.leaders.presentation.emaus.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.runtime.Composable
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.cancel
import mftau.composeapp.generated.resources.delete
import mftau.composeapp.generated.resources.reset_draws
import mftau.composeapp.generated.resources.reset_draws_msg
import pl.mftau.mftau.common.presentation.composables.TauAlertDialog

@Composable
fun DeleteAllDrawsDialog(
    isVisible: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    TauAlertDialog(
        isVisible = isVisible,
        imageVector = Icons.Default.DeleteForever,
        dialogTitleId = Res.string.reset_draws,
        dialogTextId = Res.string.reset_draws_msg,
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