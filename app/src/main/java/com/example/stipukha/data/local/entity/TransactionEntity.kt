package com.example.stipukha.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val budgetId: Long,
    val amountInCents: Long,
    val category: String,
    val timestamp: Long,
    val transactionType: String // EXPENSE, INCOME, CORRECTION
)
