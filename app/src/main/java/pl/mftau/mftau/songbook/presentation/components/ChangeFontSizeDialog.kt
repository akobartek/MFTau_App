package pl.mftau.mftau.songbook.presentation.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.TextDecrease
import androidx.compose.material.icons.filled.TextIncrease
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pl.mftau.mftau.R
import pl.mftau.mftau.core.utils.showShortToast
import pl.mftau.mftau.songbook.domain.model.SongBookPreferences.Companion.DEFAULT_FONT_SIZE
import pl.mftau.mftau.ui.theme.mfTauFont

@Composable
fun ChangeFontSizeDialog(
    isVisible: Boolean,
    currentFontSize: Int,
    onSave: (Int) -> Unit,
    dismiss: () -> Unit
) {
    val context = LocalContext.current
    var selectedFontSize by remember { mutableIntStateOf(currentFontSize) }
    val minValue = 12
    val maxValue = 36

    if (isVisible)
        AlertDialog(
            icon = {
                Icon(imageVector = Icons.Filled.FormatSize, contentDescription = null)
            },
            title = {
                Text(text = stringResource(id = R.string.change_font_size), fontFamily = mfTauFont)
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.example_text),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    SongText(
                        text = stringResource(id = R.string.example_song),
                        fontSize = selectedFontSize,
                        modifier = Modifier
                            .heightIn(max = 360.dp)
                            .horizontalScroll(rememberScrollState())
                            .verticalScroll(rememberScrollState())
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        IconButton(onClick = {
                            if (selectedFontSize > minValue) selectedFontSize--
                            else context.showShortToast(R.string.min_size_msg)
                        }) {
                            Icon(
                                imageVector = Icons.Filled.TextDecrease,
                                tint = MaterialTheme.colorScheme.primary,
                                contentDescription = stringResource(id = R.string.lower_font_size)
                            )
                        }
                        TextButton(onClick = { selectedFontSize = DEFAULT_FONT_SIZE }) {
                            Text(text = stringResource(id = R.string.reset))
                        }
                        IconButton(onClick = {
                            if (selectedFontSize < maxValue) selectedFontSize++
                            else context.showShortToast(R.string.max_size_msg)
                        }) {
                            Icon(
                                imageVector = Icons.Filled.TextIncrease,
                                tint = MaterialTheme.colorScheme.primary,
                                contentDescription = stringResource(id = R.string.increase_font_size)
                            )
                        }
                    }
                }
            },
            onDismissRequest = {},
            confirmButton = {
                TextButton(onClick = {
                    onSave(selectedFontSize)
                    dismiss()
                }) {
                    Text(stringResource(id = R.string.save))
                }
            },
            dismissButton = {
                TextButton(onClick = dismiss) {
                    Text(stringResource(id = R.string.cancel))
                }
            }
        )
}