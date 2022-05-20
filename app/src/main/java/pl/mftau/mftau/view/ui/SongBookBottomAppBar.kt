package pl.mftau.mftau.view.ui

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import pl.mftau.mftau.R
import pl.mftau.mftau.utils.PreferencesManager

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
        icons = {
            IconButton(onClick = { navigateUp() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = stringResource(id = R.string.cd_navigate_up)
                )
            }
            IconButton(onClick = { openPdfFragment() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_pdf),
                    contentDescription = stringResource(id = R.string.cd_open_pdf)
                )
            }
            IconButton(onClick = { showAddedSongs() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_songs_added),
                    contentDescription = stringResource(id = R.string.cd_show_added_songs)
                )
            }
            IconButton(onClick = { showPlaylist() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_playlist_play),
                    contentDescription = stringResource(id = R.string.cd_show_playlist)
                )
            }
            IconButton(onClick = {
                areChordsVisible = !areChordsVisible
                showChords(areChordsVisible)
            }) {
                Icon(
                    painter = painterResource(
                        id = if (areChordsVisible) R.drawable.ic_note else R.drawable.ic_note_outline
                    ),
                    contentDescription = stringResource(id = R.string.cd_show_chords)
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
                elevation = BottomAppBarDefaults.floatingActionButtonElevation()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = "Localized description"
                )
            }
        }
    )
}