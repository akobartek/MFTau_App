package pl.mftau.mftau.auth.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.cancel
import mftau.composeapp.generated.resources.cd_clear_field
import mftau.composeapp.generated.resources.email
import mftau.composeapp.generated.resources.email_error_invalid
import mftau.composeapp.generated.resources.email_error_no_user
import mftau.composeapp.generated.resources.email_error_user_exists
import mftau.composeapp.generated.resources.empty_field_error
import mftau.composeapp.generated.resources.forgot_password
import mftau.composeapp.generated.resources.hide_password
import mftau.composeapp.generated.resources.message_sent
import mftau.composeapp.generated.resources.ok
import mftau.composeapp.generated.resources.password
import mftau.composeapp.generated.resources.password_error_empty
import mftau.composeapp.generated.resources.password_error_invalid
import mftau.composeapp.generated.resources.password_error_too_short
import mftau.composeapp.generated.resources.password_error_wrong
import mftau.composeapp.generated.resources.show_password
import mftau.composeapp.generated.resources.sign_in
import mftau.composeapp.generated.resources.sign_in_error
import mftau.composeapp.generated.resources.sign_up
import mftau.composeapp.generated.resources.sign_up_error
import mftau.composeapp.generated.resources.sign_up_successful_dialog_message
import mftau.composeapp.generated.resources.sign_up_successful_dialog_title
import mftau.composeapp.generated.resources.verify_email_dialog_message
import mftau.composeapp.generated.resources.verify_email_dialog_title
import mftau.composeapp.generated.resources.verify_email_send_again
import org.jetbrains.compose.resources.stringResource
import pl.mftau.mftau.common.presentation.autofill
import pl.mftau.mftau.common.presentation.components.NoInternetDialog
import pl.mftau.mftau.common.presentation.components.TauAlertDialog
import pl.mftau.mftau.common.presentation.components.TauCenteredTopBar
import pl.mftau.mftau.common.utils.showShortToast
import pl.mftau.mftau.core.presentation.components.CommunityLogo


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AuthScreenContent(
    state: AuthScreenState,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val (emailRef, passwordRef) = remember { FocusRequester.createRefs() }
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    LaunchedEffect(key1 = state) {
        scope.launch {
            if (state.isSignedIn) {
                context.showShortToast(R.string.signed_in)
                navigator.safePop(AuthScreen.KEY)
            }
            if (state.signInErrorSnackbarVisible) {
                screenModel.signInErrorShowed()
                snackbarHostState.showSnackbar(
                    message = stringResource(Res.string.sign_in_error),
                    withDismissAction = true
                )
            }
            if (state.signUpErrorSnackbarVisible) {
                screenModel.signUpErrorShowed()
                snackbarHostState.showSnackbar(
                    message = stringResource(Res.string.sign_up_error),
                    withDismissAction = true
                )
            }
            if (state.forgottenPasswordDialogSuccess) {
                screenModel.toggleForgottenPasswordSuccessVisibility()
                snackbarHostState.showSnackbar(
                    message = stringResource(Res.string.message_sent),
                    withDismissAction = true
                )
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TauCenteredTopBar(
                title = "",
                onNavClick = { navigator.safePop(AuthScreen.KEY) }
            )
        },
        modifier = Modifier.clickable(
            interactionSource = interactionSource,
            indication = null
        ) { focusManager.clearFocus(true) }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 48.dp)
        ) {
            CommunityLogo(modifier = Modifier
                .padding(top = 8.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) { focusManager.clearFocus(true) })
            OutlinedTextField(
                value = state.email,
                onValueChange = screenModel::updateEmail,
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
                        IconButton(onClick = { screenModel.updateEmail("") }) {
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
                    .padding(top = 24.dp)
                    .focusRequester(emailRef)
                    .focusProperties { next = passwordRef }
                    .autofill(
                        autofillTypes = listOf(AutofillType.EmailAddress),
                        onFill = { screenModel.updateEmail(it) },
                    )
            )
            OutlinedTextField(
                value = state.password,
                onValueChange = screenModel::updatePassword,
                singleLine = true,
                label = { Text(text = stringResource(Res.string.password)) },
                visualTransformation = if (state.passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                trailingIcon = {
                    IconButton(onClick = screenModel::updatePasswordHidden) {
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
                    .padding(top = 8.dp)
                    .focusRequester(passwordRef)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    focusManager.clearFocus(true)
                    screenModel.signIn()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(Res.string.sign_in))
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(
                onClick = {
                    focusManager.clearFocus(true)
                    screenModel.signUp()
                },
                modifier = Modifier.fillMaxWidth(0.75f)
            ) {
                Text(text = stringResource(Res.string.sign_up))
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(
                onClick = screenModel::toggleForgottenPasswordDialogVisibility,
                modifier = Modifier.fillMaxWidth(0.75f)
            ) {
                Text(text = stringResource(Res.string.forgot_password))
            }
        }

        if (state.forgottenPasswordDialogVisible)
            ResetPasswordDialog(
                onReset = screenModel::sendResetPasswordEmail,
                onCancel = screenModel::toggleForgottenPasswordDialogVisibility,
                isError = state.forgottenPasswordDialogError
            )

        NoInternetDialog(
            isVisible = state.noInternetAction != null,
            onReconnect = {
                screenModel.hideNoInternetDialog()
                when (state.noInternetAction) {
                    NoInternetAction.SIGN_IN -> screenModel.signIn()
                    NoInternetAction.SIGN_UP -> screenModel.signUp()
                    NoInternetAction.RESET_PASSWORD -> screenModel.toggleForgottenPasswordDialogVisibility()
                    else -> {}
                }
            },
            onDismiss = screenModel::hideNoInternetDialog
        )

        TauAlertDialog(
            isVisible = state.isSignedUpDialogVisible,
            imageVector = Icons.Outlined.ManageAccounts,
            dialogTitleId = Res.string.sign_up_successful_dialog_title,
            dialogTextId = Res.string.sign_up_successful_dialog_message,
            confirmBtnTextId = Res.string.ok,
            onConfirm = screenModel::toggleSignUpSuccessVisibility,
            dismissible = false,
        )

        TauAlertDialog(
            isVisible = state.emailUnverifiedDialogVisible,
            imageVector = Icons.Outlined.ErrorOutline,
            dialogTitleId = Res.string.verify_email_dialog_title,
            dialogTextId = Res.string.verify_email_dialog_message,
            confirmBtnTextId = Res.string.verify_email_send_again,
            onConfirm = { screenModel.toggleEmailUnverifiedDialogVisibility(true) },
            dismissible = false,
            dismissBtnTextId = Res.string.cancel,
            onDismissRequest = { screenModel.toggleEmailUnverifiedDialogVisibility(false) }
        )
    }
}