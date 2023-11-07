package pl.mftau.mftau.breviary.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import pl.mftau.mftau.R
import pl.mftau.mftau.core.presentation.components.TauTopBar

/**
 * TODO() -> SAVING BREVIARY
 */
data class BreviarySaveScreen(
    val daysFromToday: Int = 0
) : BreviaryScreen() {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Scaffold(
            topBar = {
                TauTopBar(
                    title = stringResource(id = R.string.saving_breviary),
                    onNavClick = navigator::pop
                )
            }
        ) { paddingValues ->
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(horizontal = 8.dp)
            ) {
                Text(text = "THIS IS NOT AVAILABLE YET")
            }
        }
    }
}