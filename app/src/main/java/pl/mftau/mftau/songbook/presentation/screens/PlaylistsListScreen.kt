package pl.mftau.mftau.songbook.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import pl.mftau.mftau.R
import pl.mftau.mftau.core.presentation.components.LoadingBox
import pl.mftau.mftau.core.presentation.components.TauCenteredTopBar
import pl.mftau.mftau.core.utils.safePop
import pl.mftau.mftau.core.utils.safePush
import pl.mftau.mftau.songbook.presentation.components.CreatePlaylistRow
import pl.mftau.mftau.songbook.presentation.components.ImportPlaylistDialog
import pl.mftau.mftau.songbook.presentation.components.SongBookEmptyListInfo
import pl.mftau.mftau.songbook.presentation.screenmodels.PlaylistsListScreenModel

class PlaylistsListScreen : SongBookScreen() {
    override val key: ScreenKey
        get() = KEY

    @Composable
    override fun Content() {
        PlaylistsListScreenContent(getScreenModel())
    }

    companion object {
        const val KEY = "PlaylistsListScreen"
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlaylistsListScreenContent(screenModel: PlaylistsListScreenModel) {
    val navigator = LocalNavigator.currentOrThrow
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    val state by screenModel.state.collectAsStateWithLifecycle()

    var newPlaylistName by remember { mutableStateOf("") }
    var importPlaylistDialogVisible by remember { mutableStateOf(false) }

    var isLoading by remember { mutableStateOf(false) }
    val listModifier = if (isLoading) Modifier.blur(16.dp) else Modifier

    LaunchedEffect(key1 = state) {
        isLoading = false
        newPlaylistName = ""
    }

    Scaffold(
        topBar = {
            TauCenteredTopBar(
                title = stringResource(R.string.my_playlists),
                onNavClick = { navigator.safePop(PlaylistsListScreen.KEY) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { importPlaylistDialogVisible = true }) {
                Icon(
                    imageVector = Icons.Default.Download,
                    contentDescription = stringResource(id = R.string.import_playlist)
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
            items(state.playlists) { (playlist, songsCount) ->
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    ),
                    onClick = { navigator.safePush(PlaylistDetailsScreen(playlistId = playlist.id)) }
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .animateItemPlacement(),
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = pluralStringResource(
                                    id = R.plurals.playlist_song_count,
                                    count = songsCount,
                                    songsCount
                                ),
                                style = MaterialTheme.typography.bodySmall
                            )
                            Text(
                                text = playlist.createdAt.date.toString(),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        Row {
                            Text(
                                text = playlist.name,
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.weight(1f)
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
                        .fillMaxWidth()
                ) {
                    CreatePlaylistRow(
                        newPlaylistName = newPlaylistName,
                        onChangeName = { newPlaylistName = it },
                        onSave = {
                            isLoading = true
                            screenModel.addNewPlaylist(newPlaylistName)
                        },
                        modifier = Modifier.fillMaxWidth(0.85f)
                    )
                    if (isLoading)
                        LoadingBox()
                }
            }
        }

        if (state.playlists.isEmpty())
            SongBookEmptyListInfo(messageId = R.string.empty_playlists_list)

        if (importPlaylistDialogVisible)
            ImportPlaylistDialog(
                onImport = { code -> navigator.safePush(PlaylistDetailsScreen(importCode = code)) },
                onDismiss = { importPlaylistDialogVisible = false }
            )
    }
}