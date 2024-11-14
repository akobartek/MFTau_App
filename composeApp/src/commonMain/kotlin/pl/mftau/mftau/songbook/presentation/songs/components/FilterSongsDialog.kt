package pl.mftau.mftau.songbook.presentation.songs.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.cancel
import mftau.composeapp.generated.resources.save
import mftau.composeapp.generated.resources.song_book_filter
import mftau.composeapp.generated.resources.song_types
import org.jetbrains.compose.resources.stringArrayResource
import org.jetbrains.compose.resources.stringResource
import pl.mftau.mftau.songbook.domain.model.SongTopic
import pl.mftau.mftau.ui.theme.mfTauFont

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterSongsDialog(
    isVisible: Boolean,
    currentFilter: SongTopic,
    onSave: (SongTopic) -> Unit,
    onDismiss: () -> Unit,
) {
    if (isVisible) {
        var selectedTopic by rememberSaveable { mutableIntStateOf(currentFilter.value) }

        AlertDialog(
            icon = {
                Icon(
                    imageVector = Icons.Default.FilterList,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                )
            },
            title = {
                Text(
                    text = stringResource(Res.string.song_book_filter),
                    fontFamily = mfTauFont(),
                )
            },
            text = {
                FlowRow(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    modifier = Modifier.verticalScroll(rememberScrollState()),
                ) {
                    stringArrayResource(Res.array.song_types).forEachIndexed { index, type ->
                        val selected = selectedTopic == index
                        FilterChip(
                            selected = selected,
                            onClick = { selectedTopic = index },
                            label = {
                                Text(
                                    text = type,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.defaultMinSize(minWidth = 90.dp)
                                )
                            },
                            modifier = Modifier.padding(horizontal = 4.dp),
                        )
                    }
                }
            },
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = {
                    onSave(SongTopic.fromValue(selectedTopic))
                    onDismiss()
                }) {
                    Text(stringResource(Res.string.save))
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text(stringResource(Res.string.cancel))
                }
            },
        )
    }
}