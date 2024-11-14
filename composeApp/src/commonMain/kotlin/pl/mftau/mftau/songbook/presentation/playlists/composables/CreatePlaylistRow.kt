package pl.mftau.mftau.songbook.presentation.playlists.composables

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
import androidx.compose.ui.text.input.ImeAction
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.new_playlist_name
import mftau.composeapp.generated.resources.save_playlist
import org.jetbrains.compose.resources.stringResource

@Composable
fun CreatePlaylistRow(
    newPlaylistName: String,
    onChangeName: (String) -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = newPlaylistName,
        onValueChange = onChangeName,
        label = { Text(stringResource(Res.string.new_playlist_name)) },
        trailingIcon = {
            if (newPlaylistName.isNotBlank())
                IconButton(onClick = onSave) {
                    Icon(
                        imageVector = Icons.Filled.Save,
                        contentDescription = stringResource(Res.string.save_playlist),
                    )
                }
        },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send),
        keyboardActions = KeyboardActions(onSend = { if (newPlaylistName.isNotBlank()) onSave() }),
        modifier = modifier.fillMaxWidth(),
    )
}