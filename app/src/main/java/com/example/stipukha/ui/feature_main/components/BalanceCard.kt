package com.example.stipukha.ui.feature_main.components

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BalanceCard(
    balance: Int,
    balanceAll: Int,
    days: Int
) {
    Card(
        colors = CardDefaults.cardColors(
            MaterialTheme.colorScheme.onPrimary
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Доступно на сегодня",
                color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.6f),
                fontWeight = FontWeight.W500,
                fontSize = 18.sp

            )
            Spacer(modifier = Modifier.height(8.dp))
            // Счет
            Text(
                balance.toString(),
                color = MaterialTheme.colorScheme.tertiary,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 60.sp
            )
            Spacer(modifier = Modifier.height(16.dp))

            Card(
                colors = CardDefaults.cardColors(
                    MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Всего ${balanceAll} Р * Осталось: ${days} дня",
                        color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.6f),
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}