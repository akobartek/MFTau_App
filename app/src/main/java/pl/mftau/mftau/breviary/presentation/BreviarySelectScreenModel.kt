package pl.mftau.mftau.breviary.presentation

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BreviarySelectScreenModel : ScreenModel {

    private val _daysFromToday = MutableStateFlow(0)
    val daysFromToday = _daysFromToday.asStateFlow()

    fun updateDaysFromToday(newValue: Int) {
        _daysFromToday.update { newValue }
    }
}