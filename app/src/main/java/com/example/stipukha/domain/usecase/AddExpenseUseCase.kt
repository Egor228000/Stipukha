package com.example.stipukha.domain.usecase

import com.example.stipukha.data.local.entity.TransactionEntity
import com.example.stipukha.data.repository.BudgetRepository

class AddExpenseUseCase(
    private val repository: BudgetRepository
) {
    suspend operator fun invoke(
        budgetId: Long,
        amountInCents: Long,
        category: String,
        isCorrection: Boolean = false // Если нажали "Пополнил" для корректировки balance
    ) {
        val type = if (isCorrection) "CORRECTION" else "EXPENSE"

        val transaction = TransactionEntity(
            budgetId = budgetId,
            amountInCents = amountInCents,
            category = category,
            timestamp = System.currentTimeMillis(),
            transactionType = type
        )

        repository.addTransaction(transaction)
    }
}