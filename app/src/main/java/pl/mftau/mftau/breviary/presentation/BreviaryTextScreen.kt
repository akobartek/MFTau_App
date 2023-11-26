package pl.mftau.mftau.breviary.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Down
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import pl.mftau.mftau.R
import pl.mftau.mftau.breviary.domain.model.Breviary
import pl.mftau.mftau.breviary.domain.model.Breviary.BreviaryHtml
import pl.mftau.mftau.breviary.domain.model.Breviary.Compline
import pl.mftau.mftau.breviary.domain.model.Breviary.Invitatory
import pl.mftau.mftau.breviary.domain.model.Breviary.MajorHour
import pl.mftau.mftau.breviary.domain.model.Breviary.MinorHour
import pl.mftau.mftau.breviary.domain.model.Breviary.OfficeOfReadings
import pl.mftau.mftau.breviary.domain.model.BreviaryType
import pl.mftau.mftau.breviary.presentation.BreviaryTextScreenModel.State
import pl.mftau.mftau.breviary.presentation.components.BreviaryPartHeader
import pl.mftau.mftau.breviary.presentation.components.BreviaryPartLayout
import pl.mftau.mftau.breviary.presentation.components.CanticleLayout
import pl.mftau.mftau.breviary.presentation.components.MultipleOfficesDialog
import pl.mftau.mftau.breviary.presentation.components.PsalmLayout
import pl.mftau.mftau.breviary.presentation.components.PsalmodyLayout
import pl.mftau.mftau.common.presentation.components.ComposeWebView
import pl.mftau.mftau.common.presentation.components.LoadingBox
import pl.mftau.mftau.common.presentation.components.NoInternetDialog
import pl.mftau.mftau.common.presentation.components.TauCenteredTopBar
import pl.mftau.mftau.core.utils.safePop

data class BreviaryTextScreen(
    val position: Int = 0,
    val date: String = ""
) : BreviaryScreen() {
    override val key: ScreenKey
        get() = KEY

    @Composable
    override fun Content() {
        BreviaryTextScreenContent(
            screenModel = getScreenModel(),
            position = position,
            date = date
        )
    }

    companion object {
        const val KEY = "BreviaryTextScreen"
    }
}

@Composable
fun BreviaryTextScreenContent(screenModel: BreviaryTextScreenModel, position: Int, date: String) {
    val navigator = LocalNavigator.currentOrThrow
    val state by screenModel.state.collectAsStateWithLifecycle().also {
        screenModel.setup(
            type = BreviaryType.fromPosition(position),
            date = date
        )
    }

    Scaffold(
        topBar = {
            TauCenteredTopBar(
                title = stringArrayResource(id = R.array.breviary_list)[position],
                onNavClick = { navigator.safePop(BreviaryTextScreen.KEY) },
                navIcon = Icons.Default.Close
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
                is State.Init, is State.Cancelled -> {}
                is State.Loading -> LoadingBox()

                is State.MultipleOffices -> MultipleOfficesDialog(
                    offices = (state as State.MultipleOffices).offices,
                    onSelect = screenModel::officeSelected,
                    onCancel = {
                        screenModel.cancelScreen()
                        navigator.safePop(BreviaryTextScreen.KEY)
                    }
                )

                is State.BreviaryAvailable -> BreviaryLayout(
                    breviary = (state as State.BreviaryAvailable).breviary
                )

                is State.Failure -> NoInternetDialog(
                    isVisible = true,
                    onReconnect = screenModel::checkIfThereAreMultipleOffices,
                    onDismiss = {
                        screenModel.cancelScreen()
                        navigator.safePop(BreviaryTextScreen.KEY)
                    }
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
        ) {
            when (breviary) {
                is Invitatory -> InvitatoryLayout(invitatory = breviary)
                is OfficeOfReadings -> OfficeOfReadingsLayout(officeOfReadings = breviary)
                is MajorHour -> MajorHourLayout(majorHour = breviary)
                is MinorHour -> MinorHourLayout(minorHour = breviary)
                is Compline -> ComplineLayout(compline = breviary)
                is BreviaryHtml -> ComposeWebView(html = breviary.html)
            }
        }
    }
}

@Composable
private fun InvitatoryLayout(invitatory: Invitatory) {
    Column {
        Text(text = invitatory.opening, fontSize = 15.sp)
        PsalmLayout(psalm = invitatory.psalm, isInvitatoryPsalm = true)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = invitatory.ending, fontSize = 15.sp)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OfficeOfReadingsLayout(officeOfReadings: OfficeOfReadings) {
    var optionSelected by rememberSaveable { mutableIntStateOf(0) }
    val selectedReading =
        if (optionSelected == 0) officeOfReadings.firstReading
        else officeOfReadings.firstReadingVersion2
    val options = listOf("Tekst oficalny (LG)", "Cykl dwuletni")

    Column(verticalArrangement = Arrangement.spacedBy(28.dp)) {
        Text(text = officeOfReadings.opening, fontSize = 15.sp)
        BreviaryPartLayout(title = "Hymn", breviaryPart = officeOfReadings.hymn)
        PsalmodyLayout(psalmody = officeOfReadings.psalmody)
        BreviaryPartLayout(title = "", breviaryPart = officeOfReadings.additionalPart)
        Column {
            BreviaryPartHeader(
                title = "I Czytanie",
                pages = selectedReading.breviaryPages,
                verses = selectedReading.verses
            )
            MultiChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
                options.forEachIndexed { index, option ->
                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = options.size
                        ),
                        icon = {
                            SegmentedButtonDefaults.Icon(
                                active = optionSelected == index,
                                activeContent = {
                                    Icon(
                                        imageVector = Icons.Filled.Done,
                                        contentDescription = null,
                                        modifier = Modifier.size(SegmentedButtonDefaults.IconSize)
                                    )
                                },
                                inactiveContent = null
                            )
                        },
                        onCheckedChange = { optionSelected = index },
                        checked = optionSelected == index
                    ) {
                        Text(option)
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            AnimatedContent(
                targetState = optionSelected,
                transitionSpec = {
                    slideIntoContainer(
                        animationSpec = tween(400, easing = EaseIn),
                        towards = Down
                    ).togetherWith(
                        slideOutOfContainer(
                            animationSpec = tween(400, easing = EaseOut),
                            towards = Down
                        )
                    )
                },
                label = "reading"
            ) { targetState ->
                val text =
                    if (targetState == 0) officeOfReadings.firstReading.text
                    else officeOfReadings.firstReadingVersion2.text
                Text(text = text, fontSize = 15.sp)
            }
        }
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
        BreviaryPartLayout(title = "Hymn", breviaryPart = majorHour.hymn)
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
        BreviaryPartLayout(title = "Hymn", breviaryPart = minorHour.hymn)
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
        BreviaryPartLayout(title = "Hymn", breviaryPart = compline.hymn)
        PsalmodyLayout(psalmody = compline.psalmody)
        BreviaryPartLayout(title = "Czytanie", breviaryPart = compline.reading)
        BreviaryPartLayout(title = "Responsorium krótkie", breviaryPart = compline.responsory)
        CanticleLayout(canticle = compline.canticle)
        BreviaryPartLayout(title = "Modlitwa", breviaryPart = compline.prayer)
        BreviaryPartLayout(title = "Antyfona", breviaryPart = compline.antiphon)
    }
}