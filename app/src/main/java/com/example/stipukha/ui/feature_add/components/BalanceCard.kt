package com.example.stipukha.ui.feature_add.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stipukha.R

@Composable
fun BalanceCard(balance: Long, initialAmount: Long, onEditClick: () -> Unit) {
    Text(
        text = stringResource(R.string.current_balance),
        color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.6f),
        fontSize = 16.sp,
        modifier = Modifier.padding(bottom = 8.dp)
    )

    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.fillMaxWidth().clickable { onEditClick() }
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = stringResource(R.string.total_available),
                color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.6f),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${balance / 100} ₽",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .background(MaterialTheme.colorScheme.tertiary, CircleShape)
                        .clickable { onEditClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.pencil),
                        contentDescription = "Edit",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            val progress = if (initialAmount > 0) balance.toFloat() / initialAmount else 0f
            LinearProgressIndicator(
                progress = { progress.coerceIn(0f, 1f) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(CircleShape),
                color = MaterialTheme.colorScheme.tertiary,
                trackColor = MaterialTheme.colorScheme.primary,
            )

            Text(
                text = stringResource(R.string.balance_auto_update),
                color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.6f),
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 12.dp),
                lineHeight = 16.sp
            )
        }
    }
}
