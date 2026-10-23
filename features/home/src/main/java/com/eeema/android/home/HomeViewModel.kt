package com.eeema.android.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eeema.android.domain.model.TransactionType
import com.eeema.android.domain.model.Transactions
import com.eeema.android.domain.usecases.GetTransactionsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class HomeViewModel(
    private val getTransactionsUseCase: GetTransactionsUseCase,
) : ViewModel() {

    private var _state: MutableStateFlow<HomeUiState> = MutableStateFlow(
        HomeUiState(currentTab = Tab.SUMMARY, isLoading = false),
    )
    val state: StateFlow<HomeUiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            delay(2.seconds)
            val transactions: Result<Transactions> = getTransactionsUseCase()
            when {
                transactions.isSuccess -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            summary = mapToSummary(transactions),
                            transactions = mapToTransactions(transactions),
                        )
                    }
                }

                transactions.isFailure -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            summary = SummaryUiState(percentage = 0f, spent = 0f, remaining = 0f),
                            transactions = TransactionsUiState(emptyList()),
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.OnTabChanged -> _state.update { it.copy(currentTab = event.tab) }
        }
    }

    private fun mapToSummary(transactions: Result<Transactions>): SummaryUiState {
        val transactions = transactions.getOrNull()
        return SummaryUiState(
            percentage = transactions?.spentPercentage ?: 0f,
            spent = transactions?.spentAmount ?: 0f,
            remaining = transactions?.remainingAmount ?: 0f,
        )
    }

    private fun mapToTransactions(transactions: Result<Transactions>): TransactionsUiState =
        TransactionsUiState(
            transactions.getOrNull()?.entries?.map { transaction ->
                TransactionUiModel(
                    id = transaction.id,
                    description = transaction.description,
                    quantity = "${transaction.amount} ${transaction.currency}",
                    date = transaction.createdAt,
                    isIncome = transaction.type == TransactionType.INCOME,
                )
            } ?: emptyList(),
        )
}
