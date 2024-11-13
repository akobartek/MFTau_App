package pl.mftau.mftau.common.presentation.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import pl.mftau.mftau.ui.theme.mfTauFont

@Composable
fun TauAlertDialog(
    isVisible: Boolean,
    imageVector: ImageVector? = null,
    painter: Painter? = null,
    dismissible: Boolean = true,
    dialogTitleId: StringResource,
    dialogTextId: StringResource,
    confirmBtnTextId: StringResource,
    onConfirm: () -> Unit,
    dismissBtnTextId: StringResource? = null,
    onDismissRequest: () -> Unit = {}
) {
    if (isVisible)
        AlertDialog(
            icon = {
                if (imageVector != null)
                    Icon(imageVector = imageVector, contentDescription = null)
                if (painter != null)
                    Icon(
                        painter = painter,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(24.dp),
                    )
            },
            title = {
                Text(
                    text = stringResource(dialogTitleId),
                    textAlign = TextAlign.Center,
                    fontFamily = mfTauFont(),
                )
            },
            text = { Text(text = stringResource(dialogTextId)) },
            onDismissRequest = { if (dismissible) onDismissRequest() },
            confirmButton = {
                TextButton(onClick = onConfirm) {
                    Text(stringResource(confirmBtnTextId))
                }
            },
            dismissButton = {
                if (dismissBtnTextId != null)
                    TextButton(onClick = onDismissRequest) {
                        Text(stringResource(dismissBtnTextId))
                    }
            }
        )
}