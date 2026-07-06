package com.example.stipukha.data.repository

import com.example.stipukha.data.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

interface BudgetRepository {
    fun getTransactionsByBudget(budgetId: Long): Flow<List<TransactionEntity>>
    suspend fun addTransaction(transaction: TransactionEntity)
}