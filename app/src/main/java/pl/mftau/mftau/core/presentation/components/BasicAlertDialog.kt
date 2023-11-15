package pl.mftau.mftau.core.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

@Composable
fun BasicAlertDialog(
    imageVector: ImageVector,
    dismissible: Boolean = true,
    dialogTitleId: Int,
    dialogTextId: Int,
    confirmBtnTextId: Int,
    onConfirmation: () -> Unit,
    dismissBtnTextId: Int? = null,
    onDismissRequest: () -> Unit = {}
) {
    AlertDialog(
        icon = { Icon(imageVector = imageVector, contentDescription = null) },
        title = { Text(text = stringResource(id = dialogTitleId), textAlign = TextAlign.Center) },
        text = { Text(text = stringResource(id = dialogTextId)) },
        onDismissRequest = { if (dismissible) onDismissRequest() },
        confirmButton = {
            TextButton(onClick = onConfirmation) {
                Text(stringResource(id = confirmBtnTextId))
            }
        },
        dismissButton = {
            if (dismissBtnTextId != null)
                TextButton(onClick = onDismissRequest) {
                    Text(stringResource(id = dismissBtnTextId))
                }
        }
    )
}