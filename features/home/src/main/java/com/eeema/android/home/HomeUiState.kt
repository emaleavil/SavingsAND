package com.eeema.android.home

data class HomeUiState(
    val currentTab: Tab,
    val summary: SummaryUiState = SummaryUiState(0f)
)

data class SummaryUiState(
    val percentage: Float
)