package com.eeema.android.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class HomeViewModel : ViewModel() {

    private var _state: MutableStateFlow<HomeUiState> = MutableStateFlow(
        HomeUiState(currentTab = Tab.SUMMARY),
    )
    val state: StateFlow<HomeUiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            delay(2.seconds)
            _state.update { it.copy(summary = SummaryUiState(100f)) }
        }
    }

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.OnTabChanged -> _state.update { it.copy(currentTab = event.tab) }
        }
    }
}
