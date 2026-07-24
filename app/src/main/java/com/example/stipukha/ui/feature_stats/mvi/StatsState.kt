package com.example.stipukha.ui.feature_stats.mvi

data class StatsState(
    val selectedPeriod: String = "Месяц",
    val savingsAmount: String = "0 ₽",
    val savingsTrend: String = "0% к прошлому",
    val averageCheck: String = "0 ₽",
    val economicalDay: String = "—",
    val peakExpensesDay: String = "—",
    val expensesByCategory: Map<Int, Long> = emptyMap(),
    val totalExpenses: Long = 0,
    val isLoading: Boolean = true
)


