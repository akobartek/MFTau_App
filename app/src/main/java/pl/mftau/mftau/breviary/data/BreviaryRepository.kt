package pl.mftau.mftau.breviary.data

import kotlinx.coroutines.flow.Flow
import pl.mftau.mftau.breviary.model.Breviary
import pl.mftau.mftau.breviary.model.BreviaryType

abstract class BreviaryRepository {

    protected val mBreviaryUrlTypes = arrayOf(
        "wezw", "godzczyt", "jutrznia", "modlitwa1",
        "modlitwa2", "modlitwa3", "nieszpory", "kompleta"
    )

    abstract fun checkIfThereAreMultipleOffices(daysFromToday: Int): Flow<Result<Map<String, String>?>>

    abstract fun loadBreviary(office: String, daysFromToday: Int, type: BreviaryType): Flow<Result<Breviary>>
}