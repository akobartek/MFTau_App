package pl.mftau.mftau.core.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import pl.mftau.mftau.R
import pl.mftau.mftau.core.utils.openWebsiteInChromeCustomTabsIfSupported

@Composable
fun NoPdfAppDialog(onDismiss: () -> Unit = {}) {
    val context = LocalContext.current

    MFTauAlertDialog(
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