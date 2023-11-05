package pl.mftau.mftau.breviary.model

data class Psalmody(
    val breviaryPages: String = "",
    val psalms: List<Psalm> = listOf()
)
