package com.eeema.android.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.eeema.android.theme.SavingsTheme

@Composable
fun TransactionsView(
    state: TransactionsUiState,
) {
    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(state.transactions) { transaction ->
            OutlinedCard {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.15f))
                        .padding(8.dp),
                ) {
                    Text(
                        transaction.description,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    )
                    Text(
                        transaction.date,
                        style = MaterialTheme.typography.bodySmall,
                    )
                    Text(
                        if (transaction.isIncome) "+${transaction.quantity}" else "-${transaction.quantity}",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(alignment = Alignment.End),
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    SavingsTheme {
        TransactionsView(
            state = TransactionsUiState(
                transactions = listOf(
                    TransactionUiModel(
                        id = 123L,
                        description = "Salario",
                        quantity = "2500$",
                        date = "7/10/2026",
                        isIncome = true,
                    ),
                    TransactionUiModel(
                        id = 123L,
                        description = "Compra Mercadona",
                        quantity = "123$",
                        date = "18/10/2026",
                        isIncome = false,
                    ),
                    TransactionUiModel(
                        id = 123L,
                        description = "Gasolina",
                        quantity = "53$",
                        date = "13/10/2026",
                        isIncome = false,
                    ),
                ),
            ),
        )
    }
}
