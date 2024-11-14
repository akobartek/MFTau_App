package pl.mftau.mftau.songbook.presentation.songs.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.add_song
import org.jetbrains.compose.resources.stringResource
import pl.mftau.mftau.songbook.presentation.songs.SongBookAction

@Composable
fun SongBookBottomAppBar(
    actions: List<SongBookAction>,
    onFabClicked: () -> Unit = {},
) {
    BottomAppBar(
        actions = {
            actions.forEach {
                IconButton(onClick = it.onClick) {
                    Icon(imageVector = it.icon, contentDescription = it.description)
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onFabClicked() }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(Res.string.add_song),
                )
            }
        },
    )
}