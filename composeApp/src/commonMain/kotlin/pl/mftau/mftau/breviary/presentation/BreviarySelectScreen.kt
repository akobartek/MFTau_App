package pl.mftau.mftau.breviary.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.breviary
import mftau.composeapp.generated.resources.breviary_copyright
import mftau.composeapp.generated.resources.breviary_list
import mftau.composeapp.generated.resources.cd_more_options_btn
import org.jetbrains.compose.resources.stringArrayResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import pl.mftau.mftau.Screen
import pl.mftau.mftau.breviary.presentation.composables.BreviarySelectOptionsMenu
import pl.mftau.mftau.common.presentation.composables.TauCenteredTopBar

@Composable
fun BreviarySelectScreen(
    navigateUp: () -> Unit,
    navigate: (Screen) -> Unit,
    viewModel: BreviarySelectViewModel = koinInject()
) {
    val daysFromToday by viewModel.daysFromToday.collectAsStateWithLifecycle()

    BreviarySelectScreenContent(
        title = stringResource(Res.string.breviary) + viewModel.getCorrectDaysString(daysFromToday),
        navigateUp = navigateUp,
        onSelected = { position -> navigate(Screen.BreviaryText(position, viewModel.dateString)) },
        onSaveBreviary = { navigate(Screen.BreviarySave(viewModel.dateString)) },
        daysFromToday = daysFromToday,
        updateDaysFromToday = viewModel::updateDaysFromToday,
    )
}

@Composable
fun BreviarySelectScreenContent(
    title: String,
    navigateUp: () -> Unit,
    onSelected: (Int) -> Unit,
    onSaveBreviary: () -> Unit,
    daysFromToday: Int,
    updateDaysFromToday: (Int) -> Unit,
) {
    var dropDownMenuExpanded by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TauCenteredTopBar(
                title = title,
                onNavClick = navigateUp,
                actions = {
                    IconButton(onClick = { dropDownMenuExpanded = true }) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = stringResource(Res.string.cd_more_options_btn)
                        )
                        BreviarySelectOptionsMenu(
                            expanded = dropDownMenuExpanded,
                            daysFromToday = daysFromToday,
                            onDismissRequest = { dropDownMenuExpanded = false },
                            onSaveBreviary = onSaveBreviary,
                            onUpdateDays = updateDaysFromToday,
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            stringArrayResource(Res.array.breviary_list).forEachIndexed { index, elem ->
                Column(modifier = Modifier.clickable { onSelected(index) }) {
                    Text(
                        text = elem,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(12.dp),
                    )
                    HorizontalDivider()
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(Res.string.breviary_copyright),
                style = MaterialTheme.typography.bodySmall.copy(
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            )
        }
    }
}