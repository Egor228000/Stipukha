package com.example.stipukha.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.stipukha.data.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert
    suspend fun insertTransaction(transaction: TransactionEntity)

    @Query("SELECT * FROM transactions WHERE budgetId = :budgetId ORDER BY timestamp DESC")
    fun getTransactionsByBudget(budgetId: Long): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE budgetId = :budgetId AND transactionType = :type ORDER BY timestamp DESC")
    fun getTransactionsByType(budgetId: Long, type: String): Flow<List<TransactionEntity>>

    @Query("DELETE FROM transactions WHERE budgetId = :budgetId")
    suspend fun clearTransactionsByBudget(budgetId: Long)
}