package pl.mftau.mftau.leaders.domain.model

enum class MeetingType(val index: Int) {
    FORMATION(0),
    PRAYERFUL(1),
    OTHER(2);

    companion object {
        fun fromIndex(index: Int) = when (index) {
            0 -> FORMATION
            1 -> PRAYERFUL
            else -> OTHER
        }
    }
}