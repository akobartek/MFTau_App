package pl.mftau.mftau.songbook.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pl.mftau.mftau.songbook.domain.model.Song
import pl.mftau.mftau.songbook.domain.model.SongBookPreferences

@Composable
fun SongCard(
    modifier: Modifier = Modifier,
    song: Song,
    preferences: SongBookPreferences = SongBookPreferences(),
    actions: @Composable RowScope.() -> Unit = {},
    onClick: ((Song) -> Unit)? = null
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        onClick = {
            if (onClick == null) expanded = !expanded
            else onClick(song)
        },
        modifier = modifier.fillMaxWidth()
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = song.title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                        .padding(vertical = 12.dp)
                )
                actions()
            }
            AnimatedVisibility(visible = expanded) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(top = 4.dp)
                ) {
                    SongText(
                        text = song.text,
                        fontSize = preferences.fontSize,
                        modifier = Modifier
                            .weight(1f)
                            .horizontalScroll(rememberScrollState())
                    )
                    if (preferences.areChordsVisible) {
                        VerticalDivider(
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )
                        SongText(
                            text = song.chords,
                            fontSize = preferences.fontSize
                        )
                    }
                }
            }
        }
    }
}