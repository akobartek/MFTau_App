package pl.mftau.mftau.common.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.runtime.Composable
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.cancel
import mftau.composeapp.generated.resources.no_pdf_viewer_dialog_msg
import mftau.composeapp.generated.resources.no_pdf_viewer_dialog_title
import mftau.composeapp.generated.resources.search

@Composable
fun NoPdfAppDialog(
    isVisible: Boolean,
    onDismiss: () -> Unit = {}
) {
//    val context = LocalContext.current

    TauAlertDialog(
        isVisible = isVisible,
        imageVector = Icons.Default.ErrorOutline,
        dialogTitleId = Res.string.no_pdf_viewer_dialog_title,
        dialogTextId = Res.string.no_pdf_viewer_dialog_msg,
        confirmBtnTextId = Res.string.search,
        onConfirm = {
            onDismiss()
            // TODO
//            context.openWebsiteInChromeCustomTabsIfSupported("https://play.google.com/store/search?q=pdf")
        },
        dismissBtnTextId = Res.string.cancel,
        onDismissRequest = onDismiss
    )
}