package pl.mftau.mftau.core.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CollectionsBookmark
import androidx.compose.material.icons.outlined.Diversity3
import androidx.compose.material.icons.outlined.OpenInBrowser
import androidx.compose.material.icons.outlined.PersonPin
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.breviary
import mftau.composeapp.generated.resources.cd_main_screen_btn
import mftau.composeapp.generated.resources.gospel
import mftau.composeapp.generated.resources.ic_breviary
import mftau.composeapp.generated.resources.ic_gospel
import mftau.composeapp.generated.resources.ic_song_book
import mftau.composeapp.generated.resources.ic_statute
import mftau.composeapp.generated.resources.meetings
import mftau.composeapp.generated.resources.mftau_website
import mftau.composeapp.generated.resources.people
import mftau.composeapp.generated.resources.readings
import mftau.composeapp.generated.resources.song_book
import mftau.composeapp.generated.resources.statute
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.koinInject
import pl.mftau.mftau.Screen
import pl.mftau.mftau.common.presentation.PdfOpener
import pl.mftau.mftau.common.presentation.composables.NoPdfAppDialog
import pl.mftau.mftau.common.presentation.launchPdf
import pl.mftau.mftau.ui.theme.mfTauFont

private const val COMMUNITY_WEBSITE = "https://mftau.pl/"
private const val STATUTE_FILE_NAME = "statut.pdf"

private data class HomeButtonData(
    val title: String,
    val icon: ImageVector,
    val onClick: () -> Unit = {}
)

@Composable
private fun HomeScreenButton(
    buttonData: HomeButtonData,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.defaultMinSize(minWidth = 80.dp),
    ) {
        Icon(
            imageVector = buttonData.icon,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = stringResource(Res.string.cd_main_screen_btn, buttonData.title),
            modifier = Modifier.size(48.dp),
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = buttonData.title.uppercase(),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 16.sp,
            fontFamily = mfTauFont(),
        )
    }
}

@Composable
private fun ButtonsRow(
    buttonsData: List<HomeButtonData>,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier,
    ) {
        buttonsData.forEach { buttonData ->
            HomeScreenButton(
                buttonData = buttonData,
                modifier = Modifier.clickable { buttonData.onClick() },
            )
        }
    }
}

@Composable
fun FirstButtonsRow(
    modifier: Modifier = Modifier,
    navigate: (Screen) -> Unit,
    pdfOpener: PdfOpener = koinInject(),
) {
    val scope = rememberCoroutineScope()
    val uriHandler = LocalUriHandler.current
    var pdfDialogVisible by rememberSaveable { mutableStateOf(false) }

    ButtonsRow(
        buttonsData = listOf(
            HomeButtonData(
                title = stringResource(Res.string.mftau_website),
                icon = Icons.Outlined.OpenInBrowser,
                onClick = { uriHandler.openUri(COMMUNITY_WEBSITE) },
            ),
            HomeButtonData(
                title = stringResource(Res.string.gospel),
                icon = vectorResource(Res.drawable.ic_gospel),
                onClick = { navigate(Screen.Gospel) },
            ),
            HomeButtonData(
                title = stringResource(Res.string.statute),
                icon = vectorResource(Res.drawable.ic_statute),
                onClick = {
                    scope.launchPdf(
                        pdfOpener = pdfOpener,
                        fileName = STATUTE_FILE_NAME,
                        onFailure = { pdfDialogVisible = true },
                    )
                },
            )
        ),
        modifier = modifier,
    )

    NoPdfAppDialog(isVisible = pdfDialogVisible, onDismiss = { pdfDialogVisible = false })
}

@Composable
fun SecondButtonsRow(
    modifier: Modifier = Modifier,
    navigate: (Screen) -> Unit,
) {
    ButtonsRow(
        buttonsData = listOf(
            HomeButtonData(
                title = stringResource(Res.string.readings),
                icon = Icons.Outlined.CollectionsBookmark,
                onClick = { navigate(Screen.Readings) },
            ),
            HomeButtonData(
                title = stringResource(Res.string.song_book),
                icon = vectorResource(Res.drawable.ic_song_book),
                onClick = { navigate(Screen.SongBook) },
            ),
            HomeButtonData(
                title = stringResource(Res.string.breviary),
                icon = vectorResource(Res.drawable.ic_breviary),
                onClick = { navigate(Screen.BreviarySelect) },
            )
        ),
        modifier = modifier,
    )
}

@Composable
fun LeaderButtonRow(
    modifier: Modifier = Modifier,
    navigate: (Screen) -> Unit,
) {
    ButtonsRow(
        buttonsData = listOf(
            HomeButtonData(
                title = stringResource(Res.string.people),
                icon = Icons.Outlined.PersonPin,
                onClick = { navigate(Screen.LeadersPeople) },
            ),
            HomeButtonData(
                title = stringResource(Res.string.meetings),
                icon = Icons.Outlined.Diversity3,
                onClick = { navigate(Screen.LeadersMeetings) },
            )
        ),
        modifier = modifier.padding(top = 16.dp)
    )
}