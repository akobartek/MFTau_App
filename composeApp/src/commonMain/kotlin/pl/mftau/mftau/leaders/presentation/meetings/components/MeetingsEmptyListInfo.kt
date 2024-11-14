package pl.mftau.mftau.leaders.presentation.meetings.components

import androidx.compose.runtime.Composable
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.empty_meeting_list
import mftau.composeapp.generated.resources.ic_no_meetings
import pl.mftau.mftau.common.presentation.composables.EmptyListInfo

@Composable
fun MeetingsEmptyListInfo() {
    EmptyListInfo(
        messageId = Res.string.empty_meeting_list,
        drawableId = Res.drawable.ic_no_meetings,
    )
}