package pl.mftau.mftau.core.presentation.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.launch
import pl.mftau.mftau.R
import pl.mftau.mftau.core.presentation.screenmodels.EmailScreenModel
import pl.mftau.mftau.core.utils.openWebsiteInChromeCustomTabsIfSupported
import pl.mftau.mftau.ui.theme.mfTauFont
import java.io.Serializable


data class EmailScreen(val screenType: EmailScreenType) : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val context = LocalContext.current
        val scope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }

        val screenModel = rememberScreenModel { EmailScreenModel() }
        val state by screenModel.state.collectAsState()

        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    title = {
                        Text(
                            text = stringResource(
                                id = when (screenType) {
                                    is EmailScreenType.AskForPray -> R.string.ask_for_pray
                                    is EmailScreenType.ReportError -> R.string.report_error
                                }
                            ),
                            fontFamily = mfTauFont
                        )

                    },
                    navigationIcon = {
                        IconButton(onClick = { navigator.pop() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = stringResource(id = R.string.cd_back_arrow_btn)
                            )
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    if (!state.gdprChecked) {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = context.getString(R.string.rodo_error_message),
                                withDismissAction = true
                            )
                        }
                        return@FloatingActionButton
                    }
                    if (screenModel.validateInput()) {
                        val emailIntent = Intent(Intent.ACTION_SENDTO)
                        emailIntent.apply {
                            data = Uri.parse("mailto:")
                            putExtra(Intent.EXTRA_EMAIL, arrayOf(screenType.email))
                            putExtra(Intent.EXTRA_SUBJECT, "Wiadomość od ${state.name.trim()}")
                            putExtra(Intent.EXTRA_TEXT, state.text.trim())
                        }

                        try {
                            context.startActivity(emailIntent)
                        } catch (ex: android.content.ActivityNotFoundException) {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = context.getString(R.string.send_mail_error),
                                    withDismissAction = true
                                )
                            }
                        }
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Send,
                        contentDescription = stringResource(id = R.string.send_mail)
                    )
                }
            }
        ) { paddingValues ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(horizontal = 48.dp, vertical = 16.dp)
            ) {
                OutlinedTextField(
                    value = state.name,
                    onValueChange = { value -> screenModel.updateName(value) },
                    label = { Text(text = stringResource(id = R.string.email_name)) },
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
                    isError = state.nameError,
                    supportingText = {
                        if (state.nameError)
                            Text(text = stringResource(id = R.string.empty_email_name_error))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = state.text,
                    onValueChange = { value -> screenModel.updateText(value) },
                    label = {
                        Text(
                            text = stringResource(
                                id = when (screenType) {
                                    is EmailScreenType.AskForPray -> R.string.intention
                                    is EmailScreenType.ReportError -> R.string.error_description
                                }
                            )
                        )
                    },
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                    minLines = 5,
                    maxLines = 10,
                    isError = state.textError,
                    supportingText = {
                        if (state.textError)
                            Text(
                                text = stringResource(
                                    id = when (screenType) {
                                        is EmailScreenType.AskForPray -> R.string.empty_email_intention_error
                                        is EmailScreenType.ReportError -> R.string.empty_email_description_error
                                    }
                                )
                            )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = state.gdprChecked,
                        onCheckedChange = { value -> screenModel.updateGdpr(value) },
                        modifier = Modifier.offset(x = (-12).dp)
                    )

                    val privacyPolicyText = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                            append(stringResource(id = R.string.privacy_policy_part1))
                        }

                        pushStringAnnotation(
                            tag = "policy",
                            annotation = "http://mftau.pl/polityka-prywatnosci/"
                        )
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                            append(stringResource(id = R.string.privacy_policy_part2))
                        }
                        pop()

                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                            append(".")
                        }
                    }
                    ClickableText(
                        text = privacyPolicyText,
                        onClick = { offset ->
                            privacyPolicyText.getStringAnnotations(
                                tag = "policy",
                                start = offset,
                                end = offset
                            ).firstOrNull()?.let {
                                context.openWebsiteInChromeCustomTabsIfSupported(it.item)
                            } ?: screenModel.updateGdpr(!state.gdprChecked)
                        },
                        modifier = Modifier.offset(x = (-8).dp)
                    )
                }
            }
        }
    }

    sealed class EmailScreenType(val email: String) : Serializable {
        data object AskForPray : EmailScreenType("modlitwa@mftau.pl") {
            private fun readResolve(): Any = AskForPray
        }

        data object ReportError : EmailScreenType("sokolowskijbartek@gmail.com") {
            private fun readResolve(): Any = ReportError
        }
    }
}