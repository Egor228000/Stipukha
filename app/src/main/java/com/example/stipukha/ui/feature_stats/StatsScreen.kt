package com.example.stipukha.ui.feature_stats

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stipukha.R

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

@Composable
fun PeriodSelector(selectedPeriod: String, onPeriodSelected: (String) -> Unit) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .padding(4.dp)
    ) {
        val periods = listOf("Неделя", "Месяц")
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

@Composable
fun SavingsCard(amount: String, trend: String) {
    Card(
        shape = RoundedCornerShape(24.dp),
       colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(text = "Сэкономлено", fontSize = 16.sp, color = MaterialTheme.colorScheme.tertiary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = amount, fontSize = 36.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.tertiary)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "↗ $trend",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

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

@Composable
fun QuickStatsRow(icon: Int, title: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                    .align(Alignment.CenterStart),
            )
            Text(text = title, fontSize = 14.sp, color = MaterialTheme.colorScheme.tertiary,    modifier = Modifier
                .padding(horizontal = 32.dp)
                .align(Alignment.CenterStart))
            Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.tertiary
                ,    modifier = Modifier
                    .align(Alignment.CenterEnd))
        }
        Spacer(modifier = Modifier.width(8.dp))

    }
}

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
                category = "Еда",
                amount = "12,400 ₽",
                percentage = 0.6f,
                percentText = "60%",
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))
            ExpenseProgressRow(
                icon =  R.drawable.car,
                category = "Транспорт",
                amount = "4,100 ₽",
                percentage = 0.2f,
                percentText = "20%",
                color = Color(0xFF703EE5)
            )
            Spacer(modifier = Modifier.height(16.dp))
            ExpenseProgressRow(
                icon =  R.drawable.device_gamepad_2,
                category = "Развлечение",
                amount = "4,100 ₽",
                percentage = 0.2f,
                percentText = "20%",
                color = Color(0xFF2B59A6)
            )
            Spacer(modifier = Modifier.height(16.dp))
            ExpenseProgressRow(
                icon =  R.drawable.dental,
                category = "Здоровье",
                amount = "4,100 ₽",
                percentage = 0.2f,
                percentText = "20%",
                color = Color(0xFF812BA6)
            )
            Spacer(modifier = Modifier.height(16.dp))
            ExpenseProgressRow(
                icon =  R.drawable.sketching,
                category = "Другое",
                amount = "4,100 ₽",
                percentage = 0.2f,
                percentText = "20%",
                color = Color(0xFF68A62B)
            )
        }
    }
}

@Composable
fun ExpenseProgressRow(
    icon: Int,
    category: String,
    amount: String,
    percentage: Float,
    percentText: String,
    color: Color
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint =  MaterialTheme.colorScheme.tertiary
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = category, fontSize = 14.sp, color = MaterialTheme.colorScheme.tertiary)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = amount, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.tertiary)
            Spacer(modifier = Modifier.width(6.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
        LinearProgressIndicator(
            progress = { percentage },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(CircleShape),
            color = color,
         trackColor = MaterialTheme.colorScheme.primary,
        )
    }
}

