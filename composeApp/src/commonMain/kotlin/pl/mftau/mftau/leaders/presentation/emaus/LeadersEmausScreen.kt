package pl.mftau.mftau.leaders.presentation.emaus

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.deleted_person
import mftau.composeapp.generated.resources.draw_start
import mftau.composeapp.generated.resources.draws_empty_list
import mftau.composeapp.generated.resources.draws_header
import mftau.composeapp.generated.resources.emaus
import mftau.composeapp.generated.resources.ic_draws
import mftau.composeapp.generated.resources.not_drawn_header
import mftau.composeapp.generated.resources.not_drawn_sub_header
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.koinInject
import pl.mftau.mftau.common.presentation.composables.EmptyListInfo
import pl.mftau.mftau.common.presentation.composables.RevealAnimatedContent
import pl.mftau.mftau.common.presentation.composables.TauCenteredTopBar
import pl.mftau.mftau.common.presentation.snackbars.SnackbarController
import pl.mftau.mftau.common.presentation.snackbars.SnackbarEvent
import pl.mftau.mftau.leaders.presentation.emaus.composables.EmausCard
import pl.mftau.mftau.leaders.presentation.emaus.composables.EmausFullListDialog
import pl.mftau.mftau.leaders.presentation.emaus.composables.EmausNoPeopleErrorDialog
import pl.mftau.mftau.leaders.presentation.emaus.composables.EmausOptionsIcon
import pl.mftau.mftau.leaders.presentation.people.components.PersonCard

const val ANIM_DURATION = 277

@Composable
fun LeadersEmausScreen(
    navigateUp: () -> Unit,
    viewModel: LeadersEmausViewModel = koinInject(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LeadersEmausScreenContent(
        navigateUp = navigateUp,
        state = state,
        startDraw = viewModel::startDraw,
        deleteDraws = viewModel::deleteDraws,
        toggleNoPeopleErrorVisibility = viewModel::toggleNoPeopleErrorVisibility,
        toggleDrawErrorMessageVisibility = viewModel::toggleDrawErrorMessageVisibility,
    )
}

@Composable
fun LeadersEmausScreenContent(
    navigateUp: () -> Unit,
    state: LeadersEmausScreenState,
    startDraw: () -> Unit,
    deleteDraws: (Boolean) -> Unit,
    toggleNoPeopleErrorVisibility: () -> Unit,
    toggleDrawErrorMessageVisibility: () -> Unit,
) {
    val clipboard = LocalClipboardManager.current
    val coroutineScope = rememberCoroutineScope()
    var fabOffset by remember { mutableStateOf(Offset.Zero) }

    val peopleWithoutDraw = state.people.filter { person ->
        state.lastDraw.all { it.person1 != person.id && it.person2 != person.id }
    }
    val unknownString = stringResource(Res.string.deleted_person)
    val emauses = state.lastDraw.map { emaus ->
        emaus.copy(
            person1 = state.people.firstOrNull { it.id == emaus.person1 }?.name ?: unknownString,
            person2 = state.people.firstOrNull { it.id == emaus.person2 }?.name ?: unknownString
        )
    }

    Scaffold(
        topBar = {
            TauCenteredTopBar(
                title = stringResource(Res.string.emaus),
                navIcon = Icons.Default.Close,
                onNavClick = navigateUp,
                actions = {
                    if (state.lastDraw.isNotEmpty())
                        EmausOptionsIcon(
                            onCopyDraws = {
                                val emausesString = buildAnnotatedString {
                                    emauses.forEach {
                                        appendLine("${it.person1} + ${it.person2}")
                                    }
                                }
                                clipboard.setText(emausesString)
                                coroutineScope.launch {
                                    SnackbarController.sendEvent(SnackbarEvent.CopiedToClipboard)
                                }
                            },
                            onDeleteLastDraw = { deleteDraws(false) },
                            onResetDraws = { deleteDraws(true) },
                        )
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = startDraw,
                modifier = Modifier.onGloballyPositioned {
                    if (it.isAttached)
                        fabOffset = it.positionInParent()
                },
            ) {
                Icon(
                    imageVector = vectorResource(Res.drawable.ic_draws),
                    contentDescription = stringResource(Res.string.draw_start),
                    modifier = Modifier.size(24.dp),
                )
            }
        }
    ) { paddingValues ->
        if (state.lastDraw.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
            ) {
                if (peopleWithoutDraw.isNotEmpty()) {
                    item(
                        key = "PEOPLE_WITHOUT_DRAW_HEADER",
                        span = { GridItemSpan(this.maxLineSpan) },
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(8.dp),
                        ) {
                            Text(
                                text = stringResource(Res.string.not_drawn_header),
                                style = MaterialTheme.typography.headlineSmall,
                                textAlign = TextAlign.Center,
                            )
                            Text(
                                text = stringResource(Res.string.not_drawn_sub_header),
                                style = MaterialTheme.typography.bodySmall,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                    items(
                        count = peopleWithoutDraw.size,
                        key = { peopleWithoutDraw[it].id },
                    ) { index ->
                        val person = peopleWithoutDraw[index]
                        PersonCard(
                            person = person,
                            showOnlyName = true,
                        )
                    }
                }
                item(
                    key = "DRAWS_HEADER",
                    span = { GridItemSpan(this.maxLineSpan) },
                ) {
                    Text(
                        text = stringResource(Res.string.draws_header),
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(8.dp),
                    )
                }
                items(
                    count = emauses.size,
                    key = { emauses[it].id },
                    span = { GridItemSpan(2) }
                ) { index ->
                    EmausCard(emaus = emauses[index])
                }
            }
        } else EmptyListInfo(
            messageId = Res.string.draws_empty_list,
            drawableId = Res.drawable.ic_draws,
        )
    }

    RevealAnimatedContent(
        offset = fabOffset,
        isContentVisible = state.drawInProgress,
        duration = ANIM_DURATION,
        content = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary),
            ) {
                Image(
                    painter = painterResource(Res.drawable.ic_draws),
                    contentDescription = null,
                )
            }
        }
    )

    EmausNoPeopleErrorDialog(
        isVisible = state.noPeopleErrorVisible,
        onDismiss = toggleNoPeopleErrorVisibility,
    )

    EmausFullListDialog(
        isVisible = state.drawErrorMessageVisible,
        onReset = { deleteDraws(true) },
        onDismiss = toggleDrawErrorMessageVisibility,
    )
}