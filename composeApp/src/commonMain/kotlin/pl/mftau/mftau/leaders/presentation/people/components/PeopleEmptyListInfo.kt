package pl.mftau.mftau.leaders.presentation.people.components

import androidx.compose.runtime.Composable
import pl.mftau.mftau.R
import pl.mftau.mftau.common.presentation.composables.EmptyListInfo

@Composable
fun PeopleEmptyListInfo() {
    EmptyListInfo(messageId = R.string.empty_people_list, drawableId = R.drawable.ic_no_people)
}