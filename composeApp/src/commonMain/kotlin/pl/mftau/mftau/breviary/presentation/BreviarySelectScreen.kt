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
import org.jetbrains.compose.resources.stringArrayResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import pl.mftau.mftau.breviary.presentation.composables.BreviarySelectOptionsMenu

@Composable
fun BreviarySelectScreen(
    onBackPressed: () -> Unit,
    onSelected: (Int, String) -> Unit,
    onSaveBreviary: (String) -> Unit,
    viewModel: BreviarySelectViewModel = koinInject()
) {
    var dropDownMenuExpanded by rememberSaveable { mutableStateOf(false) }
    val daysFromToday by viewModel.daysFromToday.collectAsStateWithLifecycle()
    val dateString = viewModel.getCorrectDaysString(daysFromToday)

    ScreenLayout(
        title = stringResource(Res.string.breviary_title) + dateString,
        onBackPressed = onBackPressed,
        actionIcon = {
            IconButton(onClick = { dropDownMenuExpanded = true }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    tint = wolczynColors.primary,
                    contentDescription = stringResource(Res.string.cd_more_options)
                )
                BreviarySelectOptionsMenu(
                    expanded = dropDownMenuExpanded,
                    daysFromToday = daysFromToday,
                    onDismissRequest = { dropDownMenuExpanded = false },
                    onSaveBreviary = { onSaveBreviary(viewModel.dateString) },
                    onUpdateDays = viewModel::updateDaysFromToday
                )
            }
        }
    ) {
        BreviarySelectScreenContent(onSelected = { onSelected(it, viewModel.dateString) })
    }
}

@Composable
fun BreviarySelectScreenContent(
    onSelected: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        stringArrayResource(Res.array.breviary_list).forEachIndexed { index, elem ->
            Column(modifier = Modifier.clickable { onSelected(index) }) {
                WolczynText(
                    text = elem,
                    textStyle = MaterialTheme.typography.titleMedium.copy(
                        color = wolczynColors.primary,
                    ),
                    modifier = Modifier.padding(12.dp)
                )
                HorizontalDivider()
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        WolczynText(
            text = stringResource(Res.string.breviary_copyright),
            textStyle = MaterialTheme.typography.bodySmall.copy(
                textAlign = TextAlign.Center,
                color = wolczynColors.primary,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}