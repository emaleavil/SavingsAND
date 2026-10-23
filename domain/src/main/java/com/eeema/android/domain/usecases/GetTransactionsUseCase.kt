package com.eeema.android.domain.usecases

import com.eeema.android.domain.model.Transaction
import com.eeema.android.domain.model.TransactionType
import com.eeema.android.domain.model.Transactions

class GetTransactionsUseCase {

    suspend operator fun invoke(): Result<Transactions> = Result.success(
        Transactions(
            entries = listOf(
                Transaction(
                    id = "txn-001",
                    amount = 2500f,
                    createdAt = "2026-10-01T00:00:00Z",
                    description = "Salario",
                    currency = "EUR",
                    type = TransactionType.INCOME,
                ),
                Transaction(
                    id = "txn-002",
                    amount = 48.75f,
                    createdAt = "2026-10-03T00:00:00Z",
                    description = "Compra en Mercadona",
                    currency = "EUR",
                ),
                Transaction(
                    id = "txn-003",
                    amount = 52f,
                    createdAt = "2026-10-05T00:00:00Z",
                    description = "Gasolina",
                    currency = "EUR",
                ),
                Transaction(
                    id = "txn-004",
                    amount = 12.99f,
                    createdAt = "2026-10-08T00:00:00Z",
                    description = "Suscripción de música",
                    currency = "EUR",
                ),
                Transaction(
                    id = "txn-005",
                    amount = 850f,
                    createdAt = "2026-10-10T00:00:00Z",
                    description = "Alquiler",
                    currency = "EUR",
                ),
                Transaction(
                    id = "txn-006",
                    amount = 35.4f,
                    createdAt = "2026-10-12T00:00:00Z",
                    description = "Cena",
                    currency = "EUR",
                ),
                Transaction(
                    id = "txn-007",
                    amount = 15.99f,
                    createdAt = "2026-10-18T00:00:00Z",
                    description = "Suscripción Netflix",
                    currency = "EUR",
                ),
                Transaction(
                    id = "txn-008",
                    amount = 254.59f,
                    createdAt = "2026-10-20T00:00:00Z",
                    description = "Arreglo coche",
                    currency = "EUR",
                ),
                Transaction(
                    id = "txn-009",
                    amount = 151.4f,
                    createdAt = "2026-10-22T00:00:00Z",
                    description = "Compra de Ropa",
                    currency = "EUR",
                ),
            ),
        ),
    )
}
