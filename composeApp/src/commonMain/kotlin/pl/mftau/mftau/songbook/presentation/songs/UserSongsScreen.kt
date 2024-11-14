package pl.mftau.mftau.songbook.presentation.songs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.add_song
import mftau.composeapp.generated.resources.delete_song
import mftau.composeapp.generated.resources.empty_user_songs_list
import mftau.composeapp.generated.resources.my_songs
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import pl.mftau.mftau.common.presentation.composables.LoadingBox
import pl.mftau.mftau.common.presentation.composables.TauCenteredTopBar
import pl.mftau.mftau.songbook.domain.model.Song
import pl.mftau.mftau.songbook.presentation.songs.components.DeleteSongDialog
import pl.mftau.mftau.songbook.presentation.songs.components.SongBookEmptyListInfo
import pl.mftau.mftau.songbook.presentation.songs.components.SongCard
import pl.mftau.mftau.songbook.presentation.songs.components.SongEditorDialog

@Composable
fun UserSongScreen(
    navigateUp: () -> Unit,
    viewModel: UserSongsViewModel = koinInject(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    UserSongsScreenContent(
        navigateUp = navigateUp,
        state = state,
        toggleSongEditorVisibility = viewModel::toggleSongEditorVisibility,
        saveSong = viewModel::saveSong,
        deleteSong = viewModel::deleteSong,
    )
}

@Composable
fun UserSongsScreenContent(
    navigateUp: () -> Unit,
    state: UserSongsScreenState,
    toggleSongEditorVisibility: (Song?) -> Unit,
    saveSong: (Song) -> Unit,
    deleteSong: (Song?) -> Unit,
) {
    var songDeleteClicked by remember { mutableStateOf<Song?>(null) }

    Scaffold(
        topBar = {
            TauCenteredTopBar(
                title = stringResource(Res.string.my_songs),
                onNavClick = navigateUp,
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { toggleSongEditorVisibility(null) }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(Res.string.add_song),
                )
            }
        }
    ) { paddingValues ->
        if (state.songs != null) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
            ) {
                items(items = state.songs, key = { "${it.databaseId}:${it.title}" }) { song ->
                    SongCard(
                        song = song,
                        onClick = toggleSongEditorVisibility,
                        actions = {
                            IconButton(onClick = {
                                songDeleteClicked = song
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Delete,
                                    contentDescription = stringResource(Res.string.delete_song),
                                )
                            }
                        },
                    )
                }
            }

            if (state.songs.isEmpty())
                SongBookEmptyListInfo(messageId = Res.string.empty_user_songs_list)
        } else LoadingBox()
    }

    if (state.songEditorVisible)
        SongEditorDialog(
            song = state.songToEdit,
            onSave = saveSong,
            onDismiss = { toggleSongEditorVisibility(null) },
        )

    DeleteSongDialog(
        isVisible = songDeleteClicked != null,
        onConfirm = { deleteSong(songDeleteClicked) },
        onDismiss = { songDeleteClicked = null },
    )
}