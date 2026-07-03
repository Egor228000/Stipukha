package com.example.stipukha.ui.feature_stats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stipukha.ui.feature_stats.components.ExpenseStructureCard
import com.example.stipukha.ui.feature_stats.components.PeriodSelector
import com.example.stipukha.ui.feature_stats.components.QuickStatsCard
import com.example.stipukha.ui.feature_stats.components.SavingsCard

@Composable
fun StatsScreen() {
    var selectedPeriod by remember { mutableStateOf("Месяц") }


    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {


            PeriodSelector(
                selectedPeriod = selectedPeriod,
                onPeriodSelected = { selectedPeriod = it }
            )
        }

        item {
            SavingsCard(amount = "+4,200 ₽", trend = "+15% к прошлому")
        }

        item {
            QuickStatsCard()
        }
        item {
            ExpenseStructureCard()

        }
    }
}










