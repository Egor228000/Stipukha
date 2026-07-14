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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.stipukha.ui.feature_main.mvi.MainIntent

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
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val state by viewModel.state.collectAsState()

    if (state.isOnboardingRequired) {
        BasicAlertDialogCustom(
            onConfirm = { amount, date ->
                viewModel.handleIntent(MainIntent.CompleteOnboarding(amount * 100, date))
            }
        )
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            BalanceCard(
                balance = state.dailyLimit / 100,
                balanceAll = state.balance / 100,
                days = state.days
            )
        }
        item {
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState())
            ) {
                categoryMap.forEach { (categoryResId, iconRes) ->
                    CategoryCard(
                        textButton = categoryResId,
                        iconButton = iconRes,
                        isSelected = categoryResId == state.selectedCategory,
                        onClick = { viewModel.handleIntent(MainIntent.SelectCategory(categoryResId)) }
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
                        text = state.inputAmount.take(21),
                        color = MaterialTheme.colorScheme.tertiary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    )
                }
            }
        }
        item {
            CustomKeyboardNumber(
                currentAmount = state.inputAmount,
                onAmountChange = { viewModel.handleIntent(MainIntent.UpdateInputAmount(it)) }
            )
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                ButtonIntent(
                    R.string.replenish,
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth(0.48f)
                        .align(Alignment.BottomStart),
                    colorsButton = MaterialTheme.colorScheme.onPrimary,
                    colorText = MaterialTheme.colorScheme.tertiary,
                    onClick = { viewModel.handleIntent(MainIntent.ReplenishBalance) }
                )
                ButtonIntent(
                    R.string.spent,
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth(0.48f)
                        .align(Alignment.BottomEnd),
                    colorsButton = MaterialTheme.colorScheme.tertiary,
                    colorText = MaterialTheme.colorScheme.onPrimary,
                    onClick = { viewModel.handleIntent(MainIntent.AddExpense) }
                )
            }
        }
    }
}
