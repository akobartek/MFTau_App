package pl.mftau.mftau.songbook.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import pl.mftau.mftau.R
import pl.mftau.mftau.core.presentation.components.MFTauAlertDialog

@Composable
fun DeletePlaylistDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    MFTauAlertDialog(
        imageVector = Icons.Default.Delete,
        dialogTitleId = R.string.delete_playlist,
        dialogTextId = R.string.delete_playlist_dialog_msg,
        confirmBtnTextId = R.string.delete,
        onConfirm = {
            onDismiss()
            onConfirm()
        },
        onDismissRequest = {}
    )
}