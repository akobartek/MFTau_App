package pl.mftau.mftau.songbook.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import pl.mftau.mftau.R
import pl.mftau.mftau.core.presentation.components.TauAlertDialog

@Composable
fun DeleteSongDialog(
    isVisible: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    TauAlertDialog(
        isVisible = isVisible,
        imageVector = Icons.Filled.Delete,
        dialogTitleId = R.string.delete_song,
        dialogTextId = R.string.song_delete_dialog_msg,
        confirmBtnTextId = R.string.delete,
        onConfirm = {
            onConfirm()
            onDismiss()
        },
        dismissible = true,
        dismissBtnTextId = R.string.cancel,
        onDismissRequest = onDismiss
    )
}