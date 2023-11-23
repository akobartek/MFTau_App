package pl.mftau.mftau.readings.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import pl.mftau.mftau.common.presentation.components.TauCenteredTopBar
import pl.mftau.mftau.core.utils.safePop

class ReadingsTextScreen(val name: String, val text: AnnotatedString) : ReadingsScreen() {
    override val key: ScreenKey
        get() = KEY

    @Composable
    override fun Content() {
        ReadingsTextScreenContent(name, text)
    }

    companion object {
        const val KEY = "ReadingsTextScreen"
    }
}

@Composable
fun ReadingsTextScreenContent(name: String, text: AnnotatedString) {
    val navigator = LocalNavigator.currentOrThrow
    Scaffold(
        topBar = {
            TauCenteredTopBar(
                title = name,
                onNavClick = { navigator.safePop(ReadingsTextScreen.KEY) },
                navIcon = Icons.Default.Close
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 4.dp)
                .verticalScroll(rememberScrollState())
        ) {
            SelectionContainer {
                Text(
                    text = text,
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}