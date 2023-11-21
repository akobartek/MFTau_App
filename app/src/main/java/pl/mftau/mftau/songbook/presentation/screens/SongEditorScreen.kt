package pl.mftau.mftau.songbook.presentation.screens

import androidx.compose.runtime.Composable
import pl.mftau.mftau.songbook.domain.model.Song

class SongEditorScreen(private val song: Song?): SongBookScreen() {
    @Composable
    override fun Content() {
        SongEditorScreenContent(song)
    }
}

@Composable
fun SongEditorScreenContent(song: Song?) {

}