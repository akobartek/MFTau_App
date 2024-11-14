package pl.mftau.mftau.leaders.presentation.emaus.composables

import androidx.compose.runtime.Composable
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.draws_full_msg
import mftau.composeapp.generated.resources.emaus
import mftau.composeapp.generated.resources.ic_draws
import mftau.composeapp.generated.resources.no
import mftau.composeapp.generated.resources.yes
import org.jetbrains.compose.resources.vectorResource
import pl.mftau.mftau.common.presentation.composables.TauAlertDialog

@Composable
fun EmausFullListDialog(
    isVisible: Boolean,
    onReset: () -> Unit,
    onDismiss: () -> Unit,
) {
    TauAlertDialog(
        isVisible = isVisible,
        imageVector = vectorResource(Res.drawable.ic_draws),
        dialogTitleId = Res.string.emaus,
        dialogTextId = Res.string.draws_full_msg,
        confirmBtnTextId = Res.string.yes,
        onConfirm = {
            onReset()
            onDismiss()
        },
        dismissible = false,
        onDismissRequest = onDismiss,
        dismissBtnTextId = Res.string.no,
    )
}