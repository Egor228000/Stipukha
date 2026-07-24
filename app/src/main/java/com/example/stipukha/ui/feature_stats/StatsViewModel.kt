package com.example.stipukha.ui.feature_stats

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stipukha.domain.usecase.GetTransactionsUseCase
import com.example.stipukha.ui.feature_stats.mvi.StatsIntent
import com.example.stipukha.ui.feature_stats.mvi.StatsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
class StatsViewModel(
    private val getTransactionsUseCase: GetTransactionsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(StatsState())
    val state: StateFlow<StatsState> = _state.asStateFlow()

    init {
        observeTransactions()
    }

    private fun observeTransactions() {
        viewModelScope.launch {
            getTransactionsUseCase().collect { transactions ->
                calculateStats(transactions)
            }
        }
    }

    private fun calculateStats(transactions: List<com.example.stipukha.data.local.entity.TransactionEntity>) {
        val expenses = transactions.filter { it.transactionType == "EXPENSE" }
        if (expenses.isEmpty()) {
            _state.update { it.copy(isLoading = false) }
            return
        }

        val totalExpenses = expenses.sumOf { it.amountInCents }
        val avgCheck = totalExpenses / expenses.size

        val expensesByCategory = expenses.groupBy { it.category.toIntOrNull() ?: 0 }
            .mapValues { entry -> entry.value.sumOf { it.amountInCents } }

        val dayOfWeekStats = expenses.groupBy {
            val date = Instant.ofEpochMilli(it.timestamp).atZone(ZoneId.systemDefault()).toLocalDate()
            date.dayOfWeek
        }.mapValues { it.value.sumOf { it.amountInCents } }

        val peakDay = dayOfWeekStats.maxByOrNull { it.value }?.key?.name?.lowercase()
            ?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } ?: "—"
        val minDay = dayOfWeekStats.minByOrNull { it.value }?.key?.name?.lowercase()
            ?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } ?: "—"

        _state.update {
            it.copy(
                averageCheck = "${avgCheck / 100} ₽",
                totalExpenses = totalExpenses,
                expensesByCategory = expensesByCategory,
                peakExpensesDay = peakDay,
                economicalDay = minDay,
                isLoading = false
            )
        }
    }

    fun handleIntent(intent: StatsIntent) {
        when (intent) {
            is StatsIntent.SelectPeriod -> {
                _state.update { it.copy(selectedPeriod = intent.period) }
            }
        }
    }
}
