package com.example.stipukha.ui.feature_stats.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stipukha.R

@Composable
fun QuickStatsCard() {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            QuickStatsRow(
                icon = R.drawable.receipt,
                title = "Средний чек",
                value = "420 ₽"
            )
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 12.dp),
                thickness = DividerDefaults.Thickness,
                color = DividerDefaults.color
            )
            QuickStatsRow(
                icon = R.drawable.chart_bar,
                title = "Экономный день",
                value = "Вторник"
            )
            Divider(modifier = Modifier.padding(vertical = 12.dp))
            QuickStatsRow(icon = R.drawable.calendar_month, title = "Пик расходов", value = "Суббота")
        }
    }
}
