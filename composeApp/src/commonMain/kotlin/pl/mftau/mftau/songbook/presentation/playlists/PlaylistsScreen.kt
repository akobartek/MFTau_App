package pl.mftau.mftau.songbook.presentation.playlists

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.empty_playlists_list
import mftau.composeapp.generated.resources.import_playlist
import mftau.composeapp.generated.resources.my_playlists
import mftau.composeapp.generated.resources.playlist_song_count
import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import pl.mftau.mftau.common.presentation.composables.LoadingBox
import pl.mftau.mftau.common.presentation.composables.TauCenteredTopBar
import pl.mftau.mftau.songbook.presentation.playlists.composables.CreatePlaylistRow
import pl.mftau.mftau.songbook.presentation.playlists.composables.ImportPlaylistDialog
import pl.mftau.mftau.songbook.presentation.songs.components.SongBookEmptyListInfo

@Composable
fun PlaylistsScreen(
    navigateUp: () -> Unit,
    openDetails: (Long?, String?) -> Unit,
    viewModel: PlaylistsViewModel = koinInject(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    PlaylistsScreenContent(
        navigateUp = navigateUp,
        state = state,
        openDetails = openDetails,
        addPlaylist = viewModel::addNewPlaylist,
    )
}

@Composable
fun PlaylistsScreenContent(
    navigateUp: () -> Unit,
    openDetails: (Long?, String?) -> Unit,
    state: PlaylistsScreenState,
    addPlaylist: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    var newPlaylistName by rememberSaveable { mutableStateOf("") }
    var importPlaylistDialogVisible by rememberSaveable { mutableStateOf(false) }

    var isLoading by rememberSaveable { mutableStateOf(false) }
    val listModifier = if (isLoading) Modifier.blur(16.dp) else Modifier

    LaunchedEffect(key1 = state) {
        isLoading = false
        newPlaylistName = ""
    }

    Scaffold(
        topBar = {
            TauCenteredTopBar(
                title = stringResource(Res.string.my_playlists),
                onNavClick = navigateUp,
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { importPlaylistDialogVisible = true }) {
                Icon(
                    imageVector = Icons.Default.Download,
                    contentDescription = stringResource(Res.string.import_playlist),
                )
            }
        },
        modifier = Modifier.clickable(
            interactionSource = interactionSource,
            indication = null
        ) { focusManager.clearFocus(true) }
    ) { paddingValues ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = listModifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            items(items = state.playlists, key = { it.first.id }) { (playlist, songsCount) ->
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    ),
                    onClick = { openDetails(playlist.id, null) },
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .animateItem(),
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = pluralStringResource(
                                    Res.plurals.playlist_song_count,
                                    songsCount,
                                    songsCount,
                                ),
                                style = MaterialTheme.typography.bodySmall,
                            )
                            Text(
                                text = playlist.createdAt.date.toString(),
                                style = MaterialTheme.typography.bodySmall,
                            )
                        }
                        Row {
                            Text(
                                text = playlist.name,
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.weight(1f),
                            )
                        }
                    }
                }
            }
            item {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(),
                ) {
                    CreatePlaylistRow(
                        newPlaylistName = newPlaylistName,
                        onChangeName = { newPlaylistName = it },
                        onSave = {
                            isLoading = true
                            addPlaylist(newPlaylistName.trim())
                        },
                        modifier = Modifier.fillMaxWidth(0.85f),
                    )
                    if (isLoading)
                        LoadingBox()
                }
            }
            if (state.playlists.isEmpty())
                item {
                    SongBookEmptyListInfo(messageId = Res.string.empty_playlists_list)
                }
        }

        if (importPlaylistDialogVisible)
            ImportPlaylistDialog(
                onImport = { code -> openDetails(null, code) },
                onDismiss = { importPlaylistDialogVisible = false }
            )
    }
}