package pl.mftau.mftau.auth.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import pl.mftau.mftau.R
import pl.mftau.mftau.core.presentation.components.CommunityLogo
import pl.mftau.mftau.core.presentation.components.TauTopBar

class AuthScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val context = LocalContext.current
        val keyboardController = LocalSoftwareKeyboardController.current

        val screenModel = rememberScreenModel { AuthScreenModel() }
        val state by screenModel.state.collectAsStateWithLifecycle()

        Scaffold(
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
                CommunityLogo(modifier = Modifier.padding(top = 16.dp))
                OutlinedTextField(
                    value = state.email,
                    onValueChange = screenModel::updateEmail,
                    singleLine = true,
                    label = { Text(text = stringResource(id = R.string.email)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    isError = state.emailError,
                    supportingText = {
                        if (state.emailError)
                            Text(text = stringResource(id = R.string.email_error))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
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
                    isError = state.passwordErrorCode != -1,
                    supportingText = {
                        if (state.passwordErrorCode != -1)
                            Text(text = stringResource(id = state.passwordErrorCode))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
            }
        }
    }
}