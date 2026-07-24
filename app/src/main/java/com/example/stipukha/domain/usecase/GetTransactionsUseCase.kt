package com.example.stipukha.domain.usecase

import com.example.stipukha.data.local.entity.TransactionEntity
import com.example.stipukha.data.repository.BudgetRepository
import kotlinx.coroutines.flow.Flow

class GetTransactionsUseCase(
    private val repository: BudgetRepository
) {
    operator fun invoke(budgetId: Long = 1): Flow<List<TransactionEntity>> {
        return repository.getTransactionsByBudget(budgetId)
    }
}
