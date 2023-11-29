package pl.mftau.mftau.core.presentation.screens

import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.CollectionsBookmark
import androidx.compose.material.icons.outlined.Diversity3
import androidx.compose.material.icons.outlined.LockReset
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.NoAccounts
import androidx.compose.material.icons.outlined.OpenInBrowser
import androidx.compose.material.icons.outlined.PersonPin
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import pl.mftau.mftau.R
import pl.mftau.mftau.auth.domain.model.UserType
import pl.mftau.mftau.auth.presentation.AuthScreen
import pl.mftau.mftau.breviary.presentation.BreviarySelectScreen
import pl.mftau.mftau.common.presentation.components.TauAlertDialog
import pl.mftau.mftau.core.presentation.components.CommunityLogo
import pl.mftau.mftau.common.presentation.components.NoInternetDialog
import pl.mftau.mftau.common.presentation.components.NoPdfAppDialog
import pl.mftau.mftau.core.presentation.screenmodels.MainScreenModel
import pl.mftau.mftau.core.presentation.screenmodels.MainScreenModel.NoInternetAction
import pl.mftau.mftau.common.utils.getBitmapFromUri
import pl.mftau.mftau.common.utils.openPdf
import pl.mftau.mftau.common.utils.openWebsiteInChromeCustomTabsIfSupported
import pl.mftau.mftau.common.utils.safePush
import pl.mftau.mftau.common.utils.showShortToast
import pl.mftau.mftau.gospel.presentation.GospelScreen
import pl.mftau.mftau.leaders.presentation.screens.PeopleListScreen
import pl.mftau.mftau.readings.presentation.ReadingsListScreen
import pl.mftau.mftau.songbook.presentation.screens.SongBookListScreen
import pl.mftau.mftau.ui.WindowInfo
import pl.mftau.mftau.ui.rememberWindowInfo
import pl.mftau.mftau.ui.theme.mfTauFont

class MainScreen : Screen {
    @Composable
    override fun Content() {
        MainScreenContent(getScreenModel())
    }
}

@Composable
fun MainScreenContent(screenModel: MainScreenModel) {
    val windowInfo = rememberWindowInfo()
    val context = LocalContext.current
    val state by screenModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = state) {
        if (state.accountDeletedMessageVisible)
            context.showShortToast(R.string.delete_account_success)
                .also { screenModel.toggleAccountDeletedMessageVisibility() }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Row {
            ProfileOptions(screenModel)
            Spacer(modifier = Modifier.weight(1f))
            MainScreenOptions(screenModel)
        }
        if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
            CommunityLogo(Modifier.padding(top = 16.dp))
            Spacer(modifier = Modifier.height(40.dp))
            FirstButtonsRow()
            Spacer(modifier = Modifier.height(24.dp))
            SecondButtonsRow()
            AnimatedVisibility(visible = state.user?.userType == UserType.LEADER) {
                LeaderButtonRow()
            }
        } else {
            CommunityLogo(Modifier.padding(top = 4.dp))
            Spacer(modifier = Modifier.height(32.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                FirstButtonsRow()
                SecondButtonsRow()
            }
            AnimatedVisibility(visible = state.user?.userType == UserType.LEADER) {
                LeaderButtonRow()
            }
        }

        NoInternetDialog(
            isVisible = state.noInternetAction != null,
            onReconnect = {
                screenModel.hideNoInternetDialog()
                when (state.noInternetAction) {
                    NoInternetAction.RESET_PASSWORD -> screenModel.sendResetPasswordEmail()
                    NoInternetAction.DELETE_ACCOUNT -> screenModel.deleteAccount()
                    else -> {}
                }
            },
            onDismiss = screenModel::hideNoInternetDialog
        )

        TauAlertDialog(
            isVisible = state.resetPasswordDialogVisible,
            imageVector = Icons.Outlined.LockReset,
            dialogTitleId = R.string.message_sent,
            dialogTextId = R.string.reset_password_dialog_msg,
            confirmBtnTextId = R.string.ok,
            onConfirm = screenModel::toggleResetPasswordDialogVisibility,
            onDismissRequest = screenModel::toggleResetPasswordDialogVisibility
        )

        TauAlertDialog(
            isVisible = state.deleteAccountDialogVisible,
            imageVector = Icons.Outlined.NoAccounts,
            dialogTitleId = R.string.delete_account_dialog_title,
            dialogTextId = R.string.delete_account_dialog_msg,
            confirmBtnTextId = R.string.delete,
            onConfirm = {
                screenModel.toggleDeleteAccountDialogVisibility()
                screenModel.deleteAccount()
            },
            dismissBtnTextId = R.string.cancel,
            onDismissRequest = screenModel::toggleDeleteAccountDialogVisibility
        )
    }
}

