package pl.mftau.mftau.auth.presentation

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
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.launch
import pl.mftau.mftau.R
import pl.mftau.mftau.core.presentation.components.CommunityLogo
import pl.mftau.mftau.core.presentation.components.TauTopBar
import pl.mftau.mftau.auth.presentation.AuthScreenModel.EmailErrorType
import pl.mftau.mftau.auth.presentation.AuthScreenModel.PasswordErrorType
import pl.mftau.mftau.core.utils.showShortToast

class AuthScreen : Screen {
    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val context = LocalContext.current
        val keyboardController = LocalSoftwareKeyboardController.current
        val scope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }

        val screenModel = getScreenModel<AuthScreenModel>()
        val state by screenModel.state.collectAsStateWithLifecycle()

        val (emailRef, passwordRef) = remember { FocusRequester.createRefs() }
        val focusManager = LocalFocusManager.current

        LaunchedEffect(key1 = state) {
            scope.launch {
                if (state.isSignedIn) {
                    context.showShortToast(R.string.signed_in)
                    navigator.pop()
                }
                if (state.signInErrorSnackbarVisible)
                    snackbarHostState.showSnackbar(
                        message = context.getString(R.string.sign_in_error),
                        withDismissAction = true
                    ).also { screenModel.signInErrorShowed() }
                if (state.signUpErrorSnackbarVisible)
                    snackbarHostState.showSnackbar(
                        message = context.getString(R.string.sign_up_error),
                        withDismissAction = true
                    ).also { screenModel.signUpErrorShowed() }
            }
        }

        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
            topBar = {
                TauTopBar(
                    title = "",
                    onNavClick = navigator::pop
                )
            }
        ) { paddingValues ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(horizontal = 48.dp)
            ) {
                CommunityLogo(modifier = Modifier.padding(top = 8.dp))
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
                                    contentDescription = stringResource(id = R.string.show_password)
                                )
                            }
                    },
                    isError = state.emailError != null,
                    supportingText = {
                        when (state.emailError) {
                            EmailErrorType.INVALID ->
                                Text(text = stringResource(id = R.string.email_error_invalid))

                            EmailErrorType.NO_USER ->
                                Text(text = stringResource(id = R.string.email_error_no_user))

                            EmailErrorType.USER_EXISTS ->
                                Text(text = stringResource(id = R.string.email_error_user_exists))

                            else -> {}
                        }
                    },
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
                    supportingText = {
                        when (state.passwordError) {
                            PasswordErrorType.EMPTY ->
                                Text(text = stringResource(id = R.string.password_error_empty))

                            PasswordErrorType.TOO_SHORT ->
                                Text(text = stringResource(id = R.string.password_error_too_short))

                            PasswordErrorType.WRONG ->
                                Text(text = stringResource(id = R.string.password_error_wrong))

                            PasswordErrorType.INVALID ->
                                Text(text = stringResource(id = R.string.password_error_invalid))

                            else -> {}
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .focusRequester(passwordRef)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = screenModel::signIn,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.sign_in))
                }
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(
                    onClick = screenModel::signUp,
                    modifier = Modifier.fillMaxWidth(0.75f)
                ) {
                    Text(text = stringResource(id = R.string.sign_up))
                }
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(
                    onClick = screenModel::signUp,
                    modifier = Modifier.fillMaxWidth(0.75f)
                ) {
                    Text(text = stringResource(id = R.string.forgot_password))
                }
            }
        }
    }
}