package pl.mftau.mftau.auth.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.ManageAccounts
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.cancel
import mftau.composeapp.generated.resources.cd_clear_field
import mftau.composeapp.generated.resources.email
import mftau.composeapp.generated.resources.email_error_invalid
import mftau.composeapp.generated.resources.email_error_no_user
import mftau.composeapp.generated.resources.email_error_user_exists
import mftau.composeapp.generated.resources.forgot_password
import mftau.composeapp.generated.resources.hide_password
import mftau.composeapp.generated.resources.ok
import mftau.composeapp.generated.resources.password
import mftau.composeapp.generated.resources.password_error_empty
import mftau.composeapp.generated.resources.password_error_invalid
import mftau.composeapp.generated.resources.password_error_too_short
import mftau.composeapp.generated.resources.password_error_wrong
import mftau.composeapp.generated.resources.show_password
import mftau.composeapp.generated.resources.sign_in
import mftau.composeapp.generated.resources.sign_up
import mftau.composeapp.generated.resources.sign_up_successful_dialog_message
import mftau.composeapp.generated.resources.sign_up_successful_dialog_title
import mftau.composeapp.generated.resources.verify_email_dialog_message
import mftau.composeapp.generated.resources.verify_email_dialog_title
import mftau.composeapp.generated.resources.verify_email_send_again
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import pl.mftau.mftau.auth.presentation.composables.ResetPasswordDialog
import pl.mftau.mftau.common.presentation.autofill
import pl.mftau.mftau.common.presentation.composables.CommunityLogo
import pl.mftau.mftau.common.presentation.composables.NoInternetDialog
import pl.mftau.mftau.common.presentation.composables.TauAlertDialog
import pl.mftau.mftau.common.presentation.composables.TauCenteredTopBar

