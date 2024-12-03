package pl.mftau.mftau.common.presentation.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.runtime.Composable
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.cancel
import mftau.composeapp.generated.resources.no_pdf_viewer_dialog_msg
import mftau.composeapp.generated.resources.no_pdf_viewer_dialog_title

@Composable
fun NoPdfAppDialog(
    isVisible: Boolean,
    onDismiss: () -> Unit = {}
) {
    TauAlertDialog(
        isVisible = isVisible,
        imageVector = Icons.Default.ErrorOutline,
        dialogTitleId = Res.string.no_pdf_viewer_dialog_title,
        dialogTextId = Res.string.no_pdf_viewer_dialog_msg,
        confirmBtnTextId = Res.string.cancel,
        onConfirm = onDismiss,
        onDismissRequest = onDismiss,
    )
}