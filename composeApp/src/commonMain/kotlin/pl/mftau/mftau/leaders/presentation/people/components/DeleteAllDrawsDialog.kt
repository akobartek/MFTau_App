package pl.mftau.mftau.leaders.presentation.people.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.runtime.Composable
import pl.mftau.mftau.R
import pl.mftau.mftau.common.presentation.composables.TauAlertDialog

@Composable
fun DeleteAllDrawsDialog(
    isVisible: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    TauAlertDialog(
        isVisible = isVisible,
        imageVector = Icons.Default.DeleteForever,
        dialogTitleId = R.string.reset_draws,
        dialogTextId = R.string.reset_draws_msg,
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