package pl.mftau.mftau.view.ui

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import pl.mftau.mftau.R
import pl.mftau.mftau.utils.PreferencesManager
import pl.mftau.mftau.utils.getAttributeColor

@Preview(showBackground = true)
@Composable
fun SongBookBottomAppBar(
    navigateUp: () -> Unit = {},
    openPdfFragment: () -> Unit = {},
    showAddedSongs: () -> Unit = {},
    showPlaylist: () -> Unit = {},
    showChords: (Boolean) -> Unit = {},
    showChangeFontDialog: () -> Unit = {},
    showAddNewSongFragment: () -> Unit = {}
) {
    var areChordsVisible by remember {
        mutableStateOf(PreferencesManager.getSongBookShowCords())
    }

    BottomAppBar(
        actions = {
            IconButton(onClick = { navigateUp() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = stringResource(id = R.string.cd_navigate_up)
                )
            }
            IconButton(onClick = { openPdfFragment() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_pdf),
                    contentDescription = stringResource(id = R.string.open_pdf)
                )
            }
            IconButton(onClick = { showAddedSongs() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_songs_added),
                    contentDescription = stringResource(id = R.string.show_added_songs)
                )
            }
            IconButton(onClick = { showPlaylist() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_playlist_play),
                    contentDescription = stringResource(id = R.string.show_playlist)
                )
            }
            IconButton(onClick = {
                areChordsVisible = !areChordsVisible
                showChords(areChordsVisible)
            }) {
                Icon(
                    painter = painterResource(
                        id = if (areChordsVisible) R.drawable.ic_note_outline else R.drawable.ic_note
                    ),
                    contentDescription = stringResource(id = R.string.show_chords)
                )
            }
            IconButton(onClick = { showChangeFontDialog() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_format_size),
                    contentDescription = stringResource(id = R.string.change_font_size)
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddNewSongFragment() },
                containerColor = Color(LocalContext.current.getAttributeColor(R.attr.colorPrimaryContainer)),
                contentColor = Color(LocalContext.current.getAttributeColor(R.attr.colorOnPrimaryContainer))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = "Localized description"
                )
            }
        },
        containerColor = Color(LocalContext.current.getAttributeColor(R.attr.colorSurface)),
        contentColor = Color(LocalContext.current.getAttributeColor(R.attr.colorOnSurface))
    )
}