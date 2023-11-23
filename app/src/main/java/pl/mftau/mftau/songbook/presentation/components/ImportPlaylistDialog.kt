package pl.mftau.mftau.songbook.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pl.mftau.mftau.R
import pl.mftau.mftau.ui.theme.mfTauFont

@Composable
fun ImportPlaylistDialog(
    onImport: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    var playlistCode by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    val validateCode = { code: String -> code.startsWith("sb:") || code.startsWith("fs:") }

    AlertDialog(
        icon = {
            Icon(
                imageVector = Icons.Default.Download,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        },
        title = {
            Text(
                text = stringResource(id = R.string.import_playlist),
                fontFamily = mfTauFont
            )
        },
        text = {
            Column {
                Text(text = stringResource(id = R.string.import_playlist_dialog_msg))
                OutlinedTextField(
                    value = playlistCode,
                    onValueChange = { playlistCode = it },
                    singleLine = true,
                    label = { Text(text = stringResource(id = R.string.playlist_code)) },
                    trailingIcon = {
                        if (playlistCode.isNotBlank())
                            IconButton(onClick = { playlistCode = "" }) {
                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = stringResource(id = R.string.cd_clear_field)
                                )
                            }
                    },
                    isError = isError,
                    supportingText = if (isError) {
                        {
                            Text(text = stringResource(id = R.string.import_incorrect_code_error))
                        }
                    } else null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                )
            }
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                if (validateCode(playlistCode)) {
                    onDismiss()
                    onImport(playlistCode)
                }
                else isError = true
            }) {
                Text(stringResource(id = R.string.import_btn))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(id = R.string.cancel))
            }
        },
    )
}