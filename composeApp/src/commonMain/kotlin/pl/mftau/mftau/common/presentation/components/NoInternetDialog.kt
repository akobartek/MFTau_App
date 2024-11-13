package pl.mftau.mftau.common.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SignalCellularConnectedNoInternet0Bar
import androidx.compose.runtime.Composable
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.cancel
import mftau.composeapp.generated.resources.no_internet_reconnect_message
import mftau.composeapp.generated.resources.no_internet_title
import mftau.composeapp.generated.resources.try_again

@Composable
fun NoInternetDialog(
    isVisible: Boolean,
    onReconnect: () -> Unit,
    onDismiss: () -> Unit
) {
    TauAlertDialog(
        isVisible = isVisible,
        imageVector = Icons.Default.SignalCellularConnectedNoInternet0Bar,
        dialogTitleId = Res.string.no_internet_title,
        dialogTextId = Res.string.no_internet_reconnect_message,
        confirmBtnTextId = Res.string.try_again,
        onConfirm = onReconnect,
        dismissBtnTextId = Res.string.cancel,
        dismissible = false,
        onDismissRequest = onDismiss
    )
}