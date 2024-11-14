package pl.mftau.mftau.leaders.presentation.people

import pl.mftau.mftau.leaders.domain.model.Person

data class LeadersPeopleScreenState(
    val people: List<Person> = listOf(),
    val isLoading: Boolean = true,
    val personEditorVisible: Boolean = false,
    val personToEdit: Person? = null,
)