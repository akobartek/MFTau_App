package pl.mftau.mftau.songbook.presentation.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Preview
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pl.mftau.mftau.R
import pl.mftau.mftau.core.presentation.components.FullScreenDialog
import pl.mftau.mftau.songbook.domain.model.Song
import kotlin.math.max

@Composable
fun SongEditorDialog(
    isVisible: Boolean,
    song: Song? = null,
    onSave: (Song) -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    var title by rememberSaveable { mutableStateOf(song?.title ?: "") }
    var text by rememberSaveable { mutableStateOf(song?.text ?: "") }
    var chords by rememberSaveable { mutableStateOf(song?.chords ?: "") }

    FullScreenDialog(
        isVisible = isVisible,
        title = stringResource(id = if (song == null) R.string.add_song else R.string.edit_song),
        onSave = {
            onSave(
                (song ?: Song()).copy(
                    title = title,
                    text = text,
                    chords = chords
                )
            )
        },
        onDismiss = onDismiss,
        action = {
            IconButton(onClick = {
                TODO()
            }) {
                Icon(
                    imageVector = Icons.Default.Preview,
                    contentDescription = stringResource(id = R.string.show_preview)
                )
            }
        },
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
            ) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    placeholder = { Text(stringResource(id = R.string.song_text)) }
                )
                OutlinedTextField(
                    value = chords,
                    onValueChange = { chords = it },
                    placeholder = { Text(stringResource(id = R.string.song_chords)) },
                    modifier = Modifier.heightIn(max = 120.dp)
                )
            }
        }
    )
}