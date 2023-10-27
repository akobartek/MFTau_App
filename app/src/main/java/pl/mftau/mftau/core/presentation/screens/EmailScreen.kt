package pl.mftau.mftau.core.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen


data class EmailScreen(val type: EmailScreenType) : Screen {

    @Composable
    override fun Content() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = when(type) {
                    is EmailScreenType.AskForPray -> "ASK FOR PRAY"
                    is EmailScreenType.ReportError -> "REPORT BUG"
                }
            )
        }
    }

    sealed class EmailScreenType {
        data object AskForPray : EmailScreenType()
        data object ReportError : EmailScreenType()
    }
}