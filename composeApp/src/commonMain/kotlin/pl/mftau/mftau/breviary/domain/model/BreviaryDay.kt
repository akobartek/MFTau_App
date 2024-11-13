package pl.mftau.mftau.breviary.domain.model

import pl.mftau.mftau.breviary.domain.model.Breviary.BreviaryHtml

data class BreviaryDay(
    val id: Long = 0L,
    val date: String,
    val invitatory: BreviaryHtml? = BreviaryHtml(),
    val officeOfReadings: BreviaryHtml? = BreviaryHtml(),
    val lauds: BreviaryHtml? = BreviaryHtml(),
    val prayer1: BreviaryHtml? = BreviaryHtml(),
    val prayer2: BreviaryHtml? = BreviaryHtml(),
    val prayer3: BreviaryHtml? = BreviaryHtml(),
    val vespers: BreviaryHtml? = BreviaryHtml(),
    val compline: BreviaryHtml? = BreviaryHtml(),
) {
    fun getValueByIndex(index: Int): String? =
        when (index) {
            0 -> invitatory
            1 -> officeOfReadings
            2 -> lauds
            3 -> prayer1
            4 -> prayer2
            5 -> prayer3
            6 -> vespers
            7 -> compline
            else -> null
        }?.html
}
