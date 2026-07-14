package com.example.stipukha.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "budget")
data class BudgetEntity(
    @PrimaryKey val id: Long = 1, // We only have one budget at a time for now
    val initialAmountInCents: Long,
    val endDateTimestamp: Long,
    val isOnboardingCompleted: Boolean = false
)
