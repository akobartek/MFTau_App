package pl.mftau.mftau.songbook.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.runtime.Composable
import pl.mftau.mftau.R
import pl.mftau.mftau.core.presentation.components.MFTauAlertDialog

@Composable
fun SharePlaylistErrorDialog(
    onConfirm:()->Unit
) {
    MFTauAlertDialog(
        imageVector = Icons.Default.ErrorOutline,
        dialogTitleId = R.string.share_playlist,
        dialogTextId = R.string.share_empty_list_error,
        confirmBtnTextId = R.string.ok,
        onConfirm = onConfirm
    )
}