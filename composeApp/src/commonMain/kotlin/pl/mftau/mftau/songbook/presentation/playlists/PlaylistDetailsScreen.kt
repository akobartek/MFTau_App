package pl.mftau.mftau.songbook.presentation.playlists

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.delete_playlist
import mftau.composeapp.generated.resources.edit_playlist
import mftau.composeapp.generated.resources.empty_playlist
import mftau.composeapp.generated.resources.hide_chords
import mftau.composeapp.generated.resources.imported_playlist
import mftau.composeapp.generated.resources.save_playlist
import mftau.composeapp.generated.resources.show_chords
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import pl.mftau.mftau.common.presentation.composables.LoadingBox
import pl.mftau.mftau.common.presentation.composables.TauNormalTopBar
import pl.mftau.mftau.common.presentation.composables.UnsavedChangesDialog
import pl.mftau.mftau.common.utils.BackHandler
import pl.mftau.mftau.songbook.domain.model.Song
import pl.mftau.mftau.songbook.presentation.playlists.composables.DeletePlaylistDialog
import pl.mftau.mftau.songbook.presentation.playlists.composables.ImportPlaylistErrorDialog
import pl.mftau.mftau.songbook.presentation.playlists.composables.PlaylistDetailsEditList
import pl.mftau.mftau.songbook.presentation.playlists.composables.PlaylistDetailsNormalList
import pl.mftau.mftau.songbook.presentation.playlists.composables.ShareCodeDialog
import pl.mftau.mftau.songbook.presentation.playlists.composables.SharePlaylistErrorDialog
import pl.mftau.mftau.songbook.presentation.songs.components.SongBookEmptyListInfo

@Composable
fun PlaylistDetailsScreen(
    navigateUp: () -> Unit,
    playlistId: Long?,
    importCode: String?,
    viewModel: PlaylistDetailsViewModel = koinInject(),
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.init(playlistId, importCode)
    }

    PlaylistDetailsScreenContent(
        navigateUp = navigateUp,
        state = state.value,
        savePlaylist = viewModel::savePlaylist,
        toggleEditMode = viewModel::toggleEditMode,
        toggleChordsVisibility = viewModel::toggleChordsVisibility,
        generateShareCode = viewModel::generateShareCode,
        swapItems = viewModel::swapItems,
        deleteSong = viewModel::deleteSong,
        deletePlaylist = viewModel::deletePlaylist,
        toggleShareCodeDialogVisibility = viewModel::toggleShareCodeDialogVisibility,
        toggleImportErrorFlag = viewModel::toggleImportErrorFlag,
        toggleShareErrorFlag = viewModel::toggleShareErrorFlag,
    )
}

@Composable
fun PlaylistDetailsScreenContent(
    navigateUp: () -> Unit,
    state: PlaylistDetailsScreenState,
    savePlaylist: () -> Unit,
    toggleEditMode: () -> Unit,
    toggleChordsVisibility: () -> Unit,
    generateShareCode: () -> Unit,
    swapItems: (Int, Int) -> Unit,
    deleteSong: (Song) -> Unit,
    deletePlaylist: () -> Unit,
    toggleShareCodeDialogVisibility: () -> Unit,
    toggleImportErrorFlag: () -> Unit,
    toggleShareErrorFlag: () -> Unit,
) {
    var deletePlaylistDialogVisible by remember { mutableStateOf(false) }
    var unsavedChangesDialogVisible by rememberSaveable { mutableStateOf(false) }

    val onBackPressed = {
        if (state.editMode) unsavedChangesDialogVisible = true
        else navigateUp()
    }
    BackHandler { onBackPressed() }

    Scaffold(
        topBar = {
            TauNormalTopBar(
                title =
                if (state.isImported) stringResource(Res.string.imported_playlist)
                else state.playlist?.name ?: "",
                onNavClick = { onBackPressed() },
                navIcon = Icons.Default.Close,
                actions = {
                    AnimatedVisibility(visible = state.editMode) {
                        Row {
                            IconButton(onClick = savePlaylist) {
                                Icon(
                                    imageVector = Icons.Default.Save,
                                    contentDescription = stringResource(Res.string.save_playlist),
                                )
                            }
                            IconButton(onClick = { deletePlaylistDialogVisible = true }) {
                                Icon(
                                    imageVector = Icons.Filled.Delete,
                                    contentDescription = stringResource(Res.string.delete_playlist),
                                )
                            }
                        }
                    }
                    AnimatedVisibility(visible = !state.editMode) {
                        Row {
                            if (!state.isImported && state.playlist?.songs?.isNotEmpty() == true)
                                IconButton(onClick = toggleEditMode) {
                                    Icon(
                                        imageVector = Icons.Filled.Edit,
                                        contentDescription = stringResource(Res.string.edit_playlist),
                                    )
                                }
                            IconButton(onClick = toggleChordsVisibility) {
                                Icon(
                                    imageVector =
                                    if (state.preferences.areChordsVisible) Icons.Filled.MusicNote
                                    else Icons.Filled.MusicOff,
                                    contentDescription =
                                    if (state.preferences.areChordsVisible) stringResource(Res.string.hide_chords)
                                    else stringResource(Res.string.show_chords),
                                )
                            }
                            IconButton(onClick = generateShareCode) {
                                Icon(
                                    imageVector = Icons.Filled.Share,
                                    contentDescription = null,
                                )
                            }
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Crossfade(targetState = state.editMode, label = "") {
            if (state.editMode.not())
                PlaylistDetailsNormalList(
                    songs = state.playlist?.songs ?: emptyList(),
                    preferences = state.preferences,
                    modifier = Modifier.padding(paddingValues),
                )
            else
                PlaylistDetailsEditList(
                    songs = state.tempSongsList,
                    preferences = state.preferences,
                    swapSongs = swapItems,
                    deleteSong = deleteSong,
                    modifier = Modifier.padding(paddingValues),
                )
        }

        if (state.playlist == null)
            LoadingBox()
        else if (state.playlist.songs.isEmpty())
            SongBookEmptyListInfo(messageId = Res.string.empty_playlist)

        DeletePlaylistDialog(
            isVisible = deletePlaylistDialogVisible,
            onConfirm = {
                deletePlaylist()
                navigateUp()
            },
            onDismiss = { deletePlaylistDialogVisible = false },
        )

        UnsavedChangesDialog(
            isVisible = unsavedChangesDialogVisible,
            onDiscard = navigateUp,
            onDismiss = { unsavedChangesDialogVisible = false },
        )

        ShareCodeDialog(
            isVisible = state.shareCodeDialogVisible,
            code = state.shareCode,
            onDismiss = toggleShareCodeDialogVisibility,
        )

        ImportPlaylistErrorDialog(
            isVisible = state.importError,
            onConfirm = {
                toggleImportErrorFlag()
                navigateUp()
            },
        )

        SharePlaylistErrorDialog(
            isVisible = state.shareError,
            onConfirm = toggleShareErrorFlag,
        )
    }
}