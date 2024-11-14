package pl.mftau.mftau.leaders.presentation.emaus.composables

import androidx.compose.runtime.Composable
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.emaus
import mftau.composeapp.generated.resources.ic_draws
import mftau.composeapp.generated.resources.no_people_msg
import mftau.composeapp.generated.resources.ok
import org.jetbrains.compose.resources.vectorResource
import pl.mftau.mftau.common.presentation.composables.TauAlertDialog

@Composable
fun EmausNoPeopleErrorDialog(
    isVisible: Boolean,
    onDismiss: () -> Unit,
) {
    TauAlertDialog(
        isVisible = isVisible,
        imageVector = vectorResource(Res.drawable.ic_draws),
        dialogTitleId = Res.string.emaus,
        dialogTextId = Res.string.no_people_msg,
        confirmBtnTextId = Res.string.ok,
        onConfirm = onDismiss,
        dismissible = true,
        onDismissRequest = onDismiss,
    )
}