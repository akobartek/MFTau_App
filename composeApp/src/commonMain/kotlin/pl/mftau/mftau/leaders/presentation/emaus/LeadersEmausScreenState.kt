package pl.mftau.mftau.leaders.presentation.emaus

import pl.mftau.mftau.leaders.domain.model.Emaus
import pl.mftau.mftau.leaders.domain.model.Person

data class LeadersEmausScreenState(
    val isLoading: Boolean = true,
    val lastDraw: List<Emaus> = listOf(),
    val people: List<Person> = listOf(),
    val noPeopleErrorVisible: Boolean = false,
    val drawInProgress: Boolean = false,
    val drawErrorMessageVisible: Boolean = false,
)