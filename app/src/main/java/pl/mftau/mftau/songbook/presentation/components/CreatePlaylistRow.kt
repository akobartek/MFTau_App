package pl.mftau.mftau.songbook.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import pl.mftau.mftau.R

@Composable
fun CreatePlaylistRow(
    newPlaylistName: String,
    onChangeName: (String) -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = newPlaylistName,
        onValueChange = onChangeName,
        placeholder = { Text(stringResource(id = R.string.new_playlist_name)) },
        trailingIcon = {
            if (newPlaylistName.isNotBlank())
                IconButton(onClick = onSave) {
                    Icon(
                        imageVector = Icons.Filled.Save,
                        contentDescription = stringResource(id = R.string.save_playlist)
                    )
                }
        },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send),
        keyboardActions = KeyboardActions(onSend = { if (newPlaylistName.isNotBlank()) onSave() }),
        modifier = modifier.fillMaxWidth()
    )
}