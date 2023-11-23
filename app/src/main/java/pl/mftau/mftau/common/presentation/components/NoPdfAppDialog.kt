package pl.mftau.mftau.common.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import pl.mftau.mftau.R
import pl.mftau.mftau.core.utils.openWebsiteInChromeCustomTabsIfSupported

@Composable
fun NoPdfAppDialog(
    isVisible: Boolean,
    onDismiss: () -> Unit = {}
) {
    val context = LocalContext.current

    TauAlertDialog(
        isVisible = isVisible,
        imageVector = Icons.Default.ErrorOutline,
        dialogTitleId = R.string.no_pdf_viewer_dialog_title,
        dialogTextId = R.string.no_pdf_viewer_dialog_msg,
        confirmBtnTextId = R.string.search,
        onConfirm = {
            onDismiss()
            context.openWebsiteInChromeCustomTabsIfSupported("https://play.google.com/store/search?q=pdf")
        },
        dismissBtnTextId = R.string.cancel,
        onDismissRequest = onDismiss
    )
}