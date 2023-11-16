package pl.mftau.mftau.readings.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import pl.mftau.mftau.core.presentation.components.TauTopBar

class ReadingsTextScreen(val text: String, val name: String) : ReadingsScreen() {
    @Composable
    override fun Content() {
        ReadingsTextScreenContent(text, name)
    }
}

@Composable
fun ReadingsTextScreenContent(text: String, name: String) {
    val navigator = LocalNavigator.currentOrThrow
    Scaffold(
        topBar = {
            TauTopBar(
                title = name,
                onNavClick = navigator::pop
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
                )
            }
        }
    }
}