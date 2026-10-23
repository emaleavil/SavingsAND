package com.eeema.android.domain.model

import kotlin.math.max

data class Transactions(val entries: List<Transaction>) {

    private val transactionsByType: Map<TransactionType, List<Transaction>>
        get() = entries.groupBy { transaction -> transaction.type }

    val incomes: List<Transaction>
        get() = transactionsByType[TransactionType.INCOME].orEmpty()

    val expenses: List<Transaction>
        get() = transactionsByType[TransactionType.EXPENSE].orEmpty()

    val spentPercentage: Float
        get() = max(0f, ((expenses.getAmount() * 100) / incomes.getAmount()))

    val remainingAmount: Float
        get() = max(0f, incomes.getAmount() - expenses.getAmount())

    val spentAmount: Float
        get() = max(0f, expenses.getAmount())

    private fun List<Transaction>?.getAmount(): Float =
        this?.map { it.amount }?.reduce { acc, amount -> acc + amount } ?: 0f
}
