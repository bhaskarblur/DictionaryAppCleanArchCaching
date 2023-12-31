package com.bhaskarblur.dictionaryapp.dictionary_feature.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhaskarblur.dictionaryapp.core.utils.Resources
import com.bhaskarblur.dictionaryapp.dictionary_feature.domain.usecase.GetWordInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val wordInfo : GetWordInfo
) : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery : State<String> = _searchQuery

    private val _state = mutableStateOf(WordInfoState())
    val state : State<WordInfoState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvents>()
    val eventFlow = _eventFlow

    private var searchJob : Job? = null
    sealed class UIEvents {
        data class ShowSnackbar(val message: String) : UIEvents()
    }

    fun onSearch(query : String) {
        _searchQuery.value = query;
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            wordInfo(query)
                .onEach {result ->
                    when(result) {
                        is Resources.Success -> {
                            _state.value = state.value.copy(
                                wordInfoItems = result.data ?: emptyList(),
                                loading = false
                            )
                        }
                        is Resources.Error -> {
                            _state.value = state.value.copy(
                                wordInfoItems = result.data ?: emptyList(),
                                loading = false
                            )
                            _eventFlow.emit(UIEvents.ShowSnackbar(result.message ?: "There was an error"))

                        }
                        is Resources.Loading -> {
                            _state.value = state.value.copy(
                                wordInfoItems = result.data ?: emptyList(),
                                loading = true
                            )
                        }
                    }

                }.launchIn(this)

        }
    }
}