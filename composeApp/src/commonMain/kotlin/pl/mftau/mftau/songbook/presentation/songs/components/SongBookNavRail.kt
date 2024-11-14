package pl.mftau.mftau.songbook.presentation.songs.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import pl.mftau.mftau.songbook.presentation.songs.SongBookAction

@Composable
fun SongBookNavRail(actions: List<SongBookAction>) {
    NavigationRail {
        actions.forEach {
            NavigationRailItem(
                selected = false,
                alwaysShowLabel = false,
                onClick = it.onClick,
                icon = { Icon(imageVector = it.icon, contentDescription = it.description) },
            )
        }
    }
}