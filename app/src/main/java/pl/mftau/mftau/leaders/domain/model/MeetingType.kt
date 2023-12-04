package pl.mftau.mftau.leaders.domain.model

import pl.mftau.mftau.R
import pl.mftau.mftau.ui.theme.MeetingType1Color1
import pl.mftau.mftau.ui.theme.MeetingType1Color2
import pl.mftau.mftau.ui.theme.MeetingType1Color3
import pl.mftau.mftau.ui.theme.MeetingType2Color1
import pl.mftau.mftau.ui.theme.MeetingType2Color2
import pl.mftau.mftau.ui.theme.MeetingType2Color3
import pl.mftau.mftau.ui.theme.MeetingType3Color1
import pl.mftau.mftau.ui.theme.MeetingType3Color2
import pl.mftau.mftau.ui.theme.MeetingType3Color3

enum class MeetingType(val index: Int) {
    FORMATION(0),
    PRAYERFUL(1),
    OTHER(2);

    fun getProperColors() = when(this) {
        FORMATION -> Triple(MeetingType1Color1, MeetingType1Color2, MeetingType1Color3)
        PRAYERFUL -> Triple(MeetingType2Color1, MeetingType2Color2, MeetingType2Color3)
        OTHER -> Triple(MeetingType3Color1, MeetingType3Color2, MeetingType3Color3)
    }

    fun getNameResourceId() = when(this) {
        FORMATION -> R.string.meeting_formation
        PRAYERFUL -> R.string.meeting_prayerful
        OTHER -> R.string.meeting_other
    }

    companion object {
        fun fromIndex(index: Int) = when (index) {
            0 -> FORMATION
            1 -> PRAYERFUL
            else -> OTHER
        }
    }
}