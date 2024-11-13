package pl.mftau.mftau.breviary.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import pl.mftau.mftau.breviary.domain.usecases.ClearBreviaryDbUseCase

class BreviarySelectViewModel(
    private val clearBreviaryDbUseCase: ClearBreviaryDbUseCase,
) : ViewModel() {

    private val _daysFromToday = MutableStateFlow(0)
    val daysFromToday = _daysFromToday.asStateFlow()

    var dateString = ""
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getCorrectDaysString(0)
            clearBreviaryDbUseCase(dateString)
        }
    }

    fun updateDaysFromToday(newValue: Int) {
        _daysFromToday.update { newValue }
    }

    fun getCorrectDaysString(daysFromToday: Int): String {
        val date = Clock.System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .date
            .plus(daysFromToday, DateTimeUnit.DAY)
        val dayInt = date.dayOfMonth
        val day = if (dayInt < 10) "0$dayInt" else dayInt.toString()
        val monthInt = date.monthNumber
        val month = if (monthInt < 10) "0$monthInt" else monthInt.toString()
        val year = date.year.toString().substring(2)
        dateString = "$day.$month.$year"
        return " ($day.$month)"
    }
}