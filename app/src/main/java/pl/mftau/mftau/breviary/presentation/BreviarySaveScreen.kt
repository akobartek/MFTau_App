package pl.mftau.mftau.breviary.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import pl.mftau.mftau.R
import pl.mftau.mftau.breviary.domain.model.BreviaryEntity
import pl.mftau.mftau.breviary.presentation.components.MultipleOfficesDialog
import pl.mftau.mftau.core.presentation.components.BasicAlertDialog
import pl.mftau.mftau.core.presentation.components.LoadingIndicator
import pl.mftau.mftau.core.presentation.components.NoInternetDialog
import pl.mftau.mftau.core.presentation.components.TauTopBar

data class BreviarySaveScreen(
    val date: String = ""
) : BreviaryScreen() {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = getScreenModel<BreviarySaveScreenModel>()
        screenModel.setup(date = date)
        val state by screenModel.state.collectAsStateWithLifecycle()

        var saveCompleteDialogVisible by remember { mutableStateOf(false) }
        LaunchedEffect(key1 = state) {
            if (state is BreviarySaveScreenModel.State.DownloadingState) {
                val id = (state as BreviarySaveScreenModel.State.DownloadingState).entity.id
                if (id > 0) saveCompleteDialogVisible = true
            }
        }

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

                    is BreviarySaveScreenModel.State.Init -> BasicAlertDialog(
                        imageVector = Icons.Default.Save,
                        dialogTitleId = R.string.saving_breviary,
                        dialogTextId = R.string.save_breviary_dialog_msg,
                        dismissible = false,
                        confirmBtnTextId = R.string.save,
                        onConfirmation = screenModel::checkIfThereAreMultipleOffices,
                        dismissBtnTextId = R.string.cancel,
                        onDismissRequest = {
                            screenModel.cancelScreen()
                            navigator.pop()
                        }
                    )

                    is BreviarySaveScreenModel.State.MultipleOffices -> MultipleOfficesDialog(
                        offices = (state as BreviarySaveScreenModel.State.MultipleOffices).offices,
                        onSelect = screenModel::officeSelected,
                        onCancel = {
                            screenModel.cancelScreen()
                            navigator.pop()
                        }
                    )

                    is BreviarySaveScreenModel.State.DownloadingState -> DownloadingStateLayout(
                        entity = (state as BreviarySaveScreenModel.State.DownloadingState).entity
                    )

                    is BreviarySaveScreenModel.State.Failure -> NoInternetDialog(
                        onReconnect = screenModel::checkIfThereAreMultipleOffices,
                        onCancel = {
                            screenModel.cancelScreen()
                            navigator.pop()
                        }
                    )
                }

                if (saveCompleteDialogVisible)
                    BasicAlertDialog(
                        imageVector = Icons.Default.Save,
                        dialogTitleId = R.string.saving_breviary,
                        dialogTextId = R.string.save_finished_dialog_msg,
                        confirmBtnTextId = R.string.ok,
                        onConfirmation = { saveCompleteDialogVisible = false },
                        onDismissRequest = { saveCompleteDialogVisible = false }
                    )
            }
        }
    }

    @Composable
    private fun DownloadingStateLayout(entity: BreviaryEntity) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 44.dp)
        ) {
            DownloadItem(stringResource(id = R.string.invitatory), entity.invitatory)
            DownloadItem(stringResource(id = R.string.office_of_readings), entity.officeOfReadings)
            DownloadItem(stringResource(id = R.string.lauds), entity.lauds)
            DownloadItem(stringResource(id = R.string.midmorning_prayer), entity.prayer1)
            DownloadItem(stringResource(id = R.string.midday_prayer), entity.prayer2)
            DownloadItem(stringResource(id = R.string.midafternoon_prayer), entity.prayer3)
            DownloadItem(stringResource(id = R.string.vespers), entity.vespers)
            DownloadItem(stringResource(id = R.string.compline), entity.compline)
            DownloadItem(
                stringResource(id = R.string.save_in_memory),
                if (entity.id == 0L) "" else entity.id.toString()
            )
        }
    }

    @Composable
    fun DownloadItem(name: String, value: String?) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .padding(vertical = 4.dp)
        ) {
            Text(text = name, fontSize = 16.sp)
            when {
                value == null -> Icon(
                    imageVector = Icons.Default.ErrorOutline,
                    contentDescription = stringResource(id = R.string.cd_download_error),
                    tint = MaterialTheme.colorScheme.error
                )

                value.isBlank() -> CircularProgressIndicator(modifier = Modifier.size(24.dp))

                else -> Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = stringResource(id = R.string.cd_download_error)
                )
            }
        }
    }
}