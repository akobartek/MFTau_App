package pl.mftau.mftau.gospel.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mftau.mftau.common.data.PreferencesRepository
import pl.mftau.mftau.gospel.domain.model.Gospel
import pl.mftau.mftau.gospel.domain.GospelRepository

class GospelViewModel(
    private val repository: GospelRepository,
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    private val _state = MutableStateFlow<GospelScreenState>(GospelScreenState.Loading)
    val state = _state.asStateFlow()
    
    private val _repeatGospel = MutableStateFlow(false)
    val repeatGospel = _repeatGospel.asStateFlow()

    init {
        loadGospel()
        viewModelScope.launch {
            _repeatGospel.update { preferencesRepository.getRepeatGospel() }
        }
    }

    fun loadGospel() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.loadGospel()
            _state.update {
                if (result.isSuccess)
                    GospelScreenState.Success(result.getOrDefault(Gospel()))
                else GospelScreenState.Failure
            }
        }
    }

    fun getGospelToRead(): String =
        state.value.takeIf { it is GospelScreenState.Success }?.let { successState ->
            val gospel = (successState as GospelScreenState.Success).gospel
            val stringBuilder = StringBuilder()
            stringBuilder.append(gospel.author)
                .append(". ")
                .append(gospel.text.replace(";", "."))
            stringBuilder.toString()
        } ?: ""
}