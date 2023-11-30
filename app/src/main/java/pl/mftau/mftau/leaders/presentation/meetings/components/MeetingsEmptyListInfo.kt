package pl.mftau.mftau.leaders.presentation.meetings.components

import androidx.compose.runtime.Composable
import pl.mftau.mftau.R
import pl.mftau.mftau.core.presentation.components.EmptyListInfo

@Composable
fun MeetingsEmptyListInfo() {
    EmptyListInfo(messageId = R.string.empty_meeting_list, drawableId = R.drawable.ic_no_meetings)
}