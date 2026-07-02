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
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stipukha.R
import com.example.stipukha.ui.feature_main.keys

@Composable
fun CustomKeyboardNumber(sum: MutableState<String>) {
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
                            if (sum.value.isNotEmpty()) {
                                sum.value = sum.value.dropLast(1)
                                if (sum.value.isEmpty()) sum.value = "0"
                            }
                        } else {
                            if (sum.value.length > 20) {


                            } else {
                                if (sum.value == "0" && key != ".") {
                                    sum.value = key
                                } else {
                                    sum.value += key
                                }
                            }
                        }
                    }
            ) {
                if (key == "backspace") {
                    Icon(
                        painter = painterResource(R.drawable.backspace),
                        contentDescription = "Удалить",
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