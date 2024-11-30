package pl.mftau.mftau.songbook.presentation.songs

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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.PlaylistAdd
import androidx.compose.material.icons.automirrored.filled.QueueMusic
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.MusicOff
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.add_to_favourites
import mftau.composeapp.generated.resources.cd_navigate_up
import mftau.composeapp.generated.resources.change_font_size
import mftau.composeapp.generated.resources.empty_search_list
import mftau.composeapp.generated.resources.hide_chords
import mftau.composeapp.generated.resources.open_pdf
import mftau.composeapp.generated.resources.remove_from_favourites
import mftau.composeapp.generated.resources.show_added_songs
import mftau.composeapp.generated.resources.show_chords
import mftau.composeapp.generated.resources.show_playlists
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import pl.mftau.mftau.Screen
import pl.mftau.mftau.common.presentation.composables.ListScrollbar
import pl.mftau.mftau.common.presentation.composables.LoadingBox
import pl.mftau.mftau.common.presentation.composables.NoPdfAppDialog
import pl.mftau.mftau.common.utils.getScreenHeight
import pl.mftau.mftau.common.utils.getScreenWidth
import pl.mftau.mftau.songbook.domain.model.Playlist
import pl.mftau.mftau.songbook.domain.model.Song
import pl.mftau.mftau.songbook.domain.model.SongTopic
import pl.mftau.mftau.songbook.presentation.playlists.composables.AddToPlaylistDialog
import pl.mftau.mftau.songbook.presentation.songs.components.ChangeFontSizeDialog
import pl.mftau.mftau.songbook.presentation.songs.components.SongBookBottomAppBar
import pl.mftau.mftau.songbook.presentation.songs.components.SongBookEmptyListInfo
import pl.mftau.mftau.songbook.presentation.songs.components.SongBookNavRail
import pl.mftau.mftau.songbook.presentation.songs.components.SongBookSearchBar
import pl.mftau.mftau.songbook.presentation.songs.components.SongCard
import pl.mftau.mftau.songbook.presentation.songs.components.SongEditorDialog
import kotlin.math.roundToInt

