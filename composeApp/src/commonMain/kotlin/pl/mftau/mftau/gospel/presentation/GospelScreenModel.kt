package pl.mftau.mftau.gospel.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.common.data.PreferencesRepository
import pl.mftau.mftau.gospel.domain.model.Gospel
import pl.mftau.mftau.gospel.domain.GospelRepository
import pl.mftau.mftau.gospel.data.GospelRepositoryImpl

class GospelScreenModel(
    private val repository: GospelRepository,
    private val preferencesRepository: PreferencesRepository
) : StateScreenModel<GospelScreenModel.State>(State.Loading) {

    sealed class State {
        data object Loading : State()
        data class Success(val gospel: Gospel) : State()
        data object Failure : State()
    }

    private val _repeatGospel = MutableStateFlow(false)
    val repeatGospel = _repeatGospel.asStateFlow()

    init {
        loadGospel()
        screenModelScope.launch {
            _repeatGospel.update { preferencesRepository.getRepeatGospel() }
        }
    }

    fun loadGospel() {
        screenModelScope.launch(Dispatchers.IO) {
            val result = repository.loadGospel()
            mutableState.update {
                if (result.isSuccess)
                    State.Success(result.getOrDefault(Gospel()))
                else State.Failure
            }
        }
    }

    fun getGospelToRead(): String {
        return if (state.value is State.Success) {
            val gospel = (state.value as State.Success).gospel
            val stringBuilder = StringBuilder()
            stringBuilder.append(gospel.author)
                .append(". ")
                .append(gospel.text.replace(";", "."))
            stringBuilder.toString()
        } else ""
    }
}