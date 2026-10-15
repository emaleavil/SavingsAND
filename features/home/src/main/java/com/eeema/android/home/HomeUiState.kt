package com.eeema.android.home

data class HomeUiState(
    val currentTab: Tab,
    val summary: SummaryUiState = SummaryUiState(0f),
    val transactions: TransactionsUiState = TransactionsUiState(listOf())
)

data class SummaryUiState(
    val percentage: Float
)

data class TransactionsUiState(
    val transactions: List<TransactionUiModel>
)

data class TransactionUiModel(
    val id: Long, // TODO check to use UUID
    val description: String,
    val quantity: String,
    val date: String,
    val isIncome: Boolean
)