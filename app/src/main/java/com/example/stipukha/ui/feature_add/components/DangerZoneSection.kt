package com.example.stipukha.ui.feature_add.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stipukha.R


@Composable
fun DangerZoneSection() {
    Text(
        text = "Опасная зона",
        color = Color(0xFFE53935),
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        modifier = Modifier.padding(bottom = 8.dp)
    )
    Text(
        text = "Сброс удалит все текущие расходы и настройки периода. Это действие нельзя отменить.",
        color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.6f),
        fontSize = 14.sp,
        lineHeight = 20.sp,
        modifier = Modifier.padding(bottom = 16.dp)
    )

    OutlinedButton(
        onClick = {  },
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFE53935)),
        border = BorderStroke(1.dp, Color(0xFFE53935).copy(alpha = 0.5f))
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.trash_x),
                contentDescription = "Сброс"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Сбросить бюджет", fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }
    }
}