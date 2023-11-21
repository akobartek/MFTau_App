package pl.mftau.mftau.songbook.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pl.mftau.mftau.R
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
    var chords by remember { mutableStateOf(song.chords) }

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
                Column {
                    AnimatedVisibility(visible = preferences.areChordsVisible) {
                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            IconButton(onClick = {
                                chords = chords.getTransposedChords(-1)
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.ExpandMore,
                                    contentDescription = stringResource(id = R.string.song_transposition_down)
                                )
                            }
                            IconButton(onClick = {
                                chords = chords.getTransposedChords(1)
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.ExpandLess,
                                    contentDescription = stringResource(id = R.string.song_transposition_up)
                                )
                            }
                        }
                    }

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
                        AnimatedVisibility(visible = preferences.areChordsVisible) {
                            if (preferences.areChordsVisible) {
                                VerticalDivider(
                                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                                    modifier = Modifier.padding(horizontal = 4.dp)
                                )
                                SongText(
                                    text = chords,
                                    fontSize = preferences.fontSize
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


/**
mollChords = c, cis, d, dis, e, f, fis, g, gis, a, b, h
durChords  = C, Cis, D, Dis, E, F, Fis, G, Gis, A, B, H
 */
fun String.getTransposedChords(valueChange: Int): String =
    this.split("\n").joinToString("\n") { chordsLine ->
        chordsLine.split(" ").joinToString(" ") { chord ->
            if (chord.contains("cis"))
                chord.replace("cis", if (valueChange == 1) "d" else "c")
            else if (chord.contains("dis"))
                chord.replace("dis", if (valueChange == 1) "e" else "d")
            else if (chord.contains("fis"))
                chord.replace("fis", if (valueChange == 1) "g" else "f")
            else if (chord.contains("gis"))
                chord.replace("gis", if (valueChange == 1) "a" else "g")
            else if (chord.contains("c"))
                chord.replace("c", if (valueChange == 1) "cis" else "h")
            else if (chord.contains("d"))
                chord.replace("d", if (valueChange == 1) "dis" else "cis")
            else if (chord.contains("e"))
                chord.replace("e", if (valueChange == 1) "f" else "dis")
            else if (chord.contains("f"))
                chord.replace("f", if (valueChange == 1) "fis" else "e")
            else if (chord.contains("g"))
                chord.replace("g", if (valueChange == 1) "gis" else "fis")
            else if (chord.contains("a"))
                chord.replace("a", if (valueChange == 1) "b" else "gis")
            else if (chord.contains("b"))
                chord.replace("b", if (valueChange == 1) "h" else "a")
            else if (chord.contains("h"))
                chord.replace("h", if (valueChange == 1) "c" else "b")
            else if (chord.contains("Cis"))
                chord.replace("Cis", if (valueChange == 1) "D" else "C")
            else if (chord.contains("Dis"))
                chord.replace("Dis", if (valueChange == 1) "E" else "D")
            else if (chord.contains("Fis"))
                chord.replace("Fis", if (valueChange == 1) "G" else "F")
            else if (chord.contains("Gis"))
                chord.replace("Gis", if (valueChange == 1) "A" else "G")
            else if (chord.contains("C"))
                chord.replace("C", if (valueChange == 1) "Cis" else "H")
            else if (chord.contains("D"))
                chord.replace("D", if (valueChange == 1) "Dis" else "Cis")
            else if (chord.contains("E"))
                chord.replace("E", if (valueChange == 1) "F" else "Dis")
            else if (chord.contains("F"))
                chord.replace("F", if (valueChange == 1) "Fis" else "E")
            else if (chord.contains("G"))
                chord.replace("G", if (valueChange == 1) "Gis" else "Fis")
            else if (chord.contains("A"))
                chord.replace("A", if (valueChange == 1) "B" else "Gis")
            else if (chord.contains("B"))
                chord.replace("B", if (valueChange == 1) "H" else "A")
            else if (chord.contains("H"))
                chord.replace("H", if (valueChange == 1) "C" else "B")
            else ""
        }
    }