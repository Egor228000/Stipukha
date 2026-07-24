package com.example.stipukha.ui.feature_main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import com.example.stipukha.ui.feature_main.keys

@Composable
fun CustomKeyboardNumber(
    currentAmount: String,
    onAmountChange: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(16.dp)
    ) {
        items(keys) { key ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .aspectRatio(2f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .clickable {
                        if (key == "backspace") {
                            if (currentAmount.isNotEmpty()) {
                                val newVal = currentAmount.dropLast(1)
                                onAmountChange(if (newVal.isEmpty()) "0" else newVal)
                            }
                        } else {
                            if (currentAmount.length < 20) {
                                val newVal = if (currentAmount == "0" && key != ".") {
                                    key
                                } else {
                                    currentAmount + key
                                }
                                onAmountChange(newVal)
                            }
                        }
                    }
            ) {
                if (key == "backspace") {
                    Icon(
                        painter = painterResource(R.drawable.backspace),
                        contentDescription = stringResource(R.string.delete),
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                } else {
                    Text(
                        text = key,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
            }
        }
    }
}
