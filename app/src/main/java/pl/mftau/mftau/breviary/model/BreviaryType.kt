package pl.mftau.mftau.breviary.model

enum class BreviaryType(val type: Int) {
    INVITATORY(0),
    OFFICE_OF_READINGS(1),
    MORNING_PRAYER(2),
    MIDMORNING_PRAYER(3),
    MIDDAY_PRAYER(4),
    MIDAFTERNOON_PRAYER(5),
    EVENING_PRAYER(6),
    NIGHT_PRAYER(7);

    companion object {
        fun fromPosition(position: Int): BreviaryType = when (position) {
            0 -> INVITATORY
            1 -> OFFICE_OF_READINGS
            2 -> MORNING_PRAYER
            3 -> MIDMORNING_PRAYER
            4 -> MIDDAY_PRAYER
            5 -> MIDAFTERNOON_PRAYER
            6 -> EVENING_PRAYER
            else -> NIGHT_PRAYER
        }
    }
}