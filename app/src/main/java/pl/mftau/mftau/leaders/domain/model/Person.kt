package pl.mftau.mftau.leaders.domain.model

data class Person(
    var id: String = "",
    var name: String = "",
    var city: String = "",
    var type: PersonType = PersonType.MEMBER
)