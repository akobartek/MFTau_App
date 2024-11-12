package pl.mftau.mftau.leaders.presentation.meetings.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Balance
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import pl.mftau.mftau.R
import pl.mftau.mftau.ui.theme.mfTauFont

@Composable
fun AbsenceReasonDialog(
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit,
    currentReason: String
) {
    var reason by rememberSaveable { mutableStateOf(currentReason) }

    AlertDialog(
        icon = {
            Icon(
                imageVector = Icons.Default.Balance,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        },
        title = {
            Text(
                text = stringResource(id = R.string.justify_absence),
                fontFamily = mfTauFont
            )
        },
        text = {
            Column {
                Text(text = stringResource(id = R.string.absence_dialog_message))
                OutlinedTextField(
                    value = reason,
                    onValueChange = { reason = it },
                    singleLine = true,
                    placeholder = { Text(text = stringResource(id = R.string.reason)) },
                    keyboardOptions = KeyboardOptions.Default.copy(capitalization = KeyboardCapitalization.Sentences),
                    trailingIcon = {
                        if (reason.isNotBlank())
                            IconButton(onClick = { reason = "" }) {
                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = stringResource(id = R.string.cd_clear_field)
                                )
                            }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                )
            }
        },
        onDismissRequest = {},
        confirmButton = {
            TextButton(onClick = { onConfirm(reason) }) {
                Text(stringResource(id = R.string.save))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(id = R.string.cancel))
            }
        },
    )
}