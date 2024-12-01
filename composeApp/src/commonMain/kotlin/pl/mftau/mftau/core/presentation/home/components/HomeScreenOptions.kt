package pl.mftau.mftau.core.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.ask_for_pray
import mftau.composeapp.generated.resources.cd_more_options_btn
import mftau.composeapp.generated.resources.report_error
import mftau.composeapp.generated.resources.settings
import mftau.composeapp.generated.resources.sign_in
import org.jetbrains.compose.resources.stringResource
import pl.mftau.mftau.Screen
import pl.mftau.mftau.auth.domain.model.User

private const val EMAIL_URI = "mailto:"
private const val PRAY_URI =
    "${EMAIL_URI}modlitwa@mftau.pl?subject=Prośba o modlitwę"
private const val ERROR_URI =
    "${EMAIL_URI}sokolowskijbartek@gmail.com?subject=Błąd w aplikacji MF Tau"

@Composable
fun HomeScreenOptions(
    user: User?,
    navigate: (Screen) -> Unit,
) {
    val uriHandler = LocalUriHandler.current
    var dropdownExpanded by rememberSaveable { mutableStateOf(false) }

    Box {
        Icon(
            imageVector = Icons.Outlined.MoreVert,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = stringResource(Res.string.cd_more_options_btn),
            modifier = Modifier
                .padding(16.dp)
                .clickable { dropdownExpanded = true }
        )
        DropdownMenu(
            expanded = dropdownExpanded,
            onDismissRequest = { dropdownExpanded = false },
            modifier = Modifier.defaultMinSize(minWidth = 200.dp)
        ) {
            if (user == null)
                DropdownMenuItem(
                    text = { Text(text = stringResource(Res.string.sign_in)) },
                    onClick = {
                        dropdownExpanded = false
                        navigate(Screen.Auth)
                    }
                )
            DropdownMenuItem(
                text = { Text(text = stringResource(Res.string.settings)) },
                onClick = {
                    dropdownExpanded = false
                    navigate(Screen.Settings)
                }
            )
            DropdownMenuItem(
                text = { Text(text = stringResource(Res.string.ask_for_pray)) },
                onClick = {
                    dropdownExpanded = false
                    uriHandler.openUri(PRAY_URI)
                }
            )
            DropdownMenuItem(
                text = { Text(text = stringResource(Res.string.report_error)) },
                onClick = {
                    dropdownExpanded = false
                    uriHandler.openUri(ERROR_URI)
                }
            )
        }
    }
}
