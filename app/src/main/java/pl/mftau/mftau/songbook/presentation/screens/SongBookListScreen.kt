package pl.mftau.mftau.songbook.presentation.screens

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.PlaylistAdd
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import kotlinx.coroutines.launch
import pl.mftau.mftau.R
import pl.mftau.mftau.common.presentation.components.LoadingBox
import pl.mftau.mftau.songbook.presentation.components.AddToPlaylistDialog
import pl.mftau.mftau.songbook.presentation.components.ChangeFontSizeDialog
import pl.mftau.mftau.songbook.presentation.components.SongBookBottomAppBar
import pl.mftau.mftau.songbook.presentation.components.SongCard
import pl.mftau.mftau.songbook.presentation.components.SongEditorDialog
import pl.mftau.mftau.songbook.presentation.screenmodels.SongBookListScreenModel

class SongBookListScreen : SongBookScreen() {
    override val key: ScreenKey
        get() = KEY

    @Composable
    override fun Content() {
        SongBookListScreenContent(getScreenModel())
    }

    companion object {
        const val KEY = "SongBookListScreen"
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SongBookListScreenContent(screenModel: SongBookListScreenModel) {
    val state by screenModel.state.collectAsStateWithLifecycle()
    var changeFontSizeDialogVisible by remember { mutableStateOf(false) }
    var fabOffset by remember { mutableStateOf(Offset.Zero) }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(key1 = state) {
        scope.launch {
            if (state.songSavedInfoVisible) {
                screenModel.toggleSongSavedInfoVisibility()
                snackbarHostState.showSnackbar(
                    message = context.getString(R.string.song_saved),
                    withDismissAction = true
                )
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        bottomBar = {
            SongBookBottomAppBar(
                areChordsVisible = state.preferences.areChordsVisible,
                toggleChordsVisibility = screenModel::toggleChordsVisibility,
                showChangeFontDialog = { changeFontSizeDialogVisible = true },
                onFabClicked = screenModel::toggleSongEditorVisibility,
                onPositioned = { fabOffset = it }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            stickyHeader {
                Box(Modifier.fillMaxWidth()) {
                    Text(text = "xDDDDDDDDDDDD")
                }
            }

            items(state.songs, key = { it.title }) { song ->
                SongCard(
                    song = song,
                    preferences = state.preferences,
                    actions = {
                        IconButton(onClick = { screenModel.togglePlaylistDialogVisibility(song) }) {
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
                )
            }
        }

        if (state.songs.isEmpty()) LoadingBox()

        if (changeFontSizeDialogVisible)
            ChangeFontSizeDialog(
                currentFontSize = state.preferences.fontSize,
                onSave = screenModel::changeFontSize,
                dismiss = { changeFontSizeDialogVisible = false }
            )

        if (state.songSelectedToPlaylists != null)
            AddToPlaylistDialog(
                song = state.songSelectedToPlaylists!!,
                playlists = state.playlists,
                addNewPlaylist = screenModel::addNewPlaylist,
                saveSongInPlaylists = screenModel::saveSongInPlaylists,
                dismiss = { screenModel.togglePlaylistDialogVisibility(null) },
            )
    }

    if (state.songEditorVisible)
        SongEditorDialog(
            song = null,
            onSave = screenModel::saveSong,
            onDismiss = screenModel::toggleSongEditorVisibility
        )
}