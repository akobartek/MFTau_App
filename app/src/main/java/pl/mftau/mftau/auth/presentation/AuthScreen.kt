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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.launch
import pl.mftau.mftau.R
import pl.mftau.mftau.core.presentation.components.CommunityLogo
import pl.mftau.mftau.core.presentation.components.TauCenteredTopBar
import pl.mftau.mftau.auth.presentation.AuthScreenModel.EmailErrorType
import pl.mftau.mftau.auth.presentation.AuthScreenModel.PasswordErrorType
import pl.mftau.mftau.auth.presentation.AuthScreenModel.NoInternetAction
import pl.mftau.mftau.core.presentation.components.TauAlertDialog
import pl.mftau.mftau.core.presentation.components.NoInternetDialog
import pl.mftau.mftau.core.utils.safePop
import pl.mftau.mftau.core.utils.showShortToast

class AuthScreen : Screen {
    override val key: ScreenKey
        get() = KEY

    @Composable
    override fun Content() {
        AuthScreenContent(screenModel = getScreenModel())
    }

    companion object {
        const val KEY = "AuthScreen"
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AuthScreenContent(screenModel: AuthScreenModel) {
    val navigator = LocalNavigator.currentOrThrow
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val (emailRef, passwordRef) = remember { FocusRequester.createRefs() }
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    val state by screenModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = state) {
        scope.launch {
            if (state.isSignedIn) {
                context.showShortToast(R.string.signed_in)
                navigator.safePop(AuthScreen.KEY)
            }
            if (state.signInErrorSnackbarVisible) {
                screenModel.signInErrorShowed()
                snackbarHostState.showSnackbar(
                    message = context.getString(R.string.sign_in_error),
                    withDismissAction = true
                )
            }
            if (state.signUpErrorSnackbarVisible) {
                screenModel.signUpErrorShowed()
                snackbarHostState.showSnackbar(
                    message = context.getString(R.string.sign_up_error),
                    withDismissAction = true
                )
            }
            if (state.forgottenPasswordDialogSuccess) {
                screenModel.toggleForgottenPasswordSuccessVisibility()
                snackbarHostState.showSnackbar(
                    message = context.getString(R.string.message_sent),
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
                label = { Text(text = stringResource(id = R.string.email)) },
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
                                contentDescription = stringResource(id = R.string.cd_clear_field)
                            )
                        }
                },
                isError = state.emailError != null,
                supportingText = if (state.emailError != null) {
                    {
                        Text(
                            text = stringResource(
                                id = when (state.emailError) {
                                    EmailErrorType.INVALID -> R.string.email_error_invalid
                                    EmailErrorType.NO_USER -> R.string.email_error_no_user
                                    EmailErrorType.USER_EXISTS -> R.string.email_error_user_exists
                                    else -> R.string.empty_field_error
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
            )
            OutlinedTextField(
                value = state.password,
                onValueChange = screenModel::updatePassword,
                singleLine = true,
                label = { Text(text = stringResource(id = R.string.password)) },
                visualTransformation = if (state.passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                trailingIcon = {
                    IconButton(onClick = screenModel::updatePasswordHidden) {
                        if (state.passwordHidden)
                            Icon(
                                imageVector = Icons.Filled.Visibility,
                                contentDescription = stringResource(id = R.string.show_password)
                            )
                        else
                            Icon(
                                imageVector = Icons.Filled.VisibilityOff,
                                contentDescription = stringResource(id = R.string.hide_password)
                            )
                    }
                },
                isError = state.passwordError != null,
                supportingText = if (state.passwordError != null) {
                    {
                        Text(
                            text = stringResource(
                                id = when (state.passwordError) {
                                    PasswordErrorType.EMPTY -> R.string.password_error_empty
                                    PasswordErrorType.TOO_SHORT -> R.string.password_error_too_short
                                    PasswordErrorType.WRONG -> R.string.password_error_wrong
                                    PasswordErrorType.INVALID -> R.string.password_error_invalid
                                    else -> R.string.empty_field_error
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
                Text(text = stringResource(id = R.string.sign_in))
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(
                onClick = {
                    focusManager.clearFocus(true)
                    screenModel.signUp()
                },
                modifier = Modifier.fillMaxWidth(0.75f)
            ) {
                Text(text = stringResource(id = R.string.sign_up))
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(
                onClick = screenModel::toggleForgottenPasswordDialogVisibility,
                modifier = Modifier.fillMaxWidth(0.75f)
            ) {
                Text(text = stringResource(id = R.string.forgot_password))
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
            dialogTitleId = R.string.sign_up_successful_dialog_title,
            dialogTextId = R.string.sign_up_successful_dialog_message,
            confirmBtnTextId = R.string.ok,
            onConfirm = screenModel::toggleSignUpSuccessVisibility,
            dismissible = false,
        )

        TauAlertDialog(
            isVisible = state.emailUnverifiedDialogVisible,
            imageVector = Icons.Outlined.ErrorOutline,
            dialogTitleId = R.string.verify_email_dialog_title,
            dialogTextId = R.string.verify_email_dialog_message,
            confirmBtnTextId = R.string.verify_email_send_again,
            onConfirm = { screenModel.toggleEmailUnverifiedDialogVisibility(true) },
            dismissible = false,
            dismissBtnTextId = R.string.cancel,
            onDismissRequest = { screenModel.toggleEmailUnverifiedDialogVisibility(false) }
        )
    }
}