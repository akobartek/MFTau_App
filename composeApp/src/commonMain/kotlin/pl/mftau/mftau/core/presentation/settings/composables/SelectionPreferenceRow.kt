package pl.mftau.mftau.core.presentation.settings.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.selection_setting_summary
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import pl.mftau.mftau.common.presentation.composables.MultiSelectDialog

@Composable
fun SelectionPreferenceRow(
    titleId: StringResource,
    currentValue: String,
    values: List<String>,
    codes: List<String>,
    dialogImageVector: ImageVector,
    dialogTitleId: StringResource,
    onSave: (String) -> Unit,
    onClick: (() -> Unit)? = null,
) {
    var dialogVisible by rememberSaveable { mutableStateOf(false) }
    val getCorrectValueByCode = {
        val index = codes.indexOf(currentValue)
        values[if (index != -1) index else 0]
    }
    var selectedValue by remember { mutableStateOf(getCorrectValueByCode()) }

    LaunchedEffect(currentValue) {
        selectedValue = getCorrectValueByCode()
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (onClick != null) onClick.invoke()
                else dialogVisible = true
            }
            .padding(horizontal = 16.dp, vertical = 12.dp),
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(titleId),
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = stringResource(
                    Res.string.selection_setting_summary,
                    values[codes.indexOf(currentValue)]
                ),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }

    MultiSelectDialog(
        isVisible = dialogVisible,
        imageVector = dialogImageVector,
        titleId = dialogTitleId,
        currentValue = selectedValue,
        values = values,
        onSave = { onSave(codes[values.indexOf(it)]) },
        onDismiss = { dialogVisible = false },
    )
}