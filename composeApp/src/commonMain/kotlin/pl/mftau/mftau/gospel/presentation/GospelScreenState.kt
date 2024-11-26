package pl.mftau.mftau.gospel.presentation

import pl.mftau.mftau.gospel.domain.model.Gospel

sealed class GospelScreenState {
    data object Loading : GospelScreenState()
    data class Success(val gospel: Gospel) : GospelScreenState()
    data object Failure : GospelScreenState()
}