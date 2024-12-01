package pl.mftau.mftau.songbook.presentation.playlists.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pl.mftau.mftau.common.data.SongBookPreferences
import pl.mftau.mftau.songbook.domain.model.Song
import pl.mftau.mftau.songbook.presentation.songs.components.SongCard

@Composable
fun PlaylistDetailsNormalList(
    modifier: Modifier = Modifier,
    songs: List<Song>,
    preferences: SongBookPreferences,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier.fillMaxSize(),
    ) {
        items(
            items = songs,
            key = { song -> song.title },
        ) { song ->
            SongCard(
                song = song,
                preferences = preferences,
                modifier = Modifier.animateItem(),
            )
        }
    }
}