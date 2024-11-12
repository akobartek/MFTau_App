package pl.mftau.mftau.breviary.domain.model

enum class BreviaryType(val type: Int) {
    INVITATORY(0),
    OFFICE_OF_READINGS(1),
    LAUDS(2),
    MIDMORNING_PRAYER(3),
    MIDDAY_PRAYER(4),
    MIDAFTERNOON_PRAYER(5),
    VESPERS(6),
    COMPLINE(7);

    companion object {
        fun fromPosition(position: Int): BreviaryType = when (position) {
            0 -> INVITATORY
            1 -> OFFICE_OF_READINGS
            2 -> LAUDS
            3 -> MIDMORNING_PRAYER
            4 -> MIDDAY_PRAYER
            5 -> MIDAFTERNOON_PRAYER
            6 -> VESPERS
            else -> COMPLINE
        }
    }
}