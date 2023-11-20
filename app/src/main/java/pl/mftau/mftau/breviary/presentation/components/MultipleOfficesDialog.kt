package pl.mftau.mftau.breviary.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pl.mftau.mftau.R
import pl.mftau.mftau.ui.theme.mfTauFont

@Composable
fun MultipleOfficesDialog(
    offices: Map<String, String>,
    onSelect: (String) -> Unit,
    onCancel: () -> Unit
) {
    var selectedOfficeLink by remember { mutableStateOf(offices.keys.toTypedArray()[0]) }

    AlertDialog(
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_breviary),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        },
        title = {
            Text(text = stringResource(id = R.string.select_office), fontFamily = mfTauFont)
        },
        text = {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                offices.forEach { (link, text) ->
                    Row(
                        modifier = Modifier
                            .padding(vertical = 2.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .clickable { selectedOfficeLink = link }
                            .background(
                                color = if (selectedOfficeLink == link) MaterialTheme.colorScheme.secondaryContainer
                                else MaterialTheme.colorScheme.background,
                            )
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = text,
                            color =
                            if (selectedOfficeLink == link) MaterialTheme.colorScheme.onSecondaryContainer
                            else MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        },
        onDismissRequest = onCancel,
        confirmButton = {
            TextButton(onClick = { onSelect(selectedOfficeLink) }) {
                Text(stringResource(id = R.string.save))
            }
        },
        dismissButton = {
            TextButton(onClick = onCancel) {
                Text(stringResource(id = R.string.cancel))
            }
        },
        modifier = Modifier.heightIn(max = 560.dp)
    )
}