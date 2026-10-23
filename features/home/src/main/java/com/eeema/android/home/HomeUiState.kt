package com.eeema.android.home

data class HomeUiState(
    val isLoading: Boolean,
    val currentTab: Tab,
    val summary: SummaryUiState = SummaryUiState(percentage = 0f, spent = 0f, remaining = 0f),
    val transactions: TransactionsUiState = TransactionsUiState(listOf()),
)

data class SummaryUiState(
    val percentage: Float,
    val spent: Float,
    val remaining: Float,
)

data class TransactionsUiState(
    val transactions: List<TransactionUiModel>,
)

data class TransactionUiModel(
    val id: String, // TODO check to use UUID
    val description: String,
    val quantity: String,
    val date: String,
    val isIncome: Boolean,
)