@Composable
fun SongBookScreen(
    navigateUp: () -> Unit,
    navigate: (Screen) -> Unit,
    viewModel: SongBookViewModel = koinInject(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val searchBarState by viewModel.searchBarState.collectAsStateWithLifecycle()

    SongBookScreenContent(
        navigateUp = navigateUp,
        navigate = navigate,
        state = state,
        searchBarState = searchBarState,
        toggleChordsVisibility = viewModel::toggleChordsVisibility,
        onSearchQueryChange = viewModel::onSearchQueryChange,
        onSearchFilterChange = viewModel::onSearchFilterChange,
        addNewPlaylist = viewModel::addNewPlaylist,
        markSongAsFavourite = viewModel::markSongAsFavourite,
        saveSongInPlaylists = viewModel::saveSongInPlaylists,
        togglePlaylistDialogVisibility = viewModel::togglePlaylistDialogVisibility,
        changeFontSize = viewModel::changeFontSize,
        saveSong = viewModel::saveSong,
        toggleSongEditorVisibility = viewModel::toggleSongEditorVisibility,
    )
}

data class SongBookAction(
    val icon: ImageVector,
    val description: String,
    val onClick: () -> Unit
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SongBookScreenContent(
    navigateUp: () -> Unit,
    navigate: (Screen) -> Unit,
    state: SongBookScreenState,
    searchBarState: SongBookSearchBarState,
    toggleChordsVisibility: () -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onSearchFilterChange: (SongTopic) -> Unit,
    addNewPlaylist: (String) -> Unit,
    markSongAsFavourite: (Song) -> Unit,
    saveSongInPlaylists: (List<Playlist>) -> Unit,
    togglePlaylistDialogVisibility: (Song?) -> Unit,
    changeFontSize: (Int) -> Unit,
    saveSong: (Song) -> Unit,
    toggleSongEditorVisibility: () -> Unit,
) {
//    val windowInfo = currentWindowAdaptiveInfo()  TODO() -> Use this value in the future instead of size - currently it's not yet available
    val width = getScreenWidth()
    println("Songbook height: ${getScreenHeight()}")
    println("Songbook width: ${getScreenWidth()}")

    val searchBarHeightDp = 56.dp + 12.dp
    val searchBarHeightPx = with(LocalDensity.current) { searchBarHeightDp.roundToPx().toFloat() }
    var changeFontSizeDialogVisible by rememberSaveable { mutableStateOf(false) }
    var firstVisibleItemIndex by rememberSaveable { mutableIntStateOf(0) }

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

    DisposableEffect(Unit) {
        onDispose {
            firstVisibleItemIndex = lazyListState.firstVisibleItemIndex
        }
    }

    var songIndexToAnimate by remember { mutableIntStateOf(firstVisibleItemIndex) }
    LaunchedEffect(songIndexToAnimate) {
        lazyListState.animateScrollToItem(songIndexToAnimate)
    }

    var pdfDialogVisible by rememberSaveable { mutableStateOf(false) }
    val actions = listOf(
        SongBookAction(
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            description = stringResource(Res.string.cd_navigate_up),
            onClick = navigateUp,
        ),
        SongBookAction(
            icon = Icons.Filled.PictureAsPdf,
            description = stringResource(Res.string.open_pdf),
            onClick = {
//                if (!context.openPdf("spiewnik.pdf")) pdfDialogVisible = true
            },
        ),
        SongBookAction(
            icon = Icons.Filled.PostAdd,
            description = stringResource(Res.string.show_added_songs),
            onClick = { navigate(Screen.UserSongs) },
        ),
        SongBookAction(
            icon = Icons.AutoMirrored.Filled.QueueMusic,
            description = stringResource(Res.string.show_playlists),
            onClick = { navigate(Screen.Playlists) },
        ),
        state.preferences.areChordsVisible.let { areChordsVisible ->
            SongBookAction(
                icon = if (areChordsVisible) Icons.Filled.MusicNote else Icons.Filled.MusicOff,
                description = stringResource(
                    if (areChordsVisible) Res.string.hide_chords else Res.string.show_chords
                ),
                onClick = toggleChordsVisibility,
            )
        },
        SongBookAction(
            icon = Icons.Filled.FormatSize,
            description = stringResource(Res.string.change_font_size),
            onClick = { changeFontSizeDialogVisible = true },
        ),
    )

    Scaffold(
        bottomBar = {
            if (width < 600.dp)
                SongBookBottomAppBar(
                    actions = actions,
                    onFabClicked = toggleSongEditorVisibility,
                )
        },
        modifier = Modifier.nestedScroll(nestedScrollConnection),
    ) { paddingValues ->
        Row(modifier = Modifier.padding(paddingValues)) {
            if (width >= 600.dp)
                SongBookNavRail(actions = actions)
            LazyColumn(
                state = lazyListState,
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.weight(1f),
            ) {
                stickyHeader {
                    SongBookSearchBar(
                        query = searchBarState.searchQuery,
                        onQueryChanged = onSearchQueryChange,
                        filter = searchBarState.selectedFilter,
                        onFilterChanged = onSearchFilterChange,
                        modifier = Modifier
                            .offset { IntOffset(x = 0, y = searchBarOffsetHeightPx.roundToInt()) },
                    )
                }

                if (state.isLoading)
                    item { Column(Modifier.fillParentMaxHeight()) { LoadingBox() } }
                else if (state.songs.isNotEmpty()) {
                    items(items = state.songs, key = { "${it.databaseId}:${it.title}" }) { song ->
                        SongCard(
                            song = song,
                            preferences = state.preferences,
                            actions = {
                                IconButton(onClick = { togglePlaylistDialogVisibility(song) }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.PlaylistAdd,
                                        contentDescription = stringResource(Res.string.cd_navigate_up),
                                    )
                                }
                                IconButton(onClick = { markSongAsFavourite(song) }) {
                                    Crossfade(targetState = song.isFavourite, label = "") {
                                        Icon(
                                            imageVector = if (it) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                                            contentDescription = stringResource(
                                                if (it) Res.string.remove_from_favourites
                                                else Res.string.add_to_favourites
                                            ),
                                        )
                                    }
                                }
                            },
                            modifier = Modifier.fillParentMaxWidth()
                        )
                    }
                } else item {
                    Column(Modifier.fillParentMaxHeight()) {
                        SongBookEmptyListInfo(messageId = Res.string.empty_search_list)
                    }
                }
            }

            ListScrollbar(
                isVisible = !state.isLoading && state.songs.isNotEmpty(),
                headerHeight = searchBarHeightDp,
                listState = lazyListState,
                listSize = state.songs.size,
                onDrag = { songIndexToAnimate = it },
            )
        }

        // TODO() -> It crashes sometimes, check this behaviour on newer compose versions
//        LaunchedEffect(state.songs) {
//            lazyListState.animateScrollToItem(0)
//        }
    }

    ChangeFontSizeDialog(
        isVisible = changeFontSizeDialogVisible,
        currentFontSize = state.preferences.fontSize,
        onSave = changeFontSize,
        dismiss = { changeFontSizeDialogVisible = false },
    )

    if (state.songSelectedToPlaylists != null)
        AddToPlaylistDialog(
            song = state.songSelectedToPlaylists,
            playlists = state.playlists,
            addNewPlaylist = addNewPlaylist,
            saveSongInPlaylists = saveSongInPlaylists,
            dismiss = { togglePlaylistDialogVisibility(null) },
        )

    NoPdfAppDialog(isVisible = pdfDialogVisible, onDismiss = { pdfDialogVisible = false })

    if (state.songEditorVisible)
        SongEditorDialog(
            song = null,
            onSave = saveSong,
            onDismiss = toggleSongEditorVisibility,
        )
}