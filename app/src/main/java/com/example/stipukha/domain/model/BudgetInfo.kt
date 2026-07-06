package com.example.stipukha.domain.model

data class BudgetInfo(
    val budgetId: Long,
    val totalAmountInCents: Long,
    val dailyLimitInCents: Long,
    val remainingDays: Int,
    val currentBalanceInCents: Long
)