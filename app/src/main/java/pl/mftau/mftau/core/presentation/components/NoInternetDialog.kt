package pl.mftau.mftau.core.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SignalCellularConnectedNoInternet0Bar
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import pl.mftau.mftau.R
import pl.mftau.mftau.ui.theme.mfTauFont

@Composable
fun NoInternetDialog(
    onReconnect: () -> Unit,
    onCancel: () -> Unit
) {
    AlertDialog(
        icon = {
            Icon(
                imageVector = Icons.Default.SignalCellularConnectedNoInternet0Bar,
                contentDescription = null
            )
        },
        title = {
            Text(
                text = stringResource(id = R.string.no_internet_title),
                fontFamily = mfTauFont
            )
        },
        text = { Text(text = stringResource(id = R.string.no_internet_reconnect_message)) },
        onDismissRequest = {},
        confirmButton = {
            TextButton(onClick = { onReconnect() }) {
                Text(stringResource(id = R.string.try_again))
            }
        },
        dismissButton = {
            TextButton(onClick = { onCancel() }) {
                Text(stringResource(id = R.string.cancel))
            }
        }
    )
}