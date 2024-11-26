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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.gospel_for_today
import mftau.composeapp.generated.resources.listen_gospel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import pl.mftau.mftau.common.presentation.composables.LoadingBox
import pl.mftau.mftau.common.presentation.composables.NoInternetDialog
import pl.mftau.mftau.common.presentation.composables.TauCenteredTopBar
import pl.mftau.mftau.gospel.domain.model.Gospel

@Composable
fun GospelScreen(
    navigateUp: () -> Unit,
    viewModel: GospelViewModel = koinInject(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val repeatGospel by viewModel.repeatGospel.collectAsStateWithLifecycle()
    var isSpeaking by remember { mutableStateOf(false) }

//    val textToSpeech = koinInject<MyTextToSpeech>()
//    textToSpeech.setProgressListener {
//        scope.launch {
//            if (repeatGospel) {
//                delay(1000)
//                textToSpeech.speak(screenModel.getGospelToRead())
//            } else isSpeaking = false
//        }
//    }

    GospelScreenContent(
        state = state,
        navigateUp = navigateUp,
        loadGospel = viewModel::loadGospel,
        onPlayPauseClicked = {
//            isSpeaking = !isSpeaking
//            if (isSpeaking) textToSpeech.speak(screenModel.getGospelToRead())
//            else textToSpeech.stop()
        },
        isSpeaking = isSpeaking,
    )

    DisposableEffect(Unit) {
        onDispose {
            isSpeaking = false
//            if (textToSpeech.isSpeaking) {
//                textToSpeech.stop()
//                textToSpeech.shutdown()
//            }
        }
    }
}

@Composable
fun GospelScreenContent(
    state: GospelScreenState,
    navigateUp: () -> Unit,
    loadGospel: () -> Unit,
    onPlayPauseClicked: () -> Unit,
    isSpeaking: Boolean,
) {
    Scaffold(
        topBar = {
            TauCenteredTopBar(
                title = stringResource(Res.string.gospel_for_today),
                onNavClick = navigateUp,
                actions = {
                    if (state is GospelScreenState.Success)
                        IconButton(onClick = onPlayPauseClicked) {
                            Crossfade(targetState = isSpeaking, label = "") {
                                Icon(
                                    imageVector =
                                    if (it) Icons.Filled.Pause
                                    else Icons.AutoMirrored.Filled.VolumeUp,
                                    contentDescription = stringResource(Res.string.listen_gospel),
                                )
                            }
                        }
                },
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
                is GospelScreenState.Loading -> LoadingBox()
                is GospelScreenState.Success -> GospelLayout(gospel = state.gospel)
                is GospelScreenState.Failure -> NoInternetDialog(
                    isVisible = true,
                    onReconnect = loadGospel,
                    onDismiss = navigateUp,
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
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = gospel.title,
            modifier = Modifier.padding(vertical = 8.dp),
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            text = gospel.author,
            modifier = Modifier.padding(vertical = 8.dp),
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            text = gospel.text,
            modifier = Modifier.padding(vertical = 4.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}