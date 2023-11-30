package pl.mftau.mftau.leaders.presentation.people.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.launch
import pl.mftau.mftau.R
import pl.mftau.mftau.common.presentation.components.RevealAnimatedContent
import pl.mftau.mftau.common.presentation.components.TauCenteredTopBar
import pl.mftau.mftau.common.utils.copyToClipboard
import pl.mftau.mftau.common.utils.safePop
import pl.mftau.mftau.core.presentation.components.EmptyListInfo
import pl.mftau.mftau.leaders.presentation.people.components.EmausCard
import pl.mftau.mftau.leaders.presentation.people.components.EmausFullListDialog
import pl.mftau.mftau.leaders.presentation.people.components.EmausNoPeopleErrorDialog
import pl.mftau.mftau.leaders.presentation.people.components.EmausOptionsIcon
import pl.mftau.mftau.leaders.presentation.people.components.PersonCard
import pl.mftau.mftau.leaders.presentation.people.screenmodels.EmausScreenModel

class EmausScreen : LeadersScreen() {
    override val key: ScreenKey
        get() = KEY

    @Composable
    override fun Content() {
        EmausScreenContent(getScreenModel())
    }

    companion object {
        const val KEY = "EmausScreen"
        const val ANIM_DURATION = 277
    }
}

@Composable
fun EmausScreenContent(screenModel: EmausScreenModel) {
    val navigator = LocalNavigator.currentOrThrow
    val context = LocalContext.current
    val state by screenModel.state.collectAsStateWithLifecycle()
    var fabOffset by remember { mutableStateOf(Offset.Zero) }

    val peopleWithoutDraw = state.people.filter { person ->
        state.lastDraw.all { it.person1 != person.id && it.person2 != person.id }
    }
    val unknownString = context.getString(R.string.deleted_person)
    val emauses = state.lastDraw.map { emaus ->
        emaus.copy(
            person1 = state.people.firstOrNull { it.id == emaus.person1 }?.name ?: unknownString,
            person2 = state.people.firstOrNull { it.id == emaus.person2 }?.name ?: unknownString
        )
    }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(state.deleteMessageVisible) {
        val deletedSuccessfully = state.deleteMessageVisible
        scope.launch {
            if (deletedSuccessfully != null) {
                screenModel.toggleDeleteMessageVisibility()
                snackbarHostState.showSnackbar(
                    message = context.getString(
                        if (deletedSuccessfully) R.string.draw_delete_success
                        else R.string.draw_delete_error
                    ),
                    withDismissAction = true
                )
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TauCenteredTopBar(
                title = stringResource(R.string.emaus),
                navIcon = Icons.Default.Close,
                onNavClick = { navigator.safePop(EmausScreen.KEY) },
                actions = {
                    if (state.lastDraw.isNotEmpty())
                        EmausOptionsIcon(
                            onCopyDraws = {
                                val emausesString = buildString {
                                    emauses.forEach {
                                        appendLine("${it.person1} + ${it.person2}")
                                    }
                                }
                                context.copyToClipboard(emausesString, "playlist")
                            },
                            onDeleteLastDraw = { screenModel.deleteDraws(false) },
                            onResetDraws = { screenModel.deleteDraws(true) }
                        )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = screenModel::startDraw,
                modifier = Modifier.onGloballyPositioned {
                    fabOffset = it.positionInParent()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_draws),
                    contentDescription = stringResource(id = R.string.draw_start),
                    modifier = Modifier.size(24.dp)
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
                    .fillMaxSize()
            ) {
                if (peopleWithoutDraw.isNotEmpty()) {
                    item(
                        key = "PEOPLE_WITHOUT_DRAW_HEADER",
                        span = { GridItemSpan(this.maxLineSpan) }
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.not_drawn_header),
                                style = MaterialTheme.typography.headlineSmall,
                                textAlign = TextAlign.Center,
                            )
                            Text(
                                text = stringResource(id = R.string.not_drawn_sub_header),
                                style = MaterialTheme.typography.bodySmall,
                                textAlign = TextAlign.Center,
                            )
                        }

                    }
                    items(
                        count = peopleWithoutDraw.size,
                        key = { peopleWithoutDraw[it].id }
                    ) { index ->
                        val person = peopleWithoutDraw[index]
                        PersonCard(
                            person = person,
                            showOnlyName = true
                        )
                    }
                }
                item(
                    key = "DRAWS_HEADER",
                    span = { GridItemSpan(this.maxLineSpan) }
                ) {
                    Text(
                        text = stringResource(id = R.string.draws_header),
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(8.dp)
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
            messageId = R.string.draws_empty_list,
            drawableId = R.drawable.ic_draws
        )
    }

    RevealAnimatedContent(
        offset = fabOffset,
        isContentVisible = state.drawInProgress,
        duration = EmausScreen.ANIM_DURATION,
        content = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_draws),
                    contentDescription = null
                )
            }
        }
    )

    EmausNoPeopleErrorDialog(
        isVisible = state.noPeopleErrorVisible,
        onDismiss = screenModel::toggleNoPeopleErrorVisibility
    )

    EmausFullListDialog(
        isVisible = state.drawErrorMessageVisible,
        onReset = { screenModel.deleteDraws(true) },
        onDismiss = screenModel::toggleDrawErrorMessageVisibility
    )
}