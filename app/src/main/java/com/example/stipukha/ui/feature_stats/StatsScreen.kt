package com.example.stipukha.ui.feature_stats

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stipukha.ui.feature_stats.components.ExpenseStructureCard
import com.example.stipukha.ui.feature_stats.components.PeriodSelector
import com.example.stipukha.ui.feature_stats.components.QuickStatsCard
import com.example.stipukha.ui.feature_stats.components.SavingsCard
import com.example.stipukha.ui.feature_stats.mvi.StatsIntent

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun StatsScreen(viewModel: StatsViewModel) {
    val state by viewModel.state.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            PeriodSelector(
                selectedPeriod = state.selectedPeriod,
                onPeriodSelected = { viewModel.handleIntent(StatsIntent.SelectPeriod(it)) }
            )
        }

        item {
            SavingsCard(amount = state.savingsAmount, trend = state.savingsTrend)
        }

        item {
            QuickStatsCard(
                averageCheck = state.averageCheck,
                economicalDay = state.economicalDay,
                peakExpensesDay = state.peakExpensesDay
            )
        }
        item {
            ExpenseStructureCard(
                expensesByCategory = state.expensesByCategory,
                totalExpenses = state.totalExpenses
            )
        }
    }
}
