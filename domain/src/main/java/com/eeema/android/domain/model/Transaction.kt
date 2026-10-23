package com.eeema.android.domain.model

data class Transaction(
    val id: String,
    val amount: Float,
    val createdAt: String,
    val description: String,
    val currency: String,
    val type: TransactionType = TransactionType.EXPENSE,
)
