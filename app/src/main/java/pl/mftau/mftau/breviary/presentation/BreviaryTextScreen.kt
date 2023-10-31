package pl.mftau.mftau.breviary.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Deblur
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import pl.mftau.mftau.R
import pl.mftau.mftau.breviary.model.Breviary
import pl.mftau.mftau.core.presentation.components.LoadingIndicator
import pl.mftau.mftau.core.presentation.components.NoInternetDialog
import pl.mftau.mftau.ui.theme.mfTauFont

data class BreviaryTextScreen(
    val position: Int = 0,
    val daysFromToday: Int = 0
) : BreviaryScreen() {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = rememberScreenModel {
            BreviaryScreenModel(
                position = position,
                daysFromToday = daysFromToday
            )
        }
        val state by screenModel.state.collectAsState()

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    title = {
                        Text(
                            text = stringArrayResource(id = R.array.breviary_list)[position],
                            fontFamily = mfTauFont
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navigator.pop() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = stringResource(id = R.string.cd_back_arrow_btn)
                            )
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
                    is BreviaryScreenModel.State.Loading -> LoadingIndicator()

                    is BreviaryScreenModel.State.MultipleOffices ->
                        MultipleOfficesDialog(
                            offices = (state as BreviaryScreenModel.State.MultipleOffices).offices,
                            onSelect = screenModel::officeLinkSelected,
                            onCancel = { navigator.pop() }
                        )

                    is BreviaryScreenModel.State.BreviaryAvailable ->
                        BreviaryLayout(breviary = (state as BreviaryScreenModel.State.BreviaryAvailable).breviary)

                    is BreviaryScreenModel.State.Failure ->
                        NoInternetDialog(
                            reconnect = { screenModel.checkIfThereAreMultipleOffices() },
                            cancel = { navigator.pop() }
                        )
                }
            }
        }
    }
}

@Composable
private fun BreviaryLayout(breviary: Breviary) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text(text = "SUCCESS")
    }
}

@Composable
private fun MultipleOfficesDialog(
    offices: Map<String, String>,
    onSelect: (String) -> Unit,
    onCancel: () -> Unit
) {
    var selectedOfficeLink = offices.keys.iterator().next()

    AlertDialog(
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_breviary),
                contentDescription = null
            )
        },
        title = { Text(text = stringResource(id = R.string.select_office)) },
        text = {
            Column(Modifier.selectableGroup()) {
                offices.forEach { (link, text) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .selectable(
                                selected = (link == selectedOfficeLink),
                                onClick = { selectedOfficeLink = link },
                                role = Role.Tab
                            )
                            .padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (selectedOfficeLink == link)
                            Text(
                                text = text,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier
                                    .padding(start = 16.dp)
                                    .background(MaterialTheme.colorScheme.primary)
                            )
                        else
                            Text(
                                text = text,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                    }
                }
            }
        },
        onDismissRequest = {},
        confirmButton = {
            TextButton(onClick = { onSelect(selectedOfficeLink) }) {
                Text(stringResource(id = R.string.save))
            }
        },
        dismissButton = {
            TextButton(onClick = { onCancel() }) {
                Text(stringResource(id = R.string.cancel))
            }
        }
    )
}