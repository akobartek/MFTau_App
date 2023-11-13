package pl.mftau.mftau.breviary.presentation

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.breviary.domain.usecase.ClearBreviaryDbUseCase
import java.util.Calendar

class BreviarySelectScreenModel(
    private val clearDbUseCase: ClearBreviaryDbUseCase
) : ScreenModel {

    private val _daysFromToday = MutableStateFlow(0)
    val daysFromToday = _daysFromToday.asStateFlow()

    var dateString = ""
        private set

    init {
        clearDatabase()
    }

    private fun clearDatabase() {
        screenModelScope.launch {
            getCorrectDaysString(0)
            clearDbUseCase(dateString)
        }
    }

    fun updateDaysFromToday(newValue: Int) {
        _daysFromToday.update { newValue }
    }

    fun getCorrectDaysString(daysFromToday: Int): String {
        val calendar = Calendar.getInstance()
        val dayInt = calendar.get(Calendar.DAY_OF_MONTH) + daysFromToday
        val day = if (dayInt < 10) "0$dayInt" else dayInt.toString()
        val monthInt = calendar.get(Calendar.MONTH) + 1
        val month = if (monthInt < 10) "0$monthInt" else monthInt.toString()
        val year = calendar.get(Calendar.YEAR).toString().substring(2)
        dateString = "$day.$month.$year"
        return " ($day.$month)"
    }
}