package pl.mftau.mftau.leaders.domain.model

data class PersonPresence(
    val personId: String = "",
    val personName: String = "",
    val presence: Map<MeetingType, Triple<Float, Float, Float>> = mapOf()
)