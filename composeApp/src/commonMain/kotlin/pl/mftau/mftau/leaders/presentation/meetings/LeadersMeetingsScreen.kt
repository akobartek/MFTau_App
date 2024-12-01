package pl.mftau.mftau.leaders.presentation.meetings

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
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.add_meeting
import mftau.composeapp.generated.resources.meeting_types
import mftau.composeapp.generated.resources.meetings
import org.jetbrains.compose.resources.stringArrayResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import pl.mftau.mftau.common.presentation.composables.LoadingBox
import pl.mftau.mftau.common.presentation.composables.TauCenteredTopBar
import pl.mftau.mftau.leaders.domain.model.Meeting
import pl.mftau.mftau.leaders.domain.model.MeetingType
import pl.mftau.mftau.leaders.presentation.meetings.components.MeetingCard
import pl.mftau.mftau.leaders.presentation.meetings.components.MeetingEditorDialog
import pl.mftau.mftau.leaders.presentation.meetings.components.MeetingsEmptyListInfo
import pl.mftau.mftau.leaders.presentation.meetings.components.MeetingsOptionsIcon

@Composable
fun LeadersMeetingsScreen(
    navigateUp: () -> Unit,
    openPresenceScreen: () -> Unit,
    viewModel: LeadersMeetingsViewModel = koinInject(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val selectedTab by viewModel.selectedTabState.collectAsStateWithLifecycle()

    LeadersMeetingsScreenContent(
        navigateUp = navigateUp,
        openPresenceScreen = openPresenceScreen,
        state = state,
        selectedTab = selectedTab,
        saveMeeting = viewModel::saveMeeting,
        deleteMeeting = viewModel::deleteMeeting,
        toggleMeetingEditorVisibility = viewModel::toggleMeetingEditorVisibility,
        clearMeetings = viewModel::clearMeetings,
        updateSelection = viewModel::updateSelection,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeadersMeetingsScreenContent(
    navigateUp: () -> Unit,
    openPresenceScreen: () -> Unit,
    state: LeadersMeetingsScreenState,
    selectedTab: Pair<Int, Boolean>,
    saveMeeting: (Meeting) -> Unit,
    deleteMeeting: (Meeting?) -> Unit,
    toggleMeetingEditorVisibility: (Meeting?) -> Unit,
    clearMeetings: () -> Unit,
    updateSelection: (Int) -> Unit,
) {
    Scaffold(
        topBar = {
            Column {
                TauCenteredTopBar(
                    title = stringResource(Res.string.meetings),
                    onNavClick = navigateUp,
                    actions = {
                        if (state.meetings.isNotEmpty())
                            MeetingsOptionsIcon(
                                showPresenceVisible = state.people.isNotEmpty(),
                                onClearMeetings = clearMeetings,
                                onShowPresence = openPresenceScreen,
                            )
                    }
                )
                PrimaryTabRow(
                    selectedTabIndex = selectedTab.first,
                    tabs = {
                        stringArrayResource(Res.array.meeting_types).forEachIndexed { index, type ->
                            Tab(
                                selected = selectedTab.first == index,
                                onClick = { updateSelection(index) },
                                text = { Text(text = type) },
                                unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { toggleMeetingEditorVisibility(null) },
                containerColor = MaterialTheme.colorScheme.primary,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(Res.string.add_meeting)
                )
            }
        }
    ) { paddingValues ->
        AnimatedContent(
            targetState = selectedTab.first,
            transitionSpec = {
                slideIntoContainer(
                    animationSpec = tween(200, easing = EaseIn),
                    towards = if (selectedTab.second) SlideDirection.Right else SlideDirection.Left
                ).togetherWith(
                    slideOutOfContainer(
                        animationSpec = tween(200, easing = EaseOut),
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
                val meetings = state.meetings[MeetingType.fromIndex(targetState)] ?: listOf()
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(items = meetings, key = { it.id }) { meeting ->
                        MeetingCard(
                            meeting = meeting,
                            onClick = { toggleMeetingEditorVisibility(meeting) },
                        )
                    }
                }

                if (meetings.isEmpty())
                    MeetingsEmptyListInfo()
            } else LoadingBox()
        }
    }

    if (state.meetingEditorVisible)
        MeetingEditorDialog(
            meeting = state.meetingToEdit,
            people = state.people,
            currentTab = selectedTab.first,
            onSave = saveMeeting,
            onDelete = deleteMeeting,
            onDismiss = { toggleMeetingEditorVisibility(null) },
        )
}