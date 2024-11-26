package pl.mftau.mftau.leaders.domain.model

data class Person(
    val id: String = "",
    val name: String = "",
    val city: String = "",
    val type: PersonType = PersonType.MEMBER,
    val notes: String = "",
)