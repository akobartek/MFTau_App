package pl.mftau.mftau.breviary.domain.model

data class Psalmody(
    val breviaryPages: String = "",
    val psalms: List<Psalm> = listOf()
)
