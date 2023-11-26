package pl.mftau.mftau.gospel.presentation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import pl.mftau.mftau.R
import pl.mftau.mftau.common.presentation.components.LoadingBox
import pl.mftau.mftau.common.presentation.components.NoInternetDialog
import pl.mftau.mftau.common.presentation.components.TauCenteredTopBar
import pl.mftau.mftau.common.utils.safePop
import pl.mftau.mftau.common.utils.speak
import pl.mftau.mftau.gospel.domain.model.Gospel
import pl.mftau.mftau.gospel.presentation.GospelScreenModel.State

class GospelScreen : Screen {
    override val key: ScreenKey
        get() = KEY

    @Composable
    override fun Content() {
        val scope = rememberCoroutineScope()
        val screenModel = getScreenModel<GospelScreenModel>()
        val repeatGospel by screenModel.repeatGospel.collectAsStateWithLifecycle()

        val textToSpeech = koinInject<MyTextToSpeech>()
        var isSpeaking by remember { mutableStateOf(textToSpeech.isSpeaking) }
        textToSpeech.setProgressListener {
            scope.launch {
                if (repeatGospel) {
                    delay(1000)
                    textToSpeech.speak(screenModel.getGospelToRead())
                } else isSpeaking = false
            }
        }
        GospelScreenContent(
            screenModel = screenModel,
            onClick = {
                isSpeaking = !isSpeaking
                if (isSpeaking) textToSpeech.speak(screenModel.getGospelToRead())
                else textToSpeech.stop()
            },
            isSpeaking = isSpeaking
        )

        LifecycleEffect(
            onDisposed = {
                isSpeaking = false
                if (textToSpeech.isSpeaking) {
                    textToSpeech.stop()
                    textToSpeech.shutdown()
                }
            }
        )
    }

    companion object {
        const val KEY = "GospelScreen"
    }
}

@Composable
fun GospelScreenContent(screenModel: GospelScreenModel, onClick: () -> Unit, isSpeaking: Boolean) {
    val navigator = LocalNavigator.currentOrThrow
    val state by screenModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TauCenteredTopBar(
                title = stringResource(id = R.string.gospel_for_today),
                onNavClick = { navigator.safePop(GospelScreen.KEY) },
                actions = {
                    if (state is State.Success)
                        IconButton(onClick = onClick) {
                            Crossfade(targetState = isSpeaking, label = "") {
                                Icon(
                                    imageVector =
                                    if (it) Icons.Filled.Pause
                                    else Icons.AutoMirrored.Filled.VolumeUp,
                                    contentDescription = stringResource(id = R.string.listen_gospel)
                                )
                            }
                        }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            when (state) {
                is State.Loading -> LoadingBox()
                is State.Success -> GospelLayout(gospel = (state as State.Success).gospel)

                is State.Failure -> NoInternetDialog(
                    isVisible = true,
                    onReconnect = screenModel::loadGospel,
                    onDismiss = navigator::pop
                )
            }
        }
    }
}

@Composable
private fun GospelLayout(gospel: Gospel) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        Text(
            text = gospel.verses,
            modifier = Modifier.padding(vertical = 4.dp),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = gospel.title,
            modifier = Modifier.padding(vertical = 8.dp),
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = gospel.author,
            modifier = Modifier.padding(vertical = 8.dp),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = gospel.text,
            modifier = Modifier.padding(vertical = 4.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}