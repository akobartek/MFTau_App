package pl.mftau.mftau.songbook.presentation.songs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.launch
import pl.mftau.mftau.R
import pl.mftau.mftau.common.presentation.composables.LoadingBox
import pl.mftau.mftau.common.presentation.composables.TauCenteredTopBar
import pl.mftau.mftau.common.utils.safePop
import pl.mftau.mftau.songbook.domain.model.Song
import pl.mftau.mftau.songbook.presentation.songs.components.DeleteSongDialog
import pl.mftau.mftau.songbook.presentation.songs.components.SongBookEmptyListInfo
import pl.mftau.mftau.songbook.presentation.songs.components.SongCard
import pl.mftau.mftau.songbook.presentation.songs.components.SongEditorDialog

class AddedSongsListScreen : SongBookScreen() {
    override val key: ScreenKey
        get() = KEY

    @Composable
    override fun Content() {
        AddedSongsListScreenContent(getScreenModel())
    }

    companion object {
        const val KEY = "AddedSongsListScreen"
    }
}

@Composable
fun AddedSongsListScreenContent(screenModel: UserSongsListScreenModel) {
    val navigator = LocalNavigator.currentOrThrow
    val state by screenModel.state.collectAsStateWithLifecycle()
    var songDeleteClicked by remember { mutableStateOf<Song?>(null) }

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
        topBar = {
            TauCenteredTopBar(
                title = stringResource(R.string.my_songs),
                onNavClick = { navigator.safePop(AddedSongsListScreen.KEY) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = screenModel::toggleSongEditorVisibility) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add_song)
                )
            }
        }
    ) { paddingValues ->
        if (state.songs != null) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                items(state.songs ?: listOf(), key = { "${it.databaseId}:${it.title}" }) { song ->
                    SongCard(
                        song = song,
                        onClick = { screenModel.toggleSongEditorVisibility(it) },
                        actions = {
                            IconButton(onClick = {
                                songDeleteClicked = song
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Delete,
                                    contentDescription = stringResource(id = R.string.delete_song)
                                )
                            }
                        }
                    )
                }
            }

            if (state.songs?.isEmpty() == true)
                SongBookEmptyListInfo(messageId = R.string.empty_user_songs_list)
        } else LoadingBox()
    }

    if (state.songEditorVisible)
        SongEditorDialog(
            song = state.songToEdit,
            onSave = screenModel::saveSong,
            onDismiss = screenModel::toggleSongEditorVisibility
        )

    DeleteSongDialog(
        isVisible = songDeleteClicked != null,
        onConfirm = { screenModel.deleteSong(songDeleteClicked) },
        onDismiss = { songDeleteClicked = null }
    )
}