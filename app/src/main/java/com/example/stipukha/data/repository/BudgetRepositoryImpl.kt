package com.example.stipukha.data.repository

import com.example.stipukha.data.local.db.BudgetDao
import com.example.stipukha.data.local.db.TransactionDao
import com.example.stipukha.data.local.entity.BudgetEntity
import com.example.stipukha.data.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

class BudgetRepositoryImpl(
    private val transactionDao: TransactionDao,
    private val budgetDao: BudgetDao
) : BudgetRepository {

    override fun getTransactionsByBudget(budgetId: Long): Flow<List<TransactionEntity>> {
        return transactionDao.getTransactionsByBudget(budgetId)
    }

    override suspend fun addTransaction(transaction: TransactionEntity) {
        transactionDao.insertTransaction(transaction)
    }

    override fun getBudget(): Flow<BudgetEntity?> {
        return budgetDao.getBudget()
    }

    override suspend fun saveBudget(budget: BudgetEntity) {
        budgetDao.insertBudget(budget)
    }

    override suspend fun setOnboardingCompleted(completed: Boolean) {
        budgetDao.setOnboardingCompleted(completed)
    }
}
