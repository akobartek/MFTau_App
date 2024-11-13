package pl.mftau.mftau.leaders.presentation.people.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import pl.mftau.mftau.R
import pl.mftau.mftau.common.presentation.composables.TauAlertDialog

@Composable
fun EmausNoPeopleErrorDialog(
    isVisible: Boolean,
    onDismiss: () -> Unit
) {
    TauAlertDialog(
        isVisible = isVisible,
        painter = painterResource(id = R.drawable.ic_draws),
        dialogTitleId = R.string.emaus,
        dialogTextId = R.string.no_people_msg,
        confirmBtnTextId = R.string.ok,
        onConfirm = onDismiss,
        dismissible = true,
        onDismissRequest = onDismiss
    )
}