package pl.mftau.mftau.core.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LockReset
import androidx.compose.material.icons.outlined.NoAccounts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.cancel
import mftau.composeapp.generated.resources.delete
import mftau.composeapp.generated.resources.delete_account_dialog_msg
import mftau.composeapp.generated.resources.delete_account_dialog_title
import mftau.composeapp.generated.resources.message_sent
import mftau.composeapp.generated.resources.ok
import mftau.composeapp.generated.resources.reset_password_dialog_msg
import org.koin.compose.koinInject
import pl.mftau.mftau.Screen
import pl.mftau.mftau.auth.domain.model.UserType
import pl.mftau.mftau.common.presentation.composables.CommunityLogo
import pl.mftau.mftau.common.presentation.composables.NoInternetDialog
import pl.mftau.mftau.common.presentation.composables.TauAlertDialog
import pl.mftau.mftau.core.presentation.home.components.FirstButtonsRow
import pl.mftau.mftau.core.presentation.home.components.HomeScreenOptions
import pl.mftau.mftau.core.presentation.home.components.LeaderButtonRow
import pl.mftau.mftau.core.presentation.home.components.ProfileOptions
import pl.mftau.mftau.core.presentation.home.components.SecondButtonsRow


@Composable
fun HomeScreen(
    navigate: (Screen) -> Unit,
    viewModel: HomeViewModel = koinInject(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeScreenContent(
        state = state,
        navigate = navigate,
        signOut = viewModel::signOut,
        resetPassword = viewModel::sendResetPasswordEmail,
        deleteAccount = viewModel::deleteAccount,
        hideNoInternetDialog = viewModel::hideNoInternetDialog,
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreenContent(
    state: HomeScreenState,
    navigate: (Screen) -> Unit,
    signOut: () -> Unit,
    resetPassword: () -> Unit,
    deleteAccount: () -> Unit,
    hideNoInternetDialog: () -> Unit,
) {
//    val windowInfo = currentWindowAdaptiveInfo()  TODO() -> Use this value in the future instead of size - currently it's not yet available
    val size =
        with(LocalDensity.current) { LocalWindowInfo.current.containerSize.height.toDp() }

    var resetPasswordDialogVisible by remember { mutableStateOf(false) }
    var deleteAccountDialogVisible by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Row {
            ProfileOptions(
                user = state.user,
                signOut = signOut,
                resetPassword = { resetPasswordDialogVisible = true },
                deleteAccount = { deleteAccountDialogVisible = true },
            )
            Spacer(modifier = Modifier.weight(1f))
            HomeScreenOptions(
                user = state.user,
                navigate = navigate,
            )
        }
        if (size < 480.dp) {
            CommunityLogo()
            Spacer(modifier = Modifier.height(24.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                FirstButtonsRow(navigate = navigate)
                SecondButtonsRow(navigate = navigate)
            }
        } else {
            CommunityLogo(Modifier.padding(top = 16.dp))
            Spacer(modifier = Modifier.height(32.dp))
            FirstButtonsRow(navigate = navigate)
            Spacer(modifier = Modifier.height(16.dp))
            SecondButtonsRow(navigate = navigate)
        }
        AnimatedVisibility(visible = state.user?.userType == UserType.LEADER) {
            LeaderButtonRow(navigate = navigate)
        }

        NoInternetDialog(
            isVisible = state.resetPasswordFailed,
            onReconnect = {
                hideNoInternetDialog()
                resetPassword()
            },
            onDismiss = hideNoInternetDialog,
        )

        TauAlertDialog(
            isVisible = resetPasswordDialogVisible,
            imageVector = Icons.Outlined.LockReset,
            dialogTitleId = Res.string.message_sent,
            dialogTextId = Res.string.reset_password_dialog_msg,
            confirmBtnTextId = Res.string.ok,
            onConfirm = {
                resetPassword()
                resetPasswordDialogVisible = false
            },
            onDismissRequest = { resetPasswordDialogVisible = false },
        )

        TauAlertDialog(
            isVisible = deleteAccountDialogVisible,
            imageVector = Icons.Outlined.NoAccounts,
            dialogTitleId = Res.string.delete_account_dialog_title,
            dialogTextId = Res.string.delete_account_dialog_msg,
            confirmBtnTextId = Res.string.delete,
            onConfirm = {
                deleteAccount()
                deleteAccountDialogVisible = false
            },
            dismissBtnTextId = Res.string.cancel,
            onDismissRequest = { deleteAccountDialogVisible = false },
        )
    }
}