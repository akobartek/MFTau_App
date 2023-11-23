package pl.mftau.mftau.songbook.presentation.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.MusicOff
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import pl.mftau.mftau.R
import pl.mftau.mftau.core.presentation.components.LoadingBox
import pl.mftau.mftau.core.presentation.components.TauNormalTopBar
import pl.mftau.mftau.core.presentation.components.UnsavedChangesDialog
import pl.mftau.mftau.core.utils.safePop
import pl.mftau.mftau.songbook.presentation.components.DeletePlaylistDialog
import pl.mftau.mftau.songbook.presentation.components.ImportPlaylistErrorDialog
import pl.mftau.mftau.songbook.presentation.components.ShareCodeDialog
import pl.mftau.mftau.songbook.presentation.components.SharePlaylistErrorDialog
import pl.mftau.mftau.songbook.presentation.components.SongBookEmptyListInfo
import pl.mftau.mftau.songbook.presentation.components.SongCard
import pl.mftau.mftau.songbook.presentation.screenmodels.PlaylistDetailsScreenModel

class PlaylistDetailsScreen(
    private val playlistId: Long? = null,
    private val importCode: String? = null
) : SongBookScreen() {
    override val key: ScreenKey
        get() = KEY

    @Composable
    override fun Content() {
        PlaylistDetailsScreenContent(
            getScreenModel<PlaylistDetailsScreenModel>().also { it.init(playlistId, importCode) }
        )
    }

    companion object {
        const val KEY = "PlaylistDetailsScreen"
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlaylistDetailsScreenContent(screenModel: PlaylistDetailsScreenModel) {
    val navigator = LocalNavigator.currentOrThrow
    val context = LocalContext.current
    val state by screenModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    var deletePlaylistDialogVisible by remember { mutableStateOf(false) }
    var unsavedChangesDialogVisible by remember { mutableStateOf(false) }
    val onBackPressed = {
        if (state.editMode) unsavedChangesDialogVisible = true
        else navigator.safePop(PlaylistDetailsScreen.KEY)
    }


    BackHandler { onBackPressed() }

    LaunchedEffect(key1 = state.deletedSong) {
        if (state.deletedSong != null) {
            val result = snackbarHostState.showSnackbar(
                message = context.getString(R.string.song_removed),
                actionLabel = context.getString(R.string.undo),
                duration = SnackbarDuration.Long
            )
            when (result) {
                SnackbarResult.Dismissed -> screenModel.clearDeletedSong()
                SnackbarResult.ActionPerformed -> screenModel.undeleteSong()
            }
        } else snackbarHostState.currentSnackbarData?.dismiss()
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TauNormalTopBar(
                title =
                if (state.isImported) stringResource(id = R.string.imported_playlist)
                else state.playlist?.name ?: "",
                onNavClick = { onBackPressed() },
                navIcon = Icons.Default.Close,
                actions = {
                    AnimatedVisibility(visible = state.editMode) {
                        Row {
                            IconButton(onClick = screenModel::savePlaylist) {
                                Icon(
                                    imageVector = Icons.Default.Save,
                                    contentDescription = stringResource(id = R.string.save_playlist)
                                )
                            }
                            IconButton(onClick = { deletePlaylistDialogVisible = true }) {
                                Icon(
                                    imageVector = Icons.Filled.Delete,
                                    contentDescription = stringResource(id = R.string.delete_playlist)
                                )
                            }
                        }
                    }
                    AnimatedVisibility(visible = !state.editMode) {
                        Row {
                            if (!state.isImported && state.playlist?.songs?.isNotEmpty() == true)
                                IconButton(onClick = screenModel::toggleEditMode) {
                                    Icon(
                                        imageVector = Icons.Filled.Edit,
                                        contentDescription = stringResource(id = R.string.edit_playlist)
                                    )
                                }
                            IconButton(onClick = screenModel::toggleChordsVisibility) {
                                Icon(
                                    imageVector =
                                    if (state.preferences.areChordsVisible) Icons.Filled.MusicNote
                                    else Icons.Filled.MusicOff,
                                    contentDescription =
                                    if (state.preferences.areChordsVisible) stringResource(id = R.string.hide_chords)
                                    else stringResource(id = R.string.show_chords),
                                )
                            }
                            IconButton(onClick = screenModel::generateShareCode) {
                                Icon(
                                    imageVector = Icons.Filled.Share,
                                    contentDescription = "Options"
                                )
                            }
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            if (state.editMode) {
                itemsIndexed(
                    items = state.tempSongsList,
                    key = { _, song -> song.title })
                { index, song ->
                    SongCard(
                        song = song,
                        preferences = state.preferences,
                        actions = {
                            if (index > 0)
                                IconButton(onClick = {
                                    screenModel.swapItems(index, index - 1)
                                }) {
                                    Icon(
                                        imageVector = Icons.Filled.ArrowUpward,
                                        contentDescription = stringResource(id = R.string.move_song_up)
                                    )
                                }
                            if (index < state.tempSongsList.size - 1)
                                IconButton(onClick = {
                                    screenModel.swapItems(index, index + 1)
                                }) {
                                    Icon(
                                        imageVector = Icons.Filled.ArrowDownward,
                                        contentDescription = stringResource(id = R.string.move_song_down)
                                    )
                                }
                            IconButton(onClick = { screenModel.deleteSong(song) }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = stringResource(id = R.string.remove_from_playlist)
                                )
                            }
                        },
                        modifier = Modifier.animateItemPlacement()
                    )
                }
            } else {
                items(
                    items = state.playlist?.songs ?: listOf(),
                    key = { song -> song.title }
                ) { song ->
                    SongCard(
                        song = song,
                        preferences = state.preferences,
                        actions = {
                            IconButton(onClick = { screenModel.deleteSong(song) }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = stringResource(id = R.string.remove_from_playlist)
                                )
                            }
                        },
                        modifier = Modifier.animateItemPlacement()
                    )
                }
            }
        }

        if (state.playlist == null)
            LoadingBox()
        else if (state.playlist?.songs?.isEmpty() != false)
            SongBookEmptyListInfo(messageId = R.string.empty_playlist)

        DeletePlaylistDialog(
            isVisible = deletePlaylistDialogVisible,
            onConfirm = {
                screenModel.deletePlaylist()
                navigator.safePop(PlaylistDetailsScreen.KEY)
            },
            onDismiss = { deletePlaylistDialogVisible = false }
        )

        UnsavedChangesDialog(
            isVisible = unsavedChangesDialogVisible,
            onDiscard = navigator::pop,
            onDismiss = { unsavedChangesDialogVisible = false }
        )

        ShareCodeDialog(
            isVisible = state.shareCodeDialogVisible,
            code = state.shareCode,
            onDismiss = screenModel::toggleShareCodeDialogVisibility
        )

        ImportPlaylistErrorDialog(
            isVisible = state.importError,
            onConfirm = {
                screenModel.toggleImportErrorFlag()
                navigator.safePop(PlaylistDetailsScreen.KEY)
            }
        )

        SharePlaylistErrorDialog(
            isVisible = state.shareError,
            onConfirm = screenModel::toggleShareErrorFlag
        )
    }
}