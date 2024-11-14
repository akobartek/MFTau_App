package pl.mftau.mftau.readings.presentation

import pl.mftau.mftau.readings.domain.model.Reading

data class ReadingsScreenState(
    val selectedTab: Int = 0,
    val prayers: List<Reading> = listOf(),
    val writings: List<Reading> = listOf(),
    val selectedReading: Reading? = null,
)