@Composable
private fun ProfileOptions(screenModel: MainScreenModel) {
    val state by screenModel.state.collectAsStateWithLifecycle()
    var dropdownExpanded by rememberSaveable { mutableStateOf(false) }

    AnimatedVisibility(visible = state.user != null) {
        Box {
            Box(modifier = Modifier
                .padding(16.dp)
                .clickable { dropdownExpanded = true }) {
                ProfilePicture(photoUri = state.user?.photoUri)
            }
            DropdownMenu(
                expanded = dropdownExpanded,
                onDismissRequest = { dropdownExpanded = false },
                modifier = Modifier.defaultMinSize(minWidth = 200.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp)
                ) {
                    ProfilePicture(photoUri = state.user?.photoUri)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = state.user?.email ?: "")
                }
                HorizontalDivider()
                Column(modifier = Modifier.padding(horizontal = 4.dp)) {
                    DropdownMenuItem(
                        text = { Text(text = stringResource(id = R.string.sign_out)) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.Logout,
                                contentDescription = null
                            )
                        },
                        onClick = {
                            dropdownExpanded = false
                            screenModel.signOut()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = stringResource(id = R.string.reset_password)) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.LockReset,
                                contentDescription = null
                            )
                        },
                        onClick = {
                            dropdownExpanded = false
                            screenModel.sendResetPasswordEmail()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = stringResource(id = R.string.delete_account)) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.NoAccounts,
                                contentDescription = null
                            )
                        },
                        onClick = {
                            dropdownExpanded = false
                            screenModel.toggleDeleteAccountDialogVisibility()
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ProfilePicture(photoUri: Uri?) {
    val context = LocalContext.current
    if (photoUri != null)
        Image(
            bitmap = context.getBitmapFromUri(photoUri).asImageBitmap(),
            contentDescription = stringResource(id = R.string.cd_profile_pic)
        )
    else
        Icon(
            imageVector = Icons.Outlined.AccountCircle,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            contentDescription = stringResource(id = R.string.cd_profile_pic),
        )
}

@Composable
private fun MainScreenOptions(screenModel: MainScreenModel) {
    val navigator = LocalNavigator.currentOrThrow
    var dropdownExpanded by rememberSaveable { mutableStateOf(false) }
    val state by screenModel.state.collectAsStateWithLifecycle()

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
            if (state.user == null)
                DropdownMenuItem(
                    text = { Text(text = stringResource(id = R.string.sign_in)) },
                    onClick = {
                        dropdownExpanded = false
                        navigator.safePush(AuthScreen())
                    }
                )
            DropdownMenuItem(
                text = { Text(text = stringResource(id = R.string.settings)) },
                onClick = {
                    dropdownExpanded = false
                    navigator.safePush(SettingsScreen())
                }
            )
            DropdownMenuItem(
                text = { Text(text = stringResource(id = R.string.ask_for_pray)) },
                onClick = {
                    dropdownExpanded = false
                    navigator.safePush(EmailScreen(EmailScreen.EmailScreenType.AskForPray))
                }
            )
            DropdownMenuItem(
                text = { Text(text = stringResource(id = R.string.report_error)) },
                onClick = {
                    dropdownExpanded = false
                    navigator.safePush(EmailScreen(EmailScreen.EmailScreenType.ReportError))
                }
            )
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
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = stringResource(
                id = R.string.cd_main_screen_btn,
                buttonData.title
            ),
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = buttonData.title.uppercase(),
            color = MaterialTheme.colorScheme.primary,
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
private fun FirstButtonsRow(modifier: Modifier = Modifier) {
    val navigator = LocalNavigator.currentOrThrow
    val context = LocalContext.current
    var pdfDialogVisible by rememberSaveable { mutableStateOf(false) }

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
                onClick = { navigator.safePush(GospelScreen()) }
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

    NoPdfAppDialog(isVisible = pdfDialogVisible, onDismiss = { pdfDialogVisible = false })
}

@Composable
private fun SecondButtonsRow(modifier: Modifier = Modifier) {
    val navigator = LocalNavigator.currentOrThrow

    ButtonsRow(
        buttonsData = listOf(
            ButtonData(
                title = stringResource(id = R.string.readings),
                icon = Icons.Outlined.CollectionsBookmark,
                onClick = { navigator.safePush(ReadingsListScreen()) }
            ),
            ButtonData(
                title = stringResource(id = R.string.song_book),
                icon = ImageVector.vectorResource(id = R.drawable.ic_song_book),
                onClick = { navigator.safePush(SongBookListScreen()) }
            ),
            ButtonData(
                title = stringResource(id = R.string.breviary),
                icon = ImageVector.vectorResource(id = R.drawable.ic_breviary),
                onClick = { navigator.safePush(BreviarySelectScreen()) }
            )
        ),
        modifier = modifier
    )
}

@Composable
fun LeaderButtonRow(modifier: Modifier = Modifier) {
    val navigator = LocalNavigator.currentOrThrow

    ButtonsRow(
        buttonsData = listOf(
            ButtonData(
                title = stringResource(id = R.string.people),
                icon = Icons.Outlined.PersonPin,
                onClick = { navigator.safePush(PeopleListScreen()) }
            ),
            ButtonData(
                title = stringResource(id = R.string.meetings),
                icon = Icons.Outlined.Diversity3,
                onClick = { navigator.safePush(BreviarySelectScreen()) }
            )
        ),
        modifier = modifier.padding(top = 24.dp)
    )
}