package pl.mftau.mftau.songbook.presentation.playlists.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.runtime.Composable
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.ok
import mftau.composeapp.generated.resources.share_empty_list_error
import mftau.composeapp.generated.resources.share_playlist
import pl.mftau.mftau.common.presentation.composables.TauAlertDialog

@Composable
fun SharePlaylistErrorDialog(
    isVisible: Boolean,
    onConfirm: () -> Unit,
) {
    TauAlertDialog(
        isVisible = isVisible,
        imageVector = Icons.Default.ErrorOutline,
        dialogTitleId = Res.string.share_playlist,
        dialogTextId = Res.string.share_empty_list_error,
        confirmBtnTextId = Res.string.ok,
        onConfirm = onConfirm,
    )
}