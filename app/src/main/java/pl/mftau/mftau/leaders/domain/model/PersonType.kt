package pl.mftau.mftau.leaders.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BrightnessAuto
import androidx.compose.material.icons.outlined.ChildCare
import androidx.compose.material.icons.outlined.MilitaryTech
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.WorkspacePremium

enum class PersonType(val index: Int) {
    SYMPATHIZER(0),
    MEMBER(1),
    RESPONSIBLE(2),
    LEADER(3),
    ASSISTANT(4),
    OTHER(5);

    fun getTypeIcon() = when (this) {
        SYMPATHIZER -> Icons.Outlined.ChildCare
        MEMBER -> Icons.Outlined.Person
        RESPONSIBLE -> Icons.Outlined.MilitaryTech
        LEADER -> Icons.Outlined.WorkspacePremium
        ASSISTANT -> Icons.Outlined.BrightnessAuto
        OTHER -> null
    }

    companion object {
        fun fromIndex(index: Int) = when (index) {
            0 -> SYMPATHIZER
            1 -> MEMBER
            2 -> RESPONSIBLE
            3 -> LEADER
            4 -> ASSISTANT
            else -> OTHER
        }
    }
}