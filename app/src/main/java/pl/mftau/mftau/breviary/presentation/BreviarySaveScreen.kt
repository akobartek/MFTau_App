package pl.mftau.mftau.breviary.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import pl.mftau.mftau.R
import pl.mftau.mftau.breviary.presentation.components.MultipleOfficesDialog
import pl.mftau.mftau.core.presentation.components.LoadingIndicator
import pl.mftau.mftau.core.presentation.components.NoInternetDialog
import pl.mftau.mftau.core.presentation.components.TauTopBar

/**
 * TODO() -> SAVING BREVIARY
 */
data class BreviarySaveScreen(
    val date: String = ""
) : BreviaryScreen() {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = getScreenModel<BreviarySaveScreenModel>()
        screenModel.setup(date = date)
        val state by screenModel.state.collectAsStateWithLifecycle()

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
                when (state) {
                    is BreviarySaveScreenModel.State.Cancelled -> {}
                    is BreviarySaveScreenModel.State.Loading -> LoadingIndicator()

                    is BreviarySaveScreenModel.State.Init -> {}

                    is BreviarySaveScreenModel.State.MultipleOffices ->
                        MultipleOfficesDialog(
                            offices = (state as BreviarySaveScreenModel.State.MultipleOffices).offices,
                            onSelect = screenModel::officeSelected,
                            onCancel = {
                                screenModel.cancelScreen()
                                navigator.pop()
                            }
                        )

                    is BreviarySaveScreenModel.State.DownloadingState -> {}

                    is BreviarySaveScreenModel.State.Failure ->
                        NoInternetDialog(
                            onReconnect = screenModel::checkIfThereAreMultipleOffices,
                            onCancel = {
                                screenModel.cancelScreen()
                                navigator.pop()
                            }
                        )
                }
            }
        }
    }
}