package pl.mftau.mftau.songbook.presentation.playlists.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.runtime.Composable
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.import_error_dialog_msg
import mftau.composeapp.generated.resources.import_playlist
import mftau.composeapp.generated.resources.ok
import pl.mftau.mftau.common.presentation.composables.TauAlertDialog

@Composable
fun ImportPlaylistErrorDialog(
    isVisible: Boolean,
    onConfirm: () -> Unit,
) {
    TauAlertDialog(
        isVisible = isVisible,
        imageVector = Icons.Default.ErrorOutline,
        dialogTitleId = Res.string.import_playlist,
        dialogTextId = Res.string.import_error_dialog_msg,
        dismissible = false,
        confirmBtnTextId = Res.string.ok,
        onConfirm = onConfirm,
    )
}