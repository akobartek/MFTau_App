package pl.mftau.mftau.leaders.presentation.components

import androidx.compose.runtime.Composable
import pl.mftau.mftau.R
import pl.mftau.mftau.core.presentation.components.EmptyListInfo

@Composable
fun PeopleEmptyListInfo() {
    EmptyListInfo(messageId = R.string.empty_people_list, drawableId = R.drawable.ic_no_people)
}