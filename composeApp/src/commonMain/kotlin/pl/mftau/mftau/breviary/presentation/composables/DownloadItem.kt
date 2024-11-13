package pl.mftau.mftau.breviary.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.cd_download_error
import mftau.composeapp.generated.resources.cd_download_success
import org.jetbrains.compose.resources.stringResource

@Composable
fun DownloadItem(name: String, value: String?) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth(0.75f)
            .padding(vertical = 4.dp)
    ) {
        Text(text = name, fontSize = 16.sp)
        when {
            value == null -> Icon(
                imageVector = Icons.Default.ErrorOutline,
                contentDescription = stringResource(Res.string.cd_download_error),
                tint = MaterialTheme.colorScheme.error,
            )

            value.isBlank() -> CircularProgressIndicator(modifier = Modifier.size(24.dp))

            else -> Icon(
                imageVector = Icons.Default.Check,
                contentDescription = stringResource(Res.string.cd_download_success, name),
            )
        }
    }
}