package pl.mftau.mftau.songbook.presentation.screens

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import pl.mftau.mftau.R
import pl.mftau.mftau.core.presentation.components.LoadingBox
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

@Composable
fun SongBookListScreenContent(screenModel: SongBookListScreenModel) {
    val state by screenModel.state.collectAsStateWithLifecycle()
    var changeFontSizeDialogVisible by remember { mutableStateOf(false) }
    var addSongDialogVisible by remember { mutableStateOf(false) }
    var fabOffset by remember { mutableStateOf(Offset.Zero) }

    Scaffold(
        bottomBar = {
            SongBookBottomAppBar(
                areChordsVisible = state.preferences.areChordsVisible,
                toggleChordsVisibility = screenModel::toggleChordsVisibility,
                showChangeFontDialog = { changeFontSizeDialogVisible = true },
                onFabClicked = { addSongDialogVisible = true },
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

    SongEditorDialog(
        isVisible = addSongDialogVisible,
        song = null,
        onSave = {},
        onDismiss = { addSongDialogVisible = false }
    )
}