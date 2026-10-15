package com.eeema.android.home

sealed interface HomeUiEvent {
    data class OnTabChanged(val tab: Tab) : HomeUiEvent
}