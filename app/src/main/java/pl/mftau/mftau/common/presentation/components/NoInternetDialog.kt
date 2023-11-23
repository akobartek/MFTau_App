package pl.mftau.mftau.common.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SignalCellularConnectedNoInternet0Bar
import androidx.compose.runtime.Composable
import pl.mftau.mftau.R

@Composable
fun NoInternetDialog(
    isVisible: Boolean,
    onReconnect: () -> Unit,
    onDismiss: () -> Unit
) {
    TauAlertDialog(
        isVisible = isVisible,
        imageVector = Icons.Default.SignalCellularConnectedNoInternet0Bar,
        dialogTitleId = R.string.no_internet_title,
        dialogTextId = R.string.no_internet_reconnect_message,
        confirmBtnTextId = R.string.try_again,
        onConfirm = onReconnect,
        dismissBtnTextId = R.string.cancel,
        dismissible = false,
        onDismissRequest = onDismiss
    )
}