package com.example.stipukha.ui.feature_add

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavKey
import com.example.stipukha.ui.navigation.ScreenMain
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stipukha.R
import com.example.stipukha.ui.feature_add.components.BalanceCard
import com.example.stipukha.ui.feature_add.components.BudgetProtectionInfo
import com.example.stipukha.ui.feature_add.components.DangerZoneSection
import com.example.stipukha.ui.feature_add.components.PeriodCard


@Composable
fun AddScreen(onSelectedIndexChange: (Int) -> Unit, backStack: SnapshotStateList<NavKey>) {
    BackHandler(true) {
        backStack.add(ScreenMain)
        onSelectedIndexChange(1)

    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            BalanceCard()
        }

        item {
            PeriodCard()
        }

        item {
            BudgetProtectionInfo()
        }

        item {
            HorizontalDivider(
                color = MaterialTheme.colorScheme.tertiary,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        item {
            DangerZoneSection()
        }
    }

}