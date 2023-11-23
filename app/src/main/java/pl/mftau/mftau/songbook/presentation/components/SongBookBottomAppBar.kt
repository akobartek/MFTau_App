package pl.mftau.mftau.songbook.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.QueueMusic
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.MusicOff
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import pl.mftau.mftau.R
import pl.mftau.mftau.common.presentation.components.NoPdfAppDialog
import pl.mftau.mftau.core.utils.openPdf
import pl.mftau.mftau.core.utils.safePop
import pl.mftau.mftau.core.utils.safePush
import pl.mftau.mftau.songbook.presentation.screens.AddedSongsListScreen
import pl.mftau.mftau.songbook.presentation.screens.PlaylistsListScreen
import pl.mftau.mftau.songbook.presentation.screens.SongBookListScreen

@Preview(showBackground = true)
@Composable
fun SongBookBottomAppBar(
    areChordsVisible: Boolean = false,
    toggleChordsVisibility: () -> Unit = {},
    showChangeFontDialog: () -> Unit = {},
    onFabClicked: () -> Unit = {},
    onPositioned: (Offset) -> Unit = {}
) {
    val navigator = LocalNavigator.currentOrThrow
    val context = LocalContext.current

    var pdfDialogVisible by remember { mutableStateOf(false) }

    BottomAppBar(
        actions = {
            IconButton(onClick = { navigator.safePop(SongBookListScreen.KEY) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.cd_navigate_up)
                )
            }
            IconButton(onClick = {
                if (!context.openPdf("spiewnik.pdf"))
                    pdfDialogVisible = true
            }) {
                Icon(
                    imageVector = Icons.Filled.PictureAsPdf,
                    contentDescription = stringResource(id = R.string.open_pdf)
                )
            }
            IconButton(onClick = { navigator.safePush(AddedSongsListScreen()) }) {
                Icon(
                    imageVector = Icons.Filled.PostAdd,
                    contentDescription = stringResource(id = R.string.show_added_songs)
                )
            }
            IconButton(onClick = { navigator.safePush(PlaylistsListScreen()) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.QueueMusic,
                    contentDescription = stringResource(id = R.string.show_playlists)
                )
            }
            IconButton(onClick = toggleChordsVisibility) {
                Icon(
                    imageVector = if (areChordsVisible) Icons.Filled.MusicNote else Icons.Filled.MusicOff,
                    contentDescription =
                    if (areChordsVisible) stringResource(id = R.string.hide_chords)
                    else stringResource(id = R.string.show_chords),
                )
            }
            IconButton(onClick = showChangeFontDialog) {
                Icon(
                    imageVector = Icons.Filled.FormatSize,
                    contentDescription = stringResource(id = R.string.change_font_size)
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onFabClicked() },
                modifier = Modifier.onGloballyPositioned { cords ->
                    onPositioned(cords.boundsInRoot().center)
                }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(id = R.string.add_song)
                )
            }
        },
    )

    NoPdfAppDialog(isVisible = pdfDialogVisible, onDismiss = { pdfDialogVisible = false })
}