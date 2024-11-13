package pl.mftau.mftau.breviary.presentation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.breviary_list
import org.jetbrains.compose.resources.stringArrayResource
import org.koin.compose.koinInject
import pl.mftau.mftau.breviary.domain.model.Breviary
import pl.mftau.mftau.breviary.domain.model.Breviary.BreviaryHtml
import pl.mftau.mftau.breviary.domain.model.Breviary.Compline
import pl.mftau.mftau.breviary.domain.model.Breviary.Invitatory
import pl.mftau.mftau.breviary.domain.model.Breviary.MajorHour
import pl.mftau.mftau.breviary.domain.model.Breviary.MinorHour
import pl.mftau.mftau.breviary.domain.model.Breviary.OfficeOfReadings
import pl.mftau.mftau.breviary.presentation.BreviaryTextViewModel.State
import pl.mftau.mftau.breviary.presentation.composables.BreviaryPartLayout
import pl.mftau.mftau.breviary.presentation.composables.BreviaryPartWithSelectionLayout
import pl.mftau.mftau.breviary.presentation.composables.CanticleLayout
import pl.mftau.mftau.breviary.presentation.composables.HymnLayout
import pl.mftau.mftau.breviary.presentation.composables.MultipleOfficesDialog
import pl.mftau.mftau.breviary.presentation.composables.PsalmLayout
import pl.mftau.mftau.breviary.presentation.composables.PsalmodyLayout
import pl.mftau.mftau.common.presentation.composables.ComposeWebView
import pl.mftau.mftau.common.presentation.composables.HeightSpacer
import pl.mftau.mftau.common.presentation.composables.LoadingBox
import pl.mftau.mftau.common.presentation.composables.NoInternetDialog

@Composable
fun BreviaryTextScreen(
    onBackPressed: () -> Unit,
    position: Int,
    date: String,
    viewModel: BreviaryTextViewModel = koinInject()
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()
    val accentColor = MaterialTheme.colorScheme.primary

    LaunchedEffect(Unit) {
        viewModel.setup(position, date, accentColor)
    }

    ScreenLayout(
        title = stringArrayResource(Res.array.breviary_list)[position],
        onBackPressed = onBackPressed
    ) {
        BreviaryTextScreenContent(
            state = screenState,
            onOfficeSelected = viewModel::officeSelected,
            onReconnect = viewModel::checkIfThereAreMultipleOffices,
            onCancel = {
                viewModel.cancelScreen()
                onBackPressed()
            }
        )
    }
}

@Composable
fun BreviaryTextScreenContent(
    state: State,
    onOfficeSelected: (String) -> Unit,
    onReconnect: () -> Unit,
    onCancel: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is State.Init, is State.Cancelled -> {}
            is State.Loading -> LoadingBox()

            is State.MultipleOffices -> MultipleOfficesDialog(
                offices = state.offices,
                onSelect = onOfficeSelected,
                onCancel = onCancel
            )

            is State.BreviaryAvailable -> {
                BreviaryLayout(breviary = state.breviary)
            }

            is State.Failure -> {
                NoInternetDialog(
                    isVisible = true,
                    onReconnect = onReconnect,
                    onDismiss = onCancel
                )
            }
        }
    }
}

@Composable
private fun BreviaryLayout(breviary: Breviary) {
    SelectionContainer {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = 32.dp)
                .padding(horizontal = if (breviary is BreviaryHtml) 0.dp else 8.dp)
        ) {
            when (breviary) {
                is Invitatory -> InvitatoryLayout(invitatory = breviary)
                is OfficeOfReadings -> OfficeOfReadingsLayout(officeOfReadings = breviary)
                is MajorHour -> MajorHourLayout(majorHour = breviary)
                is MinorHour -> MinorHourLayout(minorHour = breviary)
                is Compline -> ComplineLayout(compline = breviary)
                is BreviaryHtml ->
                    ComposeWebView(html = breviary.getCorrectedHtml(isSystemInDarkTheme()))
            }
        }
    }
}

@Composable
private fun InvitatoryLayout(invitatory: Invitatory) {
    Column {
        Text(text = invitatory.opening, fontSize = 15.sp)
        PsalmLayout(psalm = invitatory.psalm, isInvitatoryPsalm = true)
        HeightSpacer(16.dp)
        Text(text = invitatory.ending, fontSize = 15.sp)
    }
}

