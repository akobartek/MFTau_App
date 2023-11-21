package pl.mftau.mftau.songbook.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.runtime.Composable
import pl.mftau.mftau.R
import pl.mftau.mftau.core.presentation.components.MFTauAlertDialog

@Composable
fun ImportPlaylistErrorDialog(
    onConfirm: () -> Unit
) {
    MFTauAlertDialog(
        imageVector = Icons.Default.ErrorOutline,
        dialogTitleId = R.string.import_playlist,
        dialogTextId = R.string.import_error_dialog_msg,
        dismissible = false,
        confirmBtnTextId = R.string.ok,
        onConfirm = onConfirm
    )
}