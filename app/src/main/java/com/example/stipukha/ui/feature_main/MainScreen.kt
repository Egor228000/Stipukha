package com.example.stipukha.ui.feature_main

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stipukha.R
import com.example.stipukha.ui.feature_main.components.BalanceCard
import com.example.stipukha.ui.feature_main.components.BasicAlertDialogCustom
import com.example.stipukha.ui.feature_main.components.ButtonIntent
import com.example.stipukha.ui.feature_main.components.CategoryCard
import com.example.stipukha.ui.feature_main.components.CustomKeyboardNumber

val categoryMap = mapOf(
    R.string.eat to R.drawable.tools_kitchen_2,
    R.string.transport to R.drawable.car,
    R.string.fan to R.drawable.device_gamepad_2,
    R.string.healt to R.drawable.dental,
    R.string.other to R.drawable.sketching
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

    var sum = remember { mutableStateOf("0") }
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
                        isSelected = categoryName.toString() == selectedCategory,
                        onClick = { selectedCategory = categoryName.toString() }
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
                        text = sum.value.take(21),
                        color = MaterialTheme.colorScheme.tertiary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    )
                }
            }
        }
        item {
            CustomKeyboardNumber(sum)
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






