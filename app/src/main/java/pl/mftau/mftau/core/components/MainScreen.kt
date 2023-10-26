package pl.mftau.mftau.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LibraryMusic
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.OpenInBrowser
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.mftau.mftau.R
import pl.mftau.mftau.ui.WindowInfo
import pl.mftau.mftau.ui.rememberWindowInfo
import pl.mftau.mftau.ui.theme.MFTauTheme
import pl.mftau.mftau.ui.theme.mfTauFont


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MFTauTheme(dynamicColor = false) {
        MainScreen()
    }
}

@Preview(showBackground = true, device = "spec:id=reference_phone,shape=Normal,width=891,height=411,unit=dp,dpi=420")
@Composable
fun DarkMainScreenPreview() {
    MFTauTheme(dynamicColor = false, darkTheme = false) {
        MainScreen()
    }
}

private data class ButtonData(
    val title: String,
    val icon: ImageVector
)

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    var dropdownExpanded by remember { mutableStateOf(false) }
    val windowInfo = rememberWindowInfo()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row {
            Spacer(modifier = Modifier.weight(1f))
            Box {
                Icon(
                    imageVector = Icons.Outlined.MoreVert,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    contentDescription = stringResource(id = R.string.cd_more_options_btn),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { dropdownExpanded = true }
                )
                DropdownMenu(
                    expanded = dropdownExpanded,
                    onDismissRequest = { dropdownExpanded = false },
                    modifier = Modifier.defaultMinSize(minWidth = 200.dp)
                ) {
                    // TODO() -> CHANGE STRING IF USER LOGGED IN
                    DropdownMenuItem(
                        text = { Text(text = stringResource(id = R.string.sign_in)) },
                        onClick = { /*TODO*/ }
                    )
                    DropdownMenuItem(
                        text = { Text(text = stringResource(id = R.string.settings)) },
                        onClick = { /*TODO*/ }
                    )
                    DropdownMenuItem(
                        text = { Text(text = stringResource(id = R.string.ask_for_pray)) },
                        onClick = { /*TODO*/ }
                    )
                    DropdownMenuItem(
                        text = { Text(text = stringResource(id = R.string.report_error)) },
                        onClick = { /*TODO*/ }
                    )
                }
            }
        }
        CommunityLogo(modifier.padding(top = 4.dp))
        Spacer(modifier = Modifier.height(32.dp))
        if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
            FirstButtonsRow(onItemClick = { /*TODO*/ })
            Spacer(modifier = Modifier.height(24.dp))
            SecondButtonsRow(onItemClick = { /*TODO*/ })
        } else {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                FirstButtonsRow(onItemClick = { /*TODO*/ })
                SecondButtonsRow(onItemClick = { /*TODO*/ })
            }
        }
    }
}

@Composable
private fun MainScreenButton(
    buttonData: ButtonData,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.defaultMinSize(minWidth = 80.dp)
    ) {
        Icon(
            imageVector = buttonData.icon,
            tint = MaterialTheme.colorScheme.secondary,
            contentDescription = stringResource(id = R.string.cd_main_screen_btn, buttonData.title),
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = buttonData.title.uppercase(),
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 16.sp,
            fontFamily = mfTauFont
        )
    }
}

@Composable
private fun ButtonsRow(
    buttonsData: List<ButtonData>,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        buttonsData.forEach { buttonData ->
            MainScreenButton(
                buttonData = buttonData,
                modifier = Modifier.clickable { onItemClick() }
            )
        }
    }
}

@Composable
fun FirstButtonsRow(
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ButtonsRow(
        buttonsData = listOf(
            ButtonData(
                stringResource(id = R.string.mftau_website),
                Icons.Outlined.OpenInBrowser
            ),
            ButtonData(
                stringResource(id = R.string.song_book),
                Icons.Outlined.LibraryMusic
            ),
            ButtonData(
                stringResource(id = R.string.prayers),
                ImageVector.vectorResource(id = R.drawable.ic_pray)
            )
        ),
        onItemClick = { onItemClick() },
        modifier = modifier
    )
}

@Composable
fun SecondButtonsRow(
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ButtonsRow(
        buttonsData = listOf(
            ButtonData(
                stringResource(id = R.string.statute),
                ImageVector.vectorResource(id = R.drawable.ic_statute)
            ),
            ButtonData(
                stringResource(id = R.string.gospel),
                ImageVector.vectorResource(id = R.drawable.ic_gospel)
            ),
            ButtonData(
                stringResource(id = R.string.breviary),
                ImageVector.vectorResource(id = R.drawable.ic_breviary)
            )
        ),
        onItemClick = { onItemClick() },
        modifier = modifier
    )
}