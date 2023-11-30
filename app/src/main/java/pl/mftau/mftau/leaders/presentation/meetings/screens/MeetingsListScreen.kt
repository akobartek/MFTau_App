package pl.mftau.mftau.leaders.presentation.meetings.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.launch
import pl.mftau.mftau.R
import pl.mftau.mftau.common.presentation.components.LoadingBox
import pl.mftau.mftau.common.presentation.components.TauCenteredTopBar
import pl.mftau.mftau.common.utils.safePop
import pl.mftau.mftau.leaders.presentation.LeadersScreen
import pl.mftau.mftau.leaders.presentation.meetings.components.MeetingCard
import pl.mftau.mftau.leaders.presentation.meetings.components.MeetingEditorDialog
import pl.mftau.mftau.leaders.presentation.meetings.components.MeetingsEmptyListInfo
import pl.mftau.mftau.leaders.presentation.meetings.components.MeetingsOptionsIcon
import pl.mftau.mftau.leaders.presentation.meetings.screenmodels.MeetingsListScreenModel

class MeetingsListScreen : LeadersScreen() {
    override val key: ScreenKey
        get() = KEY

    @Composable
    override fun Content() {
        MeetingsListScreenContent(getScreenModel())
    }

    companion object {
        const val KEY = "MeetingsListScreen"
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeetingsListScreenContent(screenModel: MeetingsListScreenModel) {
    val navigator = LocalNavigator.currentOrThrow
    val state by screenModel.state.collectAsStateWithLifecycle()
    val selectedTab by screenModel.selectedTabState.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(state.meetingSavedSuccessfully) {
        val savedSuccessfully = state.meetingSavedSuccessfully
        scope.launch {
            if (savedSuccessfully != null) {
                screenModel.toggleMeetingSavedVisibility()
                snackbarHostState.showSnackbar(
                    message = context.getString(
                        if (savedSuccessfully) R.string.meeting_saved
                        else R.string.meeting_save_error
                    ),
                    withDismissAction = true
                )
            }
        }
    }
    LaunchedEffect(state.meetingDeletedSuccessfully) {
        val deletedSuccessfully = state.meetingDeletedSuccessfully
        scope.launch {
            if (deletedSuccessfully != null) {
                screenModel.toggleMeetingDeletedVisibility()
                snackbarHostState.showSnackbar(
                    message = context.getString(
                        if (deletedSuccessfully) R.string.meeting_delete_success
                        else R.string.meeting_delete_error
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
            Column {
                TauCenteredTopBar(
                    title = stringResource(R.string.meetings),
                    onNavClick = { navigator.safePop(MeetingsListScreen.KEY) },
                    actions = {
                        if (state.meetings.isNotEmpty())
                            MeetingsOptionsIcon(
                                onClearMeetings = screenModel::clearMeetings,
                                onShowPresence = {
                                    // TODO
                                }
                            )
                    }
                )
                PrimaryTabRow(
                    selectedTabIndex = selectedTab.first,
                    tabs = {
                        stringArrayResource(id = R.array.meeting_types).forEachIndexed { index, type ->
                            Tab(
                                selected = selectedTab.first == index,
                                onClick = { screenModel.updateSelection(index) },
                                text = { Text(text = type) },
                                unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = screenModel::toggleMeetingEditorVisibility) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add_meeting)
                )
            }
        }
    ) { paddingValues ->
        AnimatedContent(
            targetState = selectedTab,
            transitionSpec = {
                slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = if (selectedTab.second) SlideDirection.Right else SlideDirection.Left
                ).togetherWith(
                    slideOutOfContainer(
                        animationSpec = tween(300, easing = EaseOut),
                        towards = if (selectedTab.second) SlideDirection.Right else SlideDirection.Left
                    )
                )
            },
            label = "meetings",
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) { targetState ->
            if (!state.isLoading) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                ) {
                    items(state.filteredMeetings, key = { it.id + targetState }) { meeting ->
                        MeetingCard(
                            meeting = meeting,
                            onClick = { screenModel.toggleMeetingEditorVisibility(meeting) },
                        )
                    }
                }

                if (state.filteredMeetings.isEmpty())
                    MeetingsEmptyListInfo()
            } else LoadingBox()
        }
    }

    if (state.meetingEditorVisible)
        MeetingEditorDialog(
            meeting = state.meetingToEdit,
            onSave = screenModel::saveMeeting,
            onDelete = screenModel::deleteMeeting,
            onDismiss = screenModel::toggleMeetingEditorVisibility
        )
}