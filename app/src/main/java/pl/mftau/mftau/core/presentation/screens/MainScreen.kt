package pl.mftau.mftau.core.presentation.screens

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import pl.mftau.mftau.R
import pl.mftau.mftau.breviary.presentation.BreviarySelectScreen
import pl.mftau.mftau.core.presentation.components.BasicAlertDialog
import pl.mftau.mftau.core.presentation.components.CommunityLogo
import pl.mftau.mftau.core.utils.openPdf
import pl.mftau.mftau.core.utils.openWebsiteInChromeCustomTabsIfSupported
import pl.mftau.mftau.gospel.presentation.GospelScreen
import pl.mftau.mftau.prayers.presentation.PrayersListScreen
import pl.mftau.mftau.songbook.presentation.SongsListScreen
import pl.mftau.mftau.ui.WindowInfo
import pl.mftau.mftau.ui.rememberWindowInfo
import pl.mftau.mftau.ui.theme.MFTauTheme
import pl.mftau.mftau.ui.theme.mfTauFont

class MainScreen : Screen {
    @Composable
    override fun Content() {
        MainScreenLayout()
    }
}

@Composable
private fun MainScreenLayout(
    modifier: Modifier = Modifier
) {
    val navigator = LocalNavigator.currentOrThrow
    var dropdownExpanded by remember { mutableStateOf(false) }
    val windowInfo = rememberWindowInfo()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
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
                        onClick = {
                            dropdownExpanded = false
                            navigator.push(AuthScreen())
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = stringResource(id = R.string.settings)) },
                        onClick = {
                            dropdownExpanded = false
                            navigator.push(SettingsScreen())
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = stringResource(id = R.string.ask_for_pray)) },
                        onClick = {
                            dropdownExpanded = false
                            navigator.push(EmailScreen(EmailScreen.EmailScreenType.AskForPray))
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = stringResource(id = R.string.report_error)) },
                        onClick = {
                            dropdownExpanded = false
                            navigator.push(EmailScreen(EmailScreen.EmailScreenType.ReportError))
                        }
                    )
                }
            }
        }
        if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
            CommunityLogo(modifier.padding(top = 16.dp))
            Spacer(modifier = Modifier.height(40.dp))

            FirstButtonsRow()
            Spacer(modifier = Modifier.height(24.dp))
            SecondButtonsRow()
        } else {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                CommunityLogo(modifier.padding(top = 4.dp))
                Spacer(modifier = Modifier.height(32.dp))

                FirstButtonsRow()
                SecondButtonsRow()
            }
        }
    }
}

private data class ButtonData(
    val title: String,
    val icon: ImageVector,
    val onClick: () -> Unit = {}
)

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
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        buttonsData.forEach { buttonData ->
            MainScreenButton(
                buttonData = buttonData,
                modifier = Modifier.clickable { buttonData.onClick() }
            )
        }
    }
}

@Composable
fun FirstButtonsRow(
    modifier: Modifier = Modifier
) {
    val navigator = LocalNavigator.currentOrThrow
    val context = LocalContext.current
    var pdfDialogVisible by remember { mutableStateOf(false) }

    ButtonsRow(
        buttonsData = listOf(
            ButtonData(
                title = stringResource(id = R.string.mftau_website),
                icon = Icons.Outlined.OpenInBrowser,
                onClick = {
                    context.openWebsiteInChromeCustomTabsIfSupported("https://mftau.pl/")
                }
            ),
            ButtonData(
                title = stringResource(id = R.string.gospel),
                icon = ImageVector.vectorResource(id = R.drawable.ic_gospel),
                onClick = { navigator.push(GospelScreen()) }
            ),
            ButtonData(
                title = stringResource(id = R.string.statute),
                icon = ImageVector.vectorResource(id = R.drawable.ic_statute),
                onClick = {
                    if (!context.openPdf("statut.pdf"))
                        pdfDialogVisible = true
                }
            )
        ),
        modifier = modifier
    )
    if (pdfDialogVisible)
        BasicAlertDialog(
            dialogTitleId = R.string.no_pdf_viewer_dialog_title,
            dialogTextId = R.string.no_pdf_viewer_dialog_msg,
            confirmBtnTextId = R.string.search,
            onConfirmation = {
                pdfDialogVisible = false
                context.openWebsiteInChromeCustomTabsIfSupported("https://play.google.com/store/search?q=pdf")
            },
            dismissBtnTextId = R.string.cancel,
            onDismissRequest = { pdfDialogVisible = false }
        )
}

@Composable
fun SecondButtonsRow(
    modifier: Modifier = Modifier
) {
    val navigator = LocalNavigator.currentOrThrow

    ButtonsRow(
        buttonsData = listOf(
            ButtonData(
                title = stringResource(id = R.string.prayers),
                icon = ImageVector.vectorResource(id = R.drawable.ic_pray),
                onClick = { navigator.push(PrayersListScreen()) }
            ),
            ButtonData(
                title = stringResource(id = R.string.song_book),
                icon = Icons.Outlined.LibraryMusic,
                onClick = { navigator.push(SongsListScreen()) }
            ),
            ButtonData(
                title = stringResource(id = R.string.breviary),
                icon = ImageVector.vectorResource(id = R.drawable.ic_breviary),
                onClick = { navigator.push(BreviarySelectScreen()) }
            )
        ),
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MFTauTheme(dynamicColor = false) {
        MainScreenLayout()
    }
}

@Preview(
    showBackground = true,
    device = "spec:id=reference_phone,shape=Normal,width=891,height=411,unit=dp,dpi=420"
)
@Composable
fun DarkMainScreenPreview() {
    MFTauTheme(dynamicColor = false, darkTheme = false) {
        MainScreenLayout()
    }
}