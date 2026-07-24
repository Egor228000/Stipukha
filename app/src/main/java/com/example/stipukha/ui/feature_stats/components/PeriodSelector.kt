package com.example.stipukha.ui.feature_stats.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stipukha.R

@Composable
fun PeriodSelector(selectedPeriod: String, onPeriodSelected: (String) -> Unit) {
    val weekStr = stringResource(R.string.week)
    val monthStr = stringResource(R.string.month)

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .padding(4.dp)
    ) {
        val periods = listOf(weekStr, monthStr)
        periods.forEach { period ->
            val isSelected = period == selectedPeriod
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (isSelected) MaterialTheme.colorScheme.onPrimary else Color.Transparent)
                    .clickable { onPeriodSelected(period) }
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = period,
                    fontSize = 16.sp,
                    fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    }
}
