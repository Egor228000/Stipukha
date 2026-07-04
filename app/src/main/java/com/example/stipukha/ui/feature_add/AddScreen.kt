package com.example.stipukha.ui.feature_add

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import com.example.stipukha.ui.feature_add.components.BalanceCard
import com.example.stipukha.ui.feature_add.components.BudgetProtectionInfo
import com.example.stipukha.ui.feature_add.components.DangerZoneSection
import com.example.stipukha.ui.feature_add.components.PeriodCard
import com.example.stipukha.ui.navigation.ScreenMain


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