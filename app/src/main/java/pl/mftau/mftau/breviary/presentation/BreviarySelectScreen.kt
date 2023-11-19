package pl.mftau.mftau.breviary.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import pl.mftau.mftau.R
import pl.mftau.mftau.ui.theme.mfTauFont

class BreviarySelectScreen : BreviaryScreen() {
    @Composable
    override fun Content() {
        BreviarySelectScreenContent(screenModel = getScreenModel())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreviarySelectScreenContent(screenModel: BreviarySelectScreenModel) {
    val navigator = LocalNavigator.currentOrThrow
    var dropDownMenuExpanded by remember { mutableStateOf(false) }

    val daysFromToday by screenModel.daysFromToday.collectAsStateWithLifecycle()
    val daysString = screenModel.getCorrectDaysString(daysFromToday)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                title = {
                    Row {
                        Text(
                            text = stringResource(id = R.string.breviary),
                            fontFamily = mfTauFont
                        )
                        AnimatedContent(
                            targetState = daysString,
                            transitionSpec = {
                                slideIntoContainer(
                                    animationSpec = tween(400, easing = EaseIn),
                                    towards = if (daysFromToday < 0) SlideDirection.Down else SlideDirection.Up
                                ).togetherWith(
                                    slideOutOfContainer(
                                        animationSpec = tween(400, easing = EaseOut),
                                        towards = if (daysFromToday < 0) SlideDirection.Down else SlideDirection.Up
                                    )
                                )
                            },
                            label = "days"
                        ) { text ->
                            Text(text = text, fontFamily = mfTauFont)
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = navigator::pop) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.cd_back_arrow_btn)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navigator.push(BreviarySaveScreen(screenModel.dateString))
                    }) {
                        Icon(imageVector = Icons.Filled.Save, contentDescription = "Lock")
                    }

                    IconButton(onClick = { dropDownMenuExpanded = true }) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "Options"
                        )
                    }

                    DropdownMenu(
                        expanded = dropDownMenuExpanded,
                        onDismissRequest = { dropDownMenuExpanded = false },
                        modifier = Modifier.defaultMinSize(minWidth = 200.dp)
                    ) {
                        if (daysFromToday != -1)
                            DropdownMenuItem(
                                text = { Text(text = stringResource(id = R.string.breviary_yesterday)) },
                                onClick = {
                                    dropDownMenuExpanded = false
                                    screenModel.updateDaysFromToday(-1)
                                }
                            )
                        if (daysFromToday != 0)
                            DropdownMenuItem(
                                text = { Text(text = stringResource(id = R.string.breviary_today)) },
                                onClick = {
                                    dropDownMenuExpanded = false
                                    screenModel.updateDaysFromToday(0)
                                }
                            )
                        if (daysFromToday != 1)
                            DropdownMenuItem(
                                text = { Text(text = stringResource(id = R.string.breviary_tomorrow)) },
                                onClick = {
                                    dropDownMenuExpanded = false
                                    screenModel.updateDaysFromToday(1)
                                }
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
            stringArrayResource(id = R.array.breviary_list).forEachIndexed { index, elem ->
                Column(
                    Modifier.clickable {
                        navigator.push(BreviaryTextScreen(index, screenModel.dateString))
                    }
                ) {
                    Text(
                        text = elem,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(12.dp)
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}