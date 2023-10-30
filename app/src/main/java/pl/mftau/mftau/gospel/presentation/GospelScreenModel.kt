package pl.mftau.mftau.gospel.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.gospel.data.Gospel
import pl.mftau.mftau.gospel.data.GospelRepository
import pl.mftau.mftau.gospel.data.GospelRepositoryImpl

class GospelScreenModel(private val gospelRepository: GospelRepository = GospelRepositoryImpl()) :
    StateScreenModel<GospelScreenModel.State>(State.Loading) {

    sealed class State {
        data object Loading : State()
        data class Success(val gospel: Gospel) : State()
        data class Failure(val throwable: Throwable) : State()
    }

    init {
        loadGospel()
    }

    fun loadGospel() {
        screenModelScope.launch {
            val result = gospelRepository.loadGospel().first()
            mutableState.update {
                if (result.isSuccess)
                    State.Success(result.getOrDefault(Gospel()))
                else
                    State.Failure(result.exceptionOrNull() ?: Throwable())
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