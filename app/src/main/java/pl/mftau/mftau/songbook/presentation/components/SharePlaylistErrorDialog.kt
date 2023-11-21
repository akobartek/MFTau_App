package pl.mftau.mftau.songbook.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.runtime.Composable
import pl.mftau.mftau.R
import pl.mftau.mftau.core.presentation.components.TauAlertDialog

@Composable
fun SharePlaylistErrorDialog(
    onConfirm:()->Unit
) {
    TauAlertDialog(
        imageVector = Icons.Default.ErrorOutline,
        dialogTitleId = R.string.share_playlist,
        dialogTextId = R.string.share_empty_list_error,
        confirmBtnTextId = R.string.ok,
        onConfirm = onConfirm
    )
}