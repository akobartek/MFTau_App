package pl.mftau.mftau.leaders.presentation.people.components

import androidx.compose.runtime.Composable
import mftau.composeapp.generated.resources.Res
import mftau.composeapp.generated.resources.empty_people_list
import mftau.composeapp.generated.resources.ic_no_people
import pl.mftau.mftau.common.presentation.composables.EmptyListInfo

@Composable
fun PeopleEmptyListInfo() {
    EmptyListInfo(
        messageId = Res.string.empty_people_list,
        drawableId = Res.drawable.ic_no_people,
    )
}