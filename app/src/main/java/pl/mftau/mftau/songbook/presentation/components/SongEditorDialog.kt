package pl.mftau.mftau.songbook.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Preview
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pl.mftau.mftau.R
import pl.mftau.mftau.common.presentation.components.FullScreenDialog
import pl.mftau.mftau.songbook.domain.model.Song
import pl.mftau.mftau.songbook.domain.model.SongBookPreferences
import pl.mftau.mftau.songbook.domain.model.SongTopic

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SongEditorDialog(
    song: Song? = null,
    onSave: (Song) -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    var title by rememberSaveable { mutableStateOf(song?.title ?: "") }
    var titleError by rememberSaveable { mutableStateOf(false) }
    var text by rememberSaveable { mutableStateOf(song?.text ?: "") }
    var textError by rememberSaveable { mutableStateOf(false) }
    var chords by rememberSaveable { mutableStateOf(song?.chords ?: "") }
    val selectedTopics = remember {
        val currentTopics = song?.topics?.map { it.value }?.toTypedArray() ?: arrayOf(0)
        mutableStateListOf(*currentTopics)
    }

    var previewActive by remember { mutableStateOf(false) }

    val verifyInput = {
        if (title.isBlank()) titleError = true
        if (text.isBlank()) textError = true
        !(titleError || textError)
    }

    FullScreenDialog(
        isVisible = true,
        title = stringResource(id = if (song == null) R.string.add_song else R.string.edit_song),
        onSave = {
            if (verifyInput()) {
                onSave(
                    (song ?: Song()).copy(
                        title = title,
                        text = text + "\n",
                        chords = chords,
                        topics = selectedTopics.map { SongTopic.fromValue(it) }.toSet(),
                        isOriginallyInSongBook = false
                    )
                )
                onDismiss()
            }
        },
        onDismiss = onDismiss,
        action = {
            IconButton(onClick = { previewActive = !previewActive }) {
                Crossfade(targetState = previewActive, label = "preview") {
                    Icon(
                        imageVector = if (it) Icons.Default.Done else Icons.Default.Preview,
                        contentDescription = stringResource(id = R.string.show_preview)
                    )
                }
            }
        },
        content = {
            AnimatedVisibility(visible = !previewActive) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        placeholder = { Text(stringResource(id = R.string.song_title)) },
                        isError = titleError,
                        supportingText = if (titleError) {
                            {
                                Text(text = stringResource(id = R.string.empty_field_error))
                            }
                        } else null,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = stringResource(id = R.string.select_topics),
                        style = MaterialTheme.typography.titleMedium
                    )
                    FlowRow(
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        stringArrayResource(id = R.array.song_types).let { types ->
                            types.sliceArray(2..<types.size).forEachIndexed { index, type ->
                                val fixedIndex = index + 2
                                val selected = selectedTopics.contains(fixedIndex)
                                FilterChip(
                                    selected = selected,
                                    onClick = {
                                        if (selected) selectedTopics.remove(fixedIndex)
                                        else selectedTopics.add(fixedIndex)
                                    },
                                    label = { Text(text = type) },
                                    leadingIcon =
                                    if (selected) {
                                        {
                                            Icon(
                                                imageVector = Icons.Filled.Done,
                                                contentDescription = null,
                                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                                            )
                                        }
                                    } else null,
                                    modifier = Modifier.padding(horizontal = 4.dp)
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = text,
                            onValueChange = { text = it },
                            placeholder = { Text(stringResource(id = R.string.song_text)) },
                            isError = textError,
                            supportingText = if (textError) {
                                {
                                    Text(text = stringResource(id = R.string.empty_field_error))
                                }
                            } else null,
                            minLines = 4,
                            modifier = Modifier
                                .weight(1f)
                                .horizontalScroll(rememberScrollState())
                        )
                        OutlinedTextField(
                            value = chords,
                            onValueChange = { chords = it },
                            placeholder = { Text(stringResource(id = R.string.song_chords)) },
                            minLines = 4,
                            modifier = Modifier
                                .widthIn(max = 120.dp)
                                .horizontalScroll(rememberScrollState())
                        )
                    }
                }
            }

            AnimatedVisibility(visible = previewActive) {
                SongCard(
                    song = Song(title, text + "\n", chords),
                    preferences = SongBookPreferences(areChordsVisible = true)
                )
            }
        }
    )
}