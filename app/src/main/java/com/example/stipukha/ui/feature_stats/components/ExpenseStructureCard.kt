package com.example.stipukha.ui.feature_stats.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stipukha.R

val categoryResources = mapOf(
    R.string.eat to Pair(R.drawable.tools_kitchen_2, Color.Black),
    R.string.transport to Pair(R.drawable.car, Color(0xFF703EE5)),
    R.string.fan to Pair(R.drawable.device_gamepad_2, Color(0xFF2B59A6)),
    R.string.healt to Pair(R.drawable.dental, Color(0xFF812BA6)),
    R.string.other to Pair(R.drawable.sketching, Color(0xFF68A62B))
)

@Composable
fun ExpenseStructureCard(
    expensesByCategory: Map<Int, Long>,
    totalExpenses: Long
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = stringResource(R.string.expense_structure),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color =  MaterialTheme.colorScheme.tertiary
            )
            Spacer(modifier = Modifier.height(16.dp))

            categoryResources.forEach { (categoryResId, resources) ->
                val amountCents = expensesByCategory[categoryResId] ?: 0L
                val percentage = if (totalExpenses > 0) amountCents.toFloat() / totalExpenses else 0f
                
                ExpenseProgressRow(
                    icon = resources.first,
                    category = categoryResId,
                    amount = "${amountCents / 100} ₽",
                    percentage = percentage,
                    color = resources.second
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
