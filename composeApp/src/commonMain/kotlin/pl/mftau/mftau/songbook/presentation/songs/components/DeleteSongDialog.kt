package pl.mftau.mftau.songbook.presentation.songs.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.cancel
import mftau.composeapp.generated.resources.delete
import mftau.composeapp.generated.resources.delete_song
import mftau.composeapp.generated.resources.song_delete_dialog_msg
import pl.mftau.mftau.common.presentation.composables.TauAlertDialog

@Composable
fun DeleteSongDialog(
    isVisible: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    TauAlertDialog(
        isVisible = isVisible,
        imageVector = Icons.Filled.Delete,
        dialogTitleId = Res.string.delete_song,
        dialogTextId = Res.string.song_delete_dialog_msg,
        confirmBtnTextId = Res.string.delete,
        onConfirm = {
            onConfirm()
            onDismiss()
        },
        dismissible = true,
        dismissBtnTextId = Res.string.cancel,
        onDismissRequest = onDismiss,
    )
}