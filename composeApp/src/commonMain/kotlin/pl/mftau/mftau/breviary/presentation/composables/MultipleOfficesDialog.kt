package pl.mftau.mftau.breviary.presentation.composables

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.cancel
import mftau.composeapp.generated.resources.ic_breviary
import mftau.composeapp.generated.resources.load
import mftau.composeapp.generated.resources.select_office
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MultipleOfficesDialog(
    offices: Map<String, String>,
    onSelect: (String) -> Unit,
    onCancel: () -> Unit
) {
    var selectedOfficeLink by rememberSaveable { mutableStateOf(offices.keys.toTypedArray()[0]) }
    var isVisible by rememberSaveable { mutableStateOf(true) }

    LaunchedEffect(isVisible) {
        if (!isVisible) onCancel()
    }

    if (isVisible)
        AlertDialog(
            icon = {
                Icon(
                    painter = painterResource(Res.drawable.ic_breviary),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            },
            title = {
                Text(
                    text = stringResource(Res.string.select_office),
                    style = MaterialTheme.typography.headlineSmall.copy(textAlign = TextAlign.Center),
                )
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
                                    color =
                                    if (selectedOfficeLink == link) MaterialTheme.colorScheme.secondaryContainer
                                    else MaterialTheme.colorScheme.background,
                                )
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = text,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color =
                                    if (selectedOfficeLink == link) MaterialTheme.colorScheme.onSecondaryContainer
                                    else MaterialTheme.colorScheme.onBackground,
                                ),
                            )
                        }
                    }
                }
            },
            onDismissRequest = { isVisible = false },
            confirmButton = {
                TextButton(onClick = { onSelect(selectedOfficeLink) }) {
                    Text(stringResource(Res.string.load))
                }
            },
            dismissButton = {
                TextButton(onClick = { isVisible = false }) {
                    Text(stringResource(Res.string.cancel))
                }
            },
            modifier = Modifier.heightIn(max = 560.dp)
        )
}