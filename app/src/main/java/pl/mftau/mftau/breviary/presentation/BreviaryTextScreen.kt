package pl.mftau.mftau.breviary.presentation

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Down
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Up
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import pl.mftau.mftau.R
import pl.mftau.mftau.breviary.model.Breviary
import pl.mftau.mftau.breviary.model.BreviaryType
import pl.mftau.mftau.breviary.model.Invitatory
import pl.mftau.mftau.breviary.model.MajorHour
import pl.mftau.mftau.breviary.model.BreviaryPart
import pl.mftau.mftau.breviary.model.Canticle
import pl.mftau.mftau.breviary.model.Compline
import pl.mftau.mftau.breviary.model.MinorHour
import pl.mftau.mftau.breviary.model.OfficeOfReadings
import pl.mftau.mftau.breviary.model.Psalm
import pl.mftau.mftau.breviary.model.Psalmody
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
        val state by screenModel.state.collectAsStateWithLifecycle()

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
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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

                    is BreviaryScreenModel.State.BreviaryAvailable -> BreviaryLayout(
                        breviary = (state as BreviaryScreenModel.State.BreviaryAvailable).breviary
                    )

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
        modifier = Modifier.heightIn(max = 560.dp)
    )
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
                else -> Text(text = "SUCCESS")
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
    var optionSelected by remember { mutableIntStateOf(0) }
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

@Composable
fun BreviaryPartHeader(title: String, pages: String, verses: String = "") {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (title.isNotBlank())
                Text(
                    text = title.uppercase(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary
                )
            Text(
                text = verses,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary
            )
        }
        Text(text = pages, fontSize = 10.sp)
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
private fun BreviaryPartLayout(title: String, breviaryPart: BreviaryPart) {
    Column {
        BreviaryPartHeader(
            title = title,
            pages = breviaryPart.breviaryPages,
            verses = breviaryPart.verses
        )
        Text(text = breviaryPart.text, fontSize = 15.sp)
    }
}

@Composable
private fun PsalmodyLayout(psalmody: Psalmody) {
    Column {
        BreviaryPartHeader(title = "Psalmodia", pages = psalmody.breviaryPages)
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            psalmody.psalms.forEach { PsalmLayout(psalm = it) }
        }
    }
}

@Composable
private fun PsalmLayout(psalm: Psalm, isInvitatoryPsalm: Boolean = false) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        if (!isInvitatoryPsalm)
            Text(
                text = psalm.antiphon1,
                fontSize = 15.sp,
            )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (psalm.name != null)
                Text(
                    text = "${psalm.name}${if (psalm.title != null) "\n${psalm.title}" else ""}",
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            if (psalm.subtitle != null)
                Text(
                    text = psalm.subtitle!!,
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Italic,
                    fontSize = 13.sp,
                    modifier = Modifier.fillMaxWidth(0.9f)
                )
        }
        if (psalm.breviaryPages != null)
            Text(
                text = psalm.breviaryPages!!,
                fontSize = 10.sp
            )
        if (psalm.part != null)
            Text(
                text = psalm.part!!,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                modifier = Modifier.fillMaxWidth()
            )
        Text(
            text = psalm.text,
            fontSize = 15.sp,
        )
        if (!isInvitatoryPsalm)
            Text(
                text = psalm.antiphon2,
                fontSize = 15.sp,
            )
    }
}

@Composable
private fun CanticleLayout(canticle: Canticle) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        BreviaryPartHeader(
            title = canticle.name,
            pages = canticle.breviaryPages,
            verses = canticle.verses
        )
        Text(
            text = canticle.antiphon1,
            fontSize = 15.sp,
        )
        Text(
            text = canticle.text,
            fontSize = 15.sp,
        )
        Text(
            text = canticle.antiphon2,
            fontSize = 15.sp,
        )
    }
}