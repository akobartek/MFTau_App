package pl.mftau.mftau.core.presentation.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.launch
import pl.mftau.mftau.R
import pl.mftau.mftau.common.presentation.composables.TauCenteredTopBar
import pl.mftau.mftau.core.presentation.screenmodels.EmailScreenModel
import pl.mftau.mftau.common.utils.openWebsiteInChromeCustomTabsIfSupported
import pl.mftau.mftau.common.utils.safePop

data class EmailScreen(val screenType: EmailScreenType) : Screen {
    override val key: ScreenKey
        get() = KEY

    @Composable
    override fun Content() {
        EmailScreenContent(
            screenModel = getScreenModel(),
            screenType = screenType
        )
    }

    enum class EmailScreenType(val email: String) {
        AskForPray("modlitwa@mftau.pl"), ReportError("sokolowskijbartek@gmail.com")
    }

    companion object {
        const val KEY = "EmailScreen"
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EmailScreenContent(screenModel: EmailScreenModel, screenType: EmailScreen.EmailScreenType) {
    val navigator = LocalNavigator.currentOrThrow
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val (nameRef, messageRef) = remember { FocusRequester.createRefs() }
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    val state by screenModel.state.collectAsStateWithLifecycle()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TauCenteredTopBar(
                title = stringResource(
                    id = when (screenType) {
                        EmailScreen.EmailScreenType.AskForPray -> R.string.ask_for_pray
                        EmailScreen.EmailScreenType.ReportError -> R.string.report_error
                    }
                ),
                onNavClick = { navigator.safePop(EmailScreen.KEY) }
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
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = stringResource(id = R.string.send_mail)
                )
            }
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
                .padding(horizontal = 48.dp, vertical = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            OutlinedTextField(
                value = state.name,
                onValueChange = screenModel::updateName,
                label = { Text(text = stringResource(id = R.string.email_name)) },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                }),
                maxLines = 1,
                isError = state.nameError,
                supportingText = if (state.nameError) {
                    {
                        Text(text = stringResource(id = R.string.empty_email_name_error))
                    }
                } else null,
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(nameRef)
                    .focusProperties { next = messageRef }
            )
            OutlinedTextField(
                value = state.text,
                onValueChange = screenModel::updateText,
                label = {
                    Text(
                        text = stringResource(
                            id = when (screenType) {
                                EmailScreen.EmailScreenType.AskForPray -> R.string.intention
                                EmailScreen.EmailScreenType.ReportError -> R.string.error_description
                            }
                        )
                    )
                },
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                minLines = 4,
                maxLines = 10,
                isError = state.textError,
                supportingText = if (state.textError) {
                    {
                        Text(
                            text = stringResource(
                                id = when (screenType) {
                                    EmailScreen.EmailScreenType.AskForPray -> R.string.empty_email_intention_error
                                    EmailScreen.EmailScreenType.ReportError -> R.string.empty_email_description_error
                                }
                            )
                        )
                    }
                } else null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
                    .focusRequester(messageRef)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = state.gdprChecked,
                    onCheckedChange = screenModel::updateGdpr,
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