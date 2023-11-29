package pl.mftau.mftau.songbook.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.PlaylistAdd
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pl.mftau.mftau.R
import pl.mftau.mftau.common.presentation.components.LoadingBox
import pl.mftau.mftau.common.utils.showShortToast
import pl.mftau.mftau.songbook.domain.model.Playlist
import pl.mftau.mftau.songbook.domain.model.Song
import pl.mftau.mftau.ui.theme.mfTauFont

@Composable
fun AddToPlaylistDialog(
    song: Song,
    playlists: List<Playlist>,
    addNewPlaylist: (String) -> Unit,
    saveSongInPlaylists: (List<Playlist>) -> Unit,
    dismiss: () -> Unit
) {
    val context = LocalContext.current
    val density = LocalDensity.current

    var selectedPlaylists by rememberSaveable {
        mutableStateOf(playlists.filter { it.songs.contains(song) })
    }
    var newPlaylistName by rememberSaveable { mutableStateOf("") }
    var isLoading by rememberSaveable { mutableStateOf(false) }
    val listModifier = if (isLoading) Modifier.blur(16.dp) else Modifier
    var listHeight by remember { mutableStateOf(0.dp) }

    LaunchedEffect(key1 = playlists) {
        isLoading = false
        newPlaylistName = ""
    }

    AlertDialog(
        icon = {
            Icon(imageVector = Icons.AutoMirrored.Filled.PlaylistAdd, contentDescription = null)
        },
        title = {
            Text(text = stringResource(id = R.string.add_to_playlist), fontFamily = mfTauFont)
        },
        text = {
            Box {
                LazyColumn(
                    contentPadding = PaddingValues(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = listModifier.onGloballyPositioned { coords ->
                        listHeight = with(density) { coords.size.height.toDp() }
                    }
                ) {
                    items(playlists) { playlist ->
                        val isSelected = selectedPlaylists.contains(playlist)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    color =
                                    if (isSelected) MaterialTheme.colorScheme.secondaryContainer
                                    else MaterialTheme.colorScheme.background,
                                )
                                .clickable {
                                    if (isSelected) selectedPlaylists -= playlist
                                    else selectedPlaylists += playlist
                                }
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = playlist.name,
                                color =
                                if (isSelected) MaterialTheme.colorScheme.onSecondaryContainer
                                else MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                    item {
                        CreatePlaylistRow(
                            newPlaylistName = newPlaylistName,
                            onChangeName = { newPlaylistName = it },
                            onSave = {
                                isLoading = true
                                addNewPlaylist(newPlaylistName.trim())
                            },
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }

                if (isLoading)
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.height(listHeight)
                    ) {
                        LoadingBox()
                    }
            }
        },
        onDismissRequest = { if (!isLoading) dismiss() },
        confirmButton = {
            TextButton(onClick = {
                saveSongInPlaylists(selectedPlaylists)
                context.showShortToast(R.string.playlists_updated)
            }) {
                Text(stringResource(id = R.string.save))
            }
        },
        dismissButton = {
            TextButton(onClick = dismiss) {
                Text(stringResource(id = R.string.cancel))
            }
        },
        modifier = Modifier.heightIn(max = 600.dp)
    )
}