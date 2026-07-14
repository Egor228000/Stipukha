package com.example.stipukha.domain.usecase

import com.example.stipukha.data.local.entity.TransactionEntity
import com.example.stipukha.data.repository.BudgetRepository

class AddTransactionUseCase(
    private val repository: BudgetRepository
) {
    suspend operator fun invoke(
        budgetId: Long,
        amountInCents: Long,
        category: String,
        transactionType: String // "EXPENSE", "INCOME", "CORRECTION"
    ) {
        val transaction = TransactionEntity(
            budgetId = budgetId,
            amountInCents = amountInCents,
            category = category,
            timestamp = System.currentTimeMillis(),
            transactionType = transactionType
        )

        repository.addTransaction(transaction)
    }
}
