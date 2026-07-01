package com.example.stipukha.ui.feature_main

import android.annotation.SuppressLint
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stipukha.R
import com.example.stipukha.ui.feature_main.components.BalanceCard
import com.example.stipukha.ui.feature_main.components.BasicAlertDialogCustom
import com.example.stipukha.ui.feature_main.components.ButtonIntent
import com.example.stipukha.ui.feature_main.components.CategoryCard

val categoryMap = mapOf(
    "Еда" to R.drawable.tools_kitchen_2,
    "Транспорт" to R.drawable.car,
    "Развлечение" to R.drawable.device_gamepad_2,
    "Здоровье" to R.drawable.dental,
    "Другое" to R.drawable.sketching
)
val keys = listOf(
    "1", "2", "3",
    "4", "5", "6",
    "7", "8", "9",
    ".", "0", "backspace"
)
@SuppressLint("ConfigurationScreenWidthHeight")
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun MainScreen() {

    var sum by remember { mutableStateOf("0") }
    var selectedCategory by remember { mutableStateOf("Еда") }


    if (false) {

        BasicAlertDialogCustom()

    }
    LazyColumn(
        modifier = Modifier

            .fillMaxSize()

    ) {
        item {
            BalanceCard(
                350,
                12000,
                24
            )
        }
        item {
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

        }
        item {
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
        }
        item {
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
        }
        item {
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
}






