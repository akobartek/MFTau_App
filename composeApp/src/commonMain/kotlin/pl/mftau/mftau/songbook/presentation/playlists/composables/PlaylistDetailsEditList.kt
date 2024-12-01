package pl.mftau.mftau.songbook.presentation.playlists.composables

import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Reorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.remove_from_playlist
import org.jetbrains.compose.resources.stringResource
import pl.mftau.mftau.common.data.SongBookPreferences
import pl.mftau.mftau.common.presentation.rememberDragAndDropListState
import pl.mftau.mftau.songbook.domain.model.Song
import pl.mftau.mftau.songbook.presentation.songs.components.SongCard

@Composable
fun PlaylistDetailsEditList(
    modifier: Modifier = Modifier,
    songs: List<Song>,
    preferences: SongBookPreferences,
    deleteSong: (Song) -> Unit,
    swapSongs: (Int, Int) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    var overscrollJob by remember { mutableStateOf<Job?>(null) }
    val dragAndDropListState = rememberDragAndDropListState(lazyListState) { from, to ->
        swapSongs(from, to)
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        state = dragAndDropListState.lazyListState,
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGesturesAfterLongPress(
                    onDrag = { change, offset ->
                        change.consume()
                        dragAndDropListState.onDrag(offset)

                        if (overscrollJob?.isActive == true) return@detectDragGesturesAfterLongPress

                        dragAndDropListState
                            .checkOverscroll()
                            .takeIf { it != 0f }
                            ?.let {
                                overscrollJob = coroutineScope.launch {
                                    dragAndDropListState.lazyListState.scrollBy(it)
                                }
                            } ?: kotlin.run { overscrollJob?.cancel() }
                    },
                    onDragStart = { offset -> dragAndDropListState.onDragStart(offset) },
                    onDragEnd = { dragAndDropListState.onDragInterrupted() },
                    onDragCancel = { dragAndDropListState.onDragInterrupted() },
                )
            },
    ) {
        items(items = songs, key = { song -> song.title }) { song ->
            SongCard(
                song = song,
                preferences = preferences,
                actions = {
                    Icon(
                        imageVector = Icons.Filled.Reorder,
                        contentDescription = null,
                    )
                    IconButton(onClick = { deleteSong(song) }) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = stringResource(Res.string.remove_from_playlist),
                        )
                    }
                },
                onClick = {},
                modifier = Modifier.animateItem(),
            )
        }
    }
}