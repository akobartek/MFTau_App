package pl.mftau.mftau.breviary.presentation

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import pl.mftau.mftau.R
import pl.mftau.mftau.breviary.model.Breviary
import pl.mftau.mftau.breviary.model.BreviaryType
import pl.mftau.mftau.breviary.model.Invitatory
import pl.mftau.mftau.core.presentation.components.LoadingIndicator
import pl.mftau.mftau.core.presentation.components.NoInternetDialog
import pl.mftau.mftau.ui.theme.TauSecondaryDark
import pl.mftau.mftau.ui.theme.TauSecondaryLight
import pl.mftau.mftau.ui.theme.mfTauFont

data class BreviaryTextScreen(
    val position: Int = 0,
    val daysFromToday: Int = 0
) : BreviaryScreen() {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val color =
            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
                TauSecondaryDark
            else TauSecondaryLight
        val screenModel = rememberScreenModel {
            BreviaryScreenModel(
                type = BreviaryType.fromPosition(position),
                daysFromToday = daysFromToday,
                accentColor = color
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
                        IconButton(onClick = navigator::pop) {
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
                    .padding(horizontal = 8.dp)
            ) {
                when (state) {
                    is BreviaryScreenModel.State.Cancelled -> {}
                    is BreviaryScreenModel.State.Loading -> LoadingIndicator()

                    is BreviaryScreenModel.State.MultipleOffices ->
                        MultipleOfficesDialog(
                            offices = (state as BreviaryScreenModel.State.MultipleOffices).offices,
                            onSelect = screenModel::officeSelected,
                            onCancel = {
                                screenModel.cancelScreen()
                                navigator.pop()
                            }
                        )

                    is BreviaryScreenModel.State.BreviaryAvailable ->
                        BreviaryLayout(breviary = (state as BreviaryScreenModel.State.BreviaryAvailable).breviary)

                    is BreviaryScreenModel.State.Failure ->
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

@Composable
private fun MultipleOfficesDialog(
    offices: Map<String, String>,
    onSelect: (String) -> Unit,
    onCancel: () -> Unit
) {
    var selectedOfficeLink by remember { mutableStateOf(offices.keys.toTypedArray()[0]) }

    AlertDialog(
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_breviary),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        },
        title = { Text(text = stringResource(id = R.string.select_office)) },
        text = {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                offices.forEach { (link, text) ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = text,
                            color =
                            if (selectedOfficeLink == link) MaterialTheme.colorScheme.onSecondaryContainer
                            else MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .padding(vertical = 2.dp)
                                .fillMaxWidth()
                                .clickable { selectedOfficeLink = link }
                                .background(
                                    color = if (selectedOfficeLink == link) MaterialTheme.colorScheme.secondaryContainer
                                    else MaterialTheme.colorScheme.background,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(8.dp)
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
            TextButton(onClick = onCancel) {
                Text(stringResource(id = R.string.cancel))
            }
        },
        modifier = Modifier.heightIn(0.dp, 560.dp)
    )
}

@Composable
private fun BreviaryLayout(breviary: Breviary) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 32.dp)
    ) {
        when (breviary) {
            is Invitatory -> InvitatoryLayout(invitatory = breviary)
            else -> Text(text = "SUCCESS")
        }
    }
}

@Composable
private fun InvitatoryLayout(invitatory: Invitatory) {
    Text(
        text = invitatory.beginning,
        fontSize = 15.sp
    )
    Text(
        text = "${invitatory.psalm.number}\n${invitatory.psalm.title}",
        color = MaterialTheme.colorScheme.secondary,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
    )
    Text(
        text = invitatory.psalm.subtitle,
        fontStyle = FontStyle.Italic,
        fontSize = 14.sp
    )
    Text(
        text = invitatory.psalm.breviaryPages ?: "",
        fontSize = 11.sp,
        modifier = Modifier.padding(vertical = 16.dp)
    )
    Text(
        text = invitatory.psalm.text,
        fontSize = 15.sp,
        modifier = Modifier.padding(vertical = 16.dp)
    )
    Text(
        text = invitatory.ending,
        fontSize = 15.sp
    )
}