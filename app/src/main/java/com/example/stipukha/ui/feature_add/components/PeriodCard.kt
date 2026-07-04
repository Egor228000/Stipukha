package com.example.stipukha.ui.feature_add.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stipukha.R

@Composable
fun PeriodCard() {
    Text(
        text = "Период до",
        color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.6f),
        fontSize = 16.sp,
        modifier = Modifier.padding(bottom = 8.dp)
    )

    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            MaterialTheme.colorScheme.onPrimary,
                            RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.calendar_month),
                        contentDescription = "Календарь",
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "25 сентября, 2024",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }

            OutlinedButton(
                onClick = {  },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.tertiary),
                border = BorderStroke(
                    1.dp,
                    MaterialTheme.colorScheme.tertiary.copy(alpha = 0.4f)
                ),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                Text("Изменить", fontSize = 14.sp)
            }
        }
    }
}