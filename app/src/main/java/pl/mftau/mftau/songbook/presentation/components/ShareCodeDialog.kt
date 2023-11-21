package pl.mftau.mftau.songbook.presentation.components

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pl.mftau.mftau.R
import pl.mftau.mftau.core.utils.copyToClipboard
import pl.mftau.mftau.ui.theme.mfTauFont

@Composable
fun ShareCodeDialog(
    code: String,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

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
                text = stringResource(id = R.string.share_playlist),
                fontFamily = mfTauFont
            )
        },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                if (code.isBlank()) CircularProgressIndicator()
                else {
                    Text(text = stringResource(id = R.string.share_playlist_dialog_msg))
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
                context.copyToClipboard(code, "playlist")
                onDismiss()
            }) {
                Text(stringResource(id = R.string.copy))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(id = R.string.cancel))
            }
        },
    )
}