package pl.mftau.mftau.songbook.presentation.screens

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.PlaylistAdd
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import kotlinx.coroutines.launch
import pl.mftau.mftau.R
import pl.mftau.mftau.common.presentation.components.ListScrollbar
import pl.mftau.mftau.common.presentation.components.LoadingBox
import pl.mftau.mftau.songbook.presentation.components.AddToPlaylistDialog
import pl.mftau.mftau.songbook.presentation.components.ChangeFontSizeDialog
import pl.mftau.mftau.songbook.presentation.components.SongBookBottomAppBar
import pl.mftau.mftau.songbook.presentation.components.SongBookEmptyListInfo
import pl.mftau.mftau.songbook.presentation.components.SongBookSearchBar
import pl.mftau.mftau.songbook.presentation.components.SongCard
import pl.mftau.mftau.songbook.presentation.components.SongEditorDialog
import pl.mftau.mftau.songbook.presentation.screenmodels.SongBookListScreenModel
import pl.mftau.mftau.ui.WindowInfo
import pl.mftau.mftau.ui.rememberWindowInfo
import kotlin.math.roundToInt

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
    val windowInfo = rememberWindowInfo()
    val state by screenModel.state.collectAsStateWithLifecycle()
    val searchBarState by screenModel.searchBarState.collectAsStateWithLifecycle()
    val searchBarHeightDp =
        if (windowInfo.screenHeightInfo is WindowInfo.WindowType.Compact) 0.dp
        else 56.dp + 12.dp
    val searchBarHeightPx = with(LocalDensity.current) { searchBarHeightDp.roundToPx().toFloat() }
    var changeFontSizeDialogVisible by remember { mutableStateOf(false) }

    val lazyListState = rememberLazyListState()
    var searchBarOffsetHeightPx by remember { mutableStateOf(0f) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = searchBarOffsetHeightPx + delta
                searchBarOffsetHeightPx = newOffset.coerceIn(-searchBarHeightPx, 0f)

                return Offset.Zero
            }
        }
    }

    var songIndexToAnimate by remember { mutableIntStateOf(0) }
    LaunchedEffect(songIndexToAnimate) {
        lazyListState.animateScrollToItem(songIndexToAnimate)
    }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(state.songSavedInfoVisible) {
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
                onFabClicked = screenModel::toggleSongEditorVisibility
            )
        },
        modifier = Modifier.nestedScroll(nestedScrollConnection)
    ) { paddingValues ->
        Row(modifier = Modifier.padding(paddingValues)) {
            LazyColumn(
                state = lazyListState,
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.weight(1f)
            ) {
                if (windowInfo.screenHeightInfo !is WindowInfo.WindowType.Compact)
                    stickyHeader {
                        SongBookSearchBar(
                            query = searchBarState.searchQuery,
                            onQueryChanged = screenModel::onSearchQueryChange,
                            filter = searchBarState.selectedFilter,
                            onFilterChanged = screenModel::onSearchFilterChange,
                            modifier = Modifier
                                .offset {
                                    IntOffset(x = 0, y = searchBarOffsetHeightPx.roundToInt())
                                }
                        )
                    }

                if (state.isLoading)
                    item { Column(Modifier.fillParentMaxHeight()) { LoadingBox() } }
                else if (state.songs.isNotEmpty()) {
                    items(state.songs, key = { it.title }) { song ->
                        SongCard(
                            song = song,
                            preferences = state.preferences,
                            actions = {
                                IconButton(onClick = {
                                    screenModel.togglePlaylistDialogVisibility(
                                        song
                                    )
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
                            },
                            modifier = Modifier.fillParentMaxWidth()
                        )
                    }
                } else item {
                    Column(Modifier.fillParentMaxHeight()) {
                        SongBookEmptyListInfo(messageId = R.string.empty_search_list)
                    }
                }
            }

            ListScrollbar(
                isVisible = !state.isLoading && state.songs.isNotEmpty(),
                headerHeight = searchBarHeightDp,
                listState = lazyListState,
                listSize = state.songs.size,
                onDrag = { songIndexToAnimate = it }
            )
        }

        // TODO() -> It crashes sometimes, check this behaviour on newer compose versions
//        LaunchedEffect(state.songs) {
//            lazyListState.animateScrollToItem(0)
//        }

        ChangeFontSizeDialog(
            isVisible = changeFontSizeDialogVisible,
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