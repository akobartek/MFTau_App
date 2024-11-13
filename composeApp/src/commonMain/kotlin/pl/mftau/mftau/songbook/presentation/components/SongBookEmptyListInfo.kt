package pl.mftau.mftau.songbook.presentation.components

import androidx.compose.runtime.Composable
import pl.mftau.mftau.R
import pl.mftau.mftau.common.presentation.composables.EmptyListInfo

@Composable
fun SongBookEmptyListInfo(messageId: Int) {
    EmptyListInfo(messageId = messageId, drawableId = R.drawable.ic_empty_songbook)
}