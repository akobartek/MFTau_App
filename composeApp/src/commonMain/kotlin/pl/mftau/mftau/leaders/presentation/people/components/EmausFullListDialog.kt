package pl.mftau.mftau.leaders.presentation.people.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import pl.mftau.mftau.R
import pl.mftau.mftau.common.presentation.composables.TauAlertDialog

@Composable
fun EmausFullListDialog(
    isVisible: Boolean,
    onReset: () -> Unit,
    onDismiss: () -> Unit
) {
    TauAlertDialog(
        isVisible = isVisible,
        painter = painterResource(id = R.drawable.ic_draws),
        dialogTitleId = R.string.emaus,
        dialogTextId = R.string.draws_full_msg,
        confirmBtnTextId = R.string.yes,
        onConfirm = {
            onReset()
            onDismiss()
        },
        dismissible = false,
        onDismissRequest = onDismiss,
        dismissBtnTextId = R.string.no
    )
}