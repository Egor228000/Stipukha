package com.example.stipukha.ui.feature_main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stipukha.R

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun MainScreen() {



    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

            val categoryMap = remember {
                mapOf(
                    "Еда" to R.drawable.tools_kitchen_2,
                    "Транспорт" to R.drawable.car,
                    "Развлечение" to R.drawable.device_gamepad_2,
                    "Здоровье" to R.drawable.dental,
                    "Другое" to R.drawable.sketching
                )
            }
            val keys = remember {
                listOf(
                    "1", "2", "3",
                    "4", "5", "6",
                    "7", "8", "9",
                    ".", "0", "backspace"
                )
            }
            var selectedCategory by remember { mutableStateOf("Еда") }

            BalanceCard(
                350,
                12000,
                24
            )

            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState())
            ) {
                categoryMap.forEach { (categoryName, iconRes) ->
                    CategoryCard(
                        textButton = categoryName,
                        iconButton = iconRes,
                        isSelected = categoryName == selectedCategory,
                        onClick = { selectedCategory = categoryName }
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            var sum by remember { mutableStateOf("0") }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.defaultMinSize(minHeight = 60.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = sum.take(21),
                        color = MaterialTheme.colorScheme.tertiary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    )
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
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
                                    if (sum.isNotEmpty()) {
                                        sum = sum.dropLast(1)
                                        if (sum.isEmpty()) sum = "0"
                                    }
                                } else {
                                    if (sum.length > 20) {


                                    } else {
                                        if (sum == "0" && key != ".") {
                                            sum = key
                                        } else {
                                            sum += key
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                ButtonIntent(
                    R.string.replenish,
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth(0.48f)
                        .align(Alignment.BottomStart),
                    colorsButton = MaterialTheme.colorScheme.onPrimary,
                    MaterialTheme.colorScheme.tertiary
                )
                ButtonIntent(
                    R.string.spent,
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth(0.48f)
                        .align(Alignment.BottomEnd),
                    colorsButton = MaterialTheme.colorScheme.tertiary,
                    MaterialTheme.colorScheme.onPrimary

                )
            }


        }
    }



@Composable
fun ButtonIntent(
    textButton: Int,
    modifier: Modifier = Modifier,
    colorsButton: Color,
    colorText: Color
) {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(colorsButton),
        modifier = modifier
    ) {
        Text(
            stringResource(textButton),
            color = colorText,
            fontSize = 20.sp,
        )
    }
}

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

@Composable
fun CategoryCard(
    textButton: String,
    iconButton: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val containerColor = if (isSelected) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onPrimary
    val contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.tertiary

    Button(
        onClick = onClick,
        colors = buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconButton),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = textButton,
                color = contentColor,
                fontSize = 16.sp
            )
        }
    }
}