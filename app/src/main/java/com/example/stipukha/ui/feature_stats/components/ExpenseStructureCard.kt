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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stipukha.R

@Composable
fun ExpenseStructureCard() {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "Структура расходов",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color =  MaterialTheme.colorScheme.tertiary
            )
            Spacer(modifier = Modifier.height(16.dp))

            ExpenseProgressRow(
                icon = R.drawable.tools_kitchen_2,
                category = R.string.eat,
                amount = "12,400 ₽",
                percentage = 0.6f,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))
            ExpenseProgressRow(
                icon =  R.drawable.car,
                category = R.string.transport,
                amount = "4,100 ₽",
                percentage = 0.2f,
                color = Color(0xFF703EE5)
            )
            Spacer(modifier = Modifier.height(16.dp))
            ExpenseProgressRow(
                icon =  R.drawable.device_gamepad_2,
                category = R.string.fan,
                amount = "4,100 ₽",
                percentage = 0.2f,
                color = Color(0xFF2B59A6)
            )
            Spacer(modifier = Modifier.height(16.dp))
            ExpenseProgressRow(
                icon =  R.drawable.dental,
                category = R.string.healt,
                amount = "4,100 ₽",
                percentage = 0.2f,
                color = Color(0xFF812BA6)
            )
            Spacer(modifier = Modifier.height(16.dp))
            ExpenseProgressRow(
                icon =  R.drawable.sketching,
                category = R.string.other,
                amount = "4,100 ₽",
                percentage = 0.2f,
                color = Color(0xFF68A62B)
            )
        }
    }
}
