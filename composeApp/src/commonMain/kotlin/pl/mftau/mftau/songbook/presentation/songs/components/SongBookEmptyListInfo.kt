package pl.mftau.mftau.songbook.presentation.songs.components

import androidx.compose.runtime.Composable
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.ic_empty_songbook
import org.jetbrains.compose.resources.StringResource
import pl.mftau.mftau.common.presentation.composables.EmptyListInfo

@Composable
fun SongBookEmptyListInfo(messageId: StringResource) {
    EmptyListInfo(messageId = messageId, drawableId = Res.drawable.ic_empty_songbook)
}