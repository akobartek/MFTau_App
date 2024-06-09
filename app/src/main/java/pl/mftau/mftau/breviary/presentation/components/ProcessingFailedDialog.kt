package pl.mftau.mftau.breviary.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RunningWithErrors
import androidx.compose.runtime.Composable
import pl.mftau.mftau.R
import pl.mftau.mftau.common.presentation.components.TauAlertDialog

@Composable
fun ProcessingFailedDialog(
    isVisible: Boolean,
    buttonClicked: Boolean,
    onDownloads: () -> Unit,
    onDismiss: () -> Unit
) {
    TauAlertDialog(
        isVisible = isVisible,
        imageVector = Icons.Default.RunningWithErrors,
        dialogTitleId = R.string.processing_failed_dialog_title,
        dialogTextId = R.string.processing_failed_dialog_message,
        confirmBtnTextId = if (buttonClicked) R.string.load else R.string.processing_failed_downloads_button,
        onConfirm = onDownloads,
        dismissBtnTextId = R.string.cancel,
        dismissible = false,
        onDismissRequest = onDismiss
    )
}