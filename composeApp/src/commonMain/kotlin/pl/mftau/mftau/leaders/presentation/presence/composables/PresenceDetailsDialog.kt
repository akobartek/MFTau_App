package pl.mftau.mftau.leaders.presentation.presence.composables

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.dp
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.absent
import mftau.composeapp.generated.resources.justified
import mftau.composeapp.generated.resources.meeting_types
import mftau.composeapp.generated.resources.present
import mftau.composeapp.generated.resources.reason_for_absence
import org.jetbrains.compose.resources.stringArrayResource
import org.jetbrains.compose.resources.stringResource
import pl.mftau.mftau.common.presentation.composables.FullScreenDialog
import pl.mftau.mftau.leaders.domain.model.Meeting
import pl.mftau.mftau.leaders.domain.model.MeetingType
import pl.mftau.mftau.leaders.domain.model.PersonPresence
import pl.mftau.mftau.leaders.presentation.meetings.components.MeetingCard
import pl.mftau.mftau.leaders.presentation.meetings.components.MeetingsEmptyListInfo
import kotlin.math.min

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PresenceDetailsDialog(
    personPresence: PersonPresence,
    meetingsMap: Map<MeetingType, List<Meeting>>,
    onDismiss: () -> Unit = {}
) {
    var selectedTab by rememberSaveable { mutableStateOf(0 to false) }

    FullScreenDialog(
        isVisible = true,
        title = personPresence.personName,
        onSave = null,
        onDismiss = onDismiss,
        content = {
            PrimaryTabRow(
                selectedTabIndex = selectedTab.first,
                tabs = {
                    stringArrayResource(Res.array.meeting_types).forEachIndexed { index, type ->
                        Tab(
                            selected = selectedTab.first == index,
                            onClick = { selectedTab = index to (selectedTab.first > index) },
                            text = { Text(text = type) },
                            unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                },
            )
            AnimatedContent(
                targetState = selectedTab.first,
                transitionSpec = {
                    slideIntoContainer(
                        animationSpec = tween(200, easing = EaseIn),
                        towards = if (selectedTab.second) AnimatedContentTransitionScope.SlideDirection.Right else AnimatedContentTransitionScope.SlideDirection.Left
                    ).togetherWith(
                        slideOutOfContainer(
                            animationSpec = tween(200, easing = EaseOut),
                            towards = if (selectedTab.second) AnimatedContentTransitionScope.SlideDirection.Right else AnimatedContentTransitionScope.SlideDirection.Left
                        )
                    )
                },
                label = "presence_details",
                modifier = Modifier.fillMaxSize(),
            ) { targetState ->
                Column {
                    val type = MeetingType.fromIndex(targetState)
                    val meetings = meetingsMap[type] ?: listOf()
                    val colors = type.getProperColors()

                    if (meetings.isNotEmpty()) {
                        Row(
                            modifier = Modifier
                                .padding(vertical = 16.dp, horizontal = 8.dp)
                                .fillMaxWidth()
                                .height(200.dp),
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .drawBehind {
                                        personPresence.presence[type]
                                            ?.toList()
                                            ?.let { list ->
                                                val colorsList = colors.toList()
                                                var startAngle = -90f
                                                val size = min(size.width, size.height)

                                                list.forEachIndexed { index, value ->
                                                    val angle = value * 360
                                                    drawArc(
                                                        color = colorsList[index],
                                                        startAngle = startAngle,
                                                        sweepAngle = angle,
                                                        useCenter = true,
                                                        size = Size(size, size),
                                                        topLeft = Offset(x = 0f, y = 0f)
                                                    )
                                                    startAngle += angle
                                                }
                                            }
                                    },
                            )
                            Column(
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxHeight(),
                            ) {
                                val colorList = colors.toList()
                                val stringRes = listOf(
                                    Res.string.present, Res.string.justified, Res.string.absent
                                )
                                personPresence.presence[type]?.toList()
                                    ?.forEachIndexed { index, value ->
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Spacer(
                                                modifier = Modifier
                                                    .size(8.dp)
                                                    .background(colorList[index])
                                            )
                                            Text(
                                                text = stringResource(
                                                    stringRes[index],
                                                    (value * 100).toInt(), "%"
                                                ),
                                                color = MaterialTheme.colorScheme.onBackground,
                                                style = MaterialTheme.typography.bodySmall,
                                                modifier = Modifier.padding(4.dp),
                                            )
                                        }
                                    }
                            }
                        }

                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            items(meetings, key = { it.id }) { meeting ->
                                val meetingWithoutNotes = meeting.copy(
                                    notes =
                                    if (!meeting.absenceList.containsKey(personPresence.personId)) ""
                                    else stringResource(
                                        Res.string.reason_for_absence,
                                        meeting.absenceList[personPresence.personId] ?: ""
                                    )
                                )
                                val background = when {
                                    meeting.attendanceList.contains(personPresence.personId) -> colors.first
                                    meeting.absenceList.containsKey(personPresence.personId) -> colors.second
                                    else -> colors.third
                                }
                                MeetingCard(meeting = meetingWithoutNotes, background = background)
                            }
                        }
                    } else MeetingsEmptyListInfo()
                }
            }
        }
    )
}