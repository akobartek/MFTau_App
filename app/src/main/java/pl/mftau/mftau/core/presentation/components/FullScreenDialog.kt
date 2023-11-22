package pl.mftau.mftau.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import pl.mftau.mftau.R

@Composable
fun FullScreenDialog(
    isVisible: Boolean,
    title: String,
    onSave: () -> Unit = {},
    onDismiss: () -> Unit,
    content: @Composable ColumnScope.() -> Unit = {},
    action: @Composable RowScope.() -> Unit = {}
) {
    if (isVisible)
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
                decorFitsSystemWindows = true,
                usePlatformDefaultWidth = false
            )
        ) {
            val focusManager = LocalFocusManager.current
            val interactionSource = remember { MutableInteractionSource() }

            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .fillMaxSize(),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(horizontal = 8.dp)
                ) {
                    IconButton(onClick = onDismiss) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(id = R.string.close_dialog)
                        )
                    }
                    Text(
                        style = MaterialTheme.typography.titleLarge,
                        text = title,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp)
                    )
                    TextButton(onClick = onSave) {
                        Text(text = stringResource(id = R.string.save))
                    }
                    action()
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 24.dp, start = 24.dp, end = 24.dp)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) { focusManager.clearFocus(true) }
                ) {
                    content()
                }
            }
        }
}