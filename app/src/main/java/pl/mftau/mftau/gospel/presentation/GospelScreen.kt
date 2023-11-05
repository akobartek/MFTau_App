package pl.mftau.mftau.gospel.presentation

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pl.mftau.mftau.R
import pl.mftau.mftau.core.data.PreferencesRepository
import pl.mftau.mftau.core.presentation.components.LoadingIndicator
import pl.mftau.mftau.core.presentation.components.NoInternetDialog
import pl.mftau.mftau.dataStore
import pl.mftau.mftau.gospel.data.Gospel
import pl.mftau.mftau.ui.theme.mfTauFont
import java.util.Locale

class GospelScreen : Screen {

    private var mTextToSpeech: MyTextToSpeech? = null

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()

        val screenModel = rememberScreenModel { GospelScreenModel() }
        val state by screenModel.state.collectAsStateWithLifecycle()
        var isPlaying by remember { mutableStateOf(false) }

        mTextToSpeech = MyTextToSpeech(context) { status ->
            if (status != TextToSpeech.ERROR) {
                mTextToSpeech?.setLanguage(Locale("pl_PL"))
                mTextToSpeech?.setSpeechRate(0.9f)
            }
        }
        mTextToSpeech?.setProgressListener {
            coroutineScope.launch {
                if (PreferencesRepository(context.dataStore).getRepeatGospel()) {
                    delay(1000)
                    readGospel(screenModel.getGospelToRead(), mTextToSpeech)
                } else isPlaying = false
            }
        }

        LifecycleEffect(
            onDisposed = {
                isPlaying = false
                if (mTextToSpeech?.isSpeaking == true) {
                    mTextToSpeech?.stop()
                    mTextToSpeech?.shutdown()
                }
            }
        )

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    title = {
                        Text(
                            text = stringResource(R.string.gospel_for_today),
                            fontFamily = mfTauFont
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = navigator::pop) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = stringResource(id = R.string.cd_back_arrow_btn)
                            )
                        }
                    },
                    actions = {
                        if (state is GospelScreenModel.State.Success)
                            IconButton(onClick = {
                                isPlaying = !isPlaying
                                if (isPlaying)
                                    readGospel(screenModel.getGospelToRead(), mTextToSpeech)
                                else mTextToSpeech?.stop()
                            }) {
                                Crossfade(targetState = isPlaying, label = "") {
                                    Icon(
                                        imageVector = if (it) Icons.Filled.Pause else Icons.Filled.VolumeUp,
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
                    is GospelScreenModel.State.Loading -> LoadingIndicator()

                    is GospelScreenModel.State.Success ->
                        GospelLayout(gospel = (state as GospelScreenModel.State.Success).gospel)

                    is GospelScreenModel.State.Failure ->
                        NoInternetDialog(
                            onReconnect = screenModel::loadGospel,
                            onCancel = navigator::pop
                        )
                }
            }
        }
    }

    private fun readGospel(textToRead: String, tts: TextToSpeech?) {
        tts?.speak(textToRead, TextToSpeech.QUEUE_FLUSH, Bundle().apply {
            putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "UtteranceID")
        }, "UtteranceID")
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