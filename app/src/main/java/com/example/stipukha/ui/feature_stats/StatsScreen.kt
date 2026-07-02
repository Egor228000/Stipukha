package com.example.stipukha.ui.feature_stats

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
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
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
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Статистика",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
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
            // .background(ToggleBackground)
            .padding(4.dp)
    ) {
        val periods = listOf("Неделя", "Месяц")
        periods.forEach { period ->
            val isSelected = period == selectedPeriod
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                 //   .background(if (isSelected) CardBackground else Color.Transparent)
                 //   .clickableBlurless { onPeriodSelected(period) }
                    .padding(horizontal = 16.dp, vertical = 6.dp),
              //  contentAlignment = Alignment.Center
            ) {
                Text(
                    text = period,
                    fontSize = 14.sp,
                    fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal,
               //     color = PrimaryText
                )
            }
        }
    }
}

@Composable
fun SavingsCard(amount: String, trend: String) {
    Card(
        shape = RoundedCornerShape(24.dp),
     //   colors = CardDefaults.cardColors(containerColor = CardBackground),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(text = "Сэкономлено", fontSize = 12.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = amount, fontSize = 36.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .clip(CircleShape)
                   // .background(LightGreen)
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "↗ $trend",
                    fontSize = 12.sp,
                    //color = TextGreen,
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
      //  colors = CardDefaults.cardColors(containerColor = CardBackground),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            QuickStatsRow(
                icon = R.drawable.calendar_month,
                title = "Средний чек",
                value = "420 ₽"
            )
            Divider(modifier = Modifier.padding(vertical = 12.dp))
            QuickStatsRow(
                icon = R.drawable.calendar_month,
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
                .size(36.dp)
                .clip(CircleShape),
            //    .background(BackgroundGray),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(18.dp),
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = title, fontSize = 14.sp)
        Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun ExpenseStructureCard() {
    Card(
        shape = RoundedCornerShape(24.dp),
       // colors = CardDefaults.cardColors(containerColor = CardBackground),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "Структура расходов",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
               // color = PrimaryText
            )
            Spacer(modifier = Modifier.height(16.dp))

            ExpenseProgressRow(
                icon = R.drawable.arrow_narrow_left,
                category = "Еда",
                amount = "12,400 ₽",
                percentage = 0.6f,
                percentText = "60%",
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))
            ExpenseProgressRow(
                icon =  R.drawable.arrow_narrow_left,
                category = "Развлечения",
                amount = "4,100 ₽",
                percentage = 0.2f,
                percentText = "20%",
                color = Color(0xFF4A5B70)
            )
            Spacer(modifier = Modifier.height(16.dp))
            ExpenseProgressRow(
                icon =  R.drawable.arrow_narrow_left,
                category = "Другое",
                amount = "4,100 ₽",
                percentage = 0.2f,
                percentText = "20%",
                color = Color(0xFF7A8A9E)
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
               // tint = PrimaryText
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = category, fontSize = 14.sp)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = amount, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = percentText, fontSize = 11.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))
        LinearProgressIndicator(
            progress = { percentage },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(CircleShape),
            color = color,
        //    trackColor = BackgroundGray,
        )
    }
}