@Composable
fun OfficeOfReadingsLayout(officeOfReadings: OfficeOfReadings) {
    var optionSelected by rememberSaveable { mutableIntStateOf(0) }
    val selectedReading =
        if (optionSelected == 0) officeOfReadings.firstReading
        else officeOfReadings.firstReadingVersion2
    val options = listOf("Tekst oficalny (LG)", "Cykl dwuletni")

    Column(verticalArrangement = Arrangement.spacedBy(28.dp)) {
        Text(text = officeOfReadings.opening, fontSize = 15.sp)
        HymnLayout(hymn = officeOfReadings.hymn)
        PsalmodyLayout(psalmody = officeOfReadings.psalmody)
        BreviaryPartLayout(title = "", breviaryPart = officeOfReadings.additionalPart)
        BreviaryPartWithSelectionLayout(
            title = "I Czytanie",
            breviaryPages = selectedReading.breviaryPages,
            verses = selectedReading.verses,
            optionSelected = optionSelected,
            options = options,
            texts = listOf(
                officeOfReadings.firstReading.text,
                officeOfReadings.firstReadingVersion2.text
            ),
            onOptionSelected = { optionSelected = it }
        )
        BreviaryPartLayout(title = "Responsorium", breviaryPart = officeOfReadings.firstResponsory)
        BreviaryPartLayout(title = "II Czytanie", breviaryPart = officeOfReadings.secondReading)
        BreviaryPartLayout(title = "Responsorium", breviaryPart = officeOfReadings.secondResponsory)
        if (officeOfReadings.teDeum != null)
            BreviaryPartLayout(title = "Te Deum", breviaryPart = officeOfReadings.teDeum)
        BreviaryPartLayout(title = "Modlitwa", breviaryPart = officeOfReadings.prayer)
        Text(text = officeOfReadings.ending, fontSize = 15.sp)
    }
}

@Composable
private fun MajorHourLayout(majorHour: MajorHour) {
    Column(verticalArrangement = Arrangement.spacedBy(28.dp)) {
        Text(text = majorHour.opening, fontSize = 15.sp)
        HymnLayout(hymn = majorHour.hymn)
        PsalmodyLayout(psalmody = majorHour.psalmody)
        BreviaryPartLayout(title = "Czytanie", breviaryPart = majorHour.reading)
        BreviaryPartLayout(title = "Responsorium krótkie", breviaryPart = majorHour.responsory)
        CanticleLayout(canticle = majorHour.canticle)
        BreviaryPartLayout(title = "Prośby", breviaryPart = majorHour.intercessions)
        Text(text = majorHour.lordsPrayer, fontSize = 15.sp)
        BreviaryPartLayout(title = "Modlitwa", breviaryPart = majorHour.prayer)
        Text(text = majorHour.ending, fontSize = 15.sp)
    }
}

@Composable
private fun MinorHourLayout(minorHour: MinorHour) {
    Column(verticalArrangement = Arrangement.spacedBy(28.dp)) {
        Text(text = minorHour.opening, fontSize = 15.sp)
        HymnLayout(hymn = minorHour.hymn)
        PsalmodyLayout(psalmody = minorHour.psalmody)
        BreviaryPartLayout(title = "Czytanie", breviaryPart = minorHour.reading)
        BreviaryPartLayout(title = "Modlitwa", breviaryPart = minorHour.prayer)
        Text(text = minorHour.ending, fontSize = 15.sp)
    }
}

@Composable
private fun ComplineLayout(compline: Compline) {
    Column(verticalArrangement = Arrangement.spacedBy(28.dp)) {
        Text(text = compline.opening, fontSize = 15.sp)
        HymnLayout(hymn = compline.hymn)
        PsalmodyLayout(psalmody = compline.psalmody)
        BreviaryPartLayout(title = "Czytanie", breviaryPart = compline.reading)
        BreviaryPartLayout(title = "Responsorium krótkie", breviaryPart = compline.responsory)
        CanticleLayout(canticle = compline.canticle)
        BreviaryPartLayout(title = "Modlitwa", breviaryPart = compline.prayer)
        BreviaryPartLayout(title = "Antyfona", breviaryPart = compline.antiphon)
    }
}