@Composable
fun AuthScreen(
    navigateUp: () -> Unit,
    viewModel: AuthViewModel = koinInject(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    AuthScreenContent(
        navigateUp = navigateUp,
        state = state,
        updateEmail = viewModel::updateEmail,
        updatePassword = viewModel::updatePassword,
        updatePasswordHidden = viewModel::updatePasswordHidden,
        signIn = viewModel::signIn,
        signUp = viewModel::signUp,
        toggleSignUpSuccessVisibility = viewModel::toggleSignUpSuccessVisibility,
        sendResetPasswordEmail = viewModel::sendResetPasswordEmail,
        toggleForgottenPasswordDialogVisibility = viewModel::toggleForgottenPasswordDialogVisibility,
        hideNoInternetDialog = viewModel::hideNoInternetDialog,
        toggleEmailUnverifiedDialogVisibility = viewModel::toggleEmailUnverifiedDialogVisibility,
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AuthScreenContent(
    navigateUp: () -> Unit,
    state: AuthScreenState,
    updateEmail: (String) -> Unit,
    updatePassword: (String) -> Unit,
    updatePasswordHidden: () -> Unit,
    signIn: () -> Unit,
    signUp: () -> Unit,
    toggleSignUpSuccessVisibility: () -> Unit,
    sendResetPasswordEmail: (String) -> Unit,
    toggleForgottenPasswordDialogVisibility: () -> Unit,
    hideNoInternetDialog: () -> Unit,
    toggleEmailUnverifiedDialogVisibility: (Boolean) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val (emailRef, passwordRef) = remember { FocusRequester.createRefs() }
    val interactionSource = remember { MutableInteractionSource() }

    LaunchedEffect(state) {
        if (state.isSignedIn)
            navigateUp()
    }

    Scaffold(
        topBar = {
            TauCenteredTopBar(
                title = "",
                onNavClick = navigateUp,
            )
        },
        modifier = Modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
        ) { focusManager.clearFocus(true) }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 48.dp),
        ) {
            CommunityLogo(modifier = Modifier
                .padding(top = 8.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) { focusManager.clearFocus(true) })

            OutlinedTextField(
                value = state.email,
                onValueChange = updateEmail,
                singleLine = true,
                label = { Text(text = stringResource(Res.string.email)) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                }),
                trailingIcon = {
                    if (state.email.isNotBlank())
                        IconButton(onClick = { updateEmail("") }) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = stringResource(Res.string.cd_clear_field)
                            )
                        }
                },
                isError = state.emailError != null,
                supportingText = if (state.emailError != null) {
                    {
                        Text(
                            text = stringResource(
                                when (state.emailError) {
                                    EmailErrorType.INVALID -> Res.string.email_error_invalid
                                    EmailErrorType.NO_USER -> Res.string.email_error_no_user
                                    EmailErrorType.USER_EXISTS -> Res.string.email_error_user_exists
                                }
                            )
                        )
                    }
                } else null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .focusRequester(emailRef)
                    .focusProperties { next = passwordRef }
                    .autofill(
                        autofillTypes = listOf(AutofillType.EmailAddress),
                        onFill = updateEmail,
                    )
            )

            OutlinedTextField(
                value = state.password,
                onValueChange = updatePassword,
                singleLine = true,
                label = { Text(text = stringResource(Res.string.password)) },
                visualTransformation = if (state.passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                trailingIcon = {
                    IconButton(onClick = updatePasswordHidden) {
                        if (state.passwordHidden)
                            Icon(
                                imageVector = Icons.Filled.Visibility,
                                contentDescription = stringResource(Res.string.show_password)
                            )
                        else
                            Icon(
                                imageVector = Icons.Filled.VisibilityOff,
                                contentDescription = stringResource(Res.string.hide_password)
                            )
                    }
                },
                isError = state.passwordError != null,
                supportingText = if (state.passwordError != null) {
                    {
                        Text(
                            text = stringResource(
                                when (state.passwordError) {
                                    PasswordErrorType.EMPTY -> Res.string.password_error_empty
                                    PasswordErrorType.TOO_SHORT -> Res.string.password_error_too_short
                                    PasswordErrorType.WRONG -> Res.string.password_error_wrong
                                    PasswordErrorType.INVALID -> Res.string.password_error_invalid
                                }
                            )
                        )
                    }
                } else null,
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(passwordRef)
            )

            Button(
                onClick = {
                    focusManager.clearFocus(true)
                    signIn()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(Res.string.sign_in))
            }

            OutlinedButton(
                onClick = {
                    focusManager.clearFocus(true)
                    signUp()
                },
                modifier = Modifier.fillMaxWidth(0.75f)
            ) {
                Text(text = stringResource(Res.string.sign_up))
            }

            OutlinedButton(
                onClick = toggleForgottenPasswordDialogVisibility,
                modifier = Modifier.fillMaxWidth(0.75f)
            ) {
                Text(text = stringResource(Res.string.forgot_password))
            }
        }

        TauAlertDialog(
            isVisible = state.isSignedUpDialogVisible,
            imageVector = Icons.Outlined.ManageAccounts,
            dialogTitleId = Res.string.sign_up_successful_dialog_title,
            dialogTextId = Res.string.sign_up_successful_dialog_message,
            confirmBtnTextId = Res.string.ok,
            onConfirm = toggleSignUpSuccessVisibility,
            dismissible = false,
        )

        if (state.forgottenPasswordDialogVisible)
            ResetPasswordDialog(
                onReset = sendResetPasswordEmail,
                onCancel = toggleForgottenPasswordDialogVisibility,
                isError = state.forgottenPasswordDialogError,
            )

        NoInternetDialog(
            isVisible = state.noInternetAction != null,
            onReconnect = {
                hideNoInternetDialog()
                when (state.noInternetAction) {
                    NoInternetAction.SIGN_IN -> signIn()
                    NoInternetAction.SIGN_UP -> signUp()
                    NoInternetAction.RESET_PASSWORD -> toggleForgottenPasswordDialogVisibility()
                    else -> {}
                }
            },
            onDismiss = hideNoInternetDialog,
        )

        TauAlertDialog(
            isVisible = state.emailUnverifiedDialogVisible,
            imageVector = Icons.Outlined.ErrorOutline,
            dialogTitleId = Res.string.verify_email_dialog_title,
            dialogTextId = Res.string.verify_email_dialog_message,
            confirmBtnTextId = Res.string.verify_email_send_again,
            onConfirm = { toggleEmailUnverifiedDialogVisibility(true) },
            dismissible = false,
            dismissBtnTextId = Res.string.cancel,
            onDismissRequest = { toggleEmailUnverifiedDialogVisibility(false) },
        )
    }
}