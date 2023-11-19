package pl.mftau.mftau.songbook.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.PlaylistAdd
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.koin.getScreenModel
import pl.mftau.mftau.R
import pl.mftau.mftau.core.presentation.components.LoadingIndicator
import pl.mftau.mftau.songbook.presentation.components.ChangeFontSizeDialog
import pl.mftau.mftau.songbook.presentation.components.SongBookBottomAppBar
import pl.mftau.mftau.songbook.presentation.components.SongText

class SongBookListScreen : SongBookScreen() {
    @Composable
    override fun Content() {
        SongBookListScreenContent(getScreenModel())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongBookListScreenContent(screenModel: SongBookScreenModel) {
    val state by screenModel.state.collectAsStateWithLifecycle()
    var changeFontSizeDialogVisible by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            SongBookBottomAppBar(
                areChordsVisible = state.preferences.areChordsVisible,
                toggleChordsVisibility = screenModel::toggleChordsVisibility,
                showChangeFontDialog = { changeFontSizeDialogVisible = true }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            items(state.songs) { song ->
                var expanded by remember { mutableStateOf(false) }

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    ),
                    onClick = { expanded = !expanded },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = song.title,
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 8.dp)
                            )
                            IconButton(onClick = {
                                // TODO() -> SHOW DIALOG TO SELECT PLAYLIST
                            }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.PlaylistAdd,
                                    contentDescription = stringResource(id = R.string.cd_navigate_up)
                                )
                            }
                            IconButton(onClick = { screenModel.markSongAsFavourite(song) }) {
                                Crossfade(targetState = song.isFavourite, label = "") {
                                    Icon(
                                        imageVector = if (it) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                                        contentDescription = stringResource(
                                            id = if (it) R.string.remove_from_favourites else R.string.add_to_favourites
                                        )
                                    )
                                }
                            }
                        }
                        AnimatedVisibility(visible = expanded) {
                            Row(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .padding(top = 4.dp)
                            ) {
                                SongText(
                                    text = song.text,
                                    fontSize = state.preferences.fontSize,
                                    modifier = Modifier
                                        .weight(1f)
                                        .horizontalScroll(rememberScrollState())
                                )
                                if (state.preferences.areChordsVisible) {
                                    VerticalDivider(
                                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                                        modifier = Modifier.padding(horizontal = 4.dp)
                                    )
                                    SongText(
                                        text = song.chords,
                                        fontSize = state.preferences.fontSize
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        if (state.songs.isEmpty())
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                LoadingIndicator()
            }

        if (changeFontSizeDialogVisible)
            ChangeFontSizeDialog(
                currentFontSize = state.preferences.fontSize,
                onSave = screenModel::changeFontSize,
                dismiss = { changeFontSizeDialogVisible = false }
            )
    }
}