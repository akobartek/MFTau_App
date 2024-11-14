package pl.mftau.mftau.songbook.presentation.playlists.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.cancel
import mftau.composeapp.generated.resources.copy
import mftau.composeapp.generated.resources.share_playlist
import mftau.composeapp.generated.resources.share_playlist_dialog_msg
import org.jetbrains.compose.resources.stringResource
import pl.mftau.mftau.common.presentation.snackbars.SnackbarController
import pl.mftau.mftau.common.presentation.snackbars.SnackbarEvent
import pl.mftau.mftau.ui.theme.mfTauFont

@Composable
fun ShareCodeDialog(
    isVisible: Boolean,
    code: String,
    onDismiss: () -> Unit,
) {
    val clipboard = LocalClipboardManager.current
    val coroutineScope = rememberCoroutineScope()

    if (isVisible)
        AlertDialog(
            icon = {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            },
            title = {
                Text(
                    text = stringResource(Res.string.share_playlist),
                    fontFamily = mfTauFont(),
                )
            },
            text = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    if (code.isBlank()) CircularProgressIndicator()
                    else {
                        Text(text = stringResource(Res.string.share_playlist_dialog_msg))
                        Spacer(modifier = Modifier.height(8.dp))
                        SelectionContainer {
                            Text(text = code)
                        }
                    }
                }
            },
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = {
                    clipboard.setText(AnnotatedString(code))
                    coroutineScope.launch {
                        SnackbarController.sendEvent(SnackbarEvent.CopiedToClipboard)
                    }
                    onDismiss()
                }) {
                    Text(stringResource(Res.string.copy))
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text(stringResource(Res.string.cancel))
                }
            },
        )
}