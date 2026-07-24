package com.example.stipukha.ui.feature_add

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import com.example.stipukha.ui.feature_add.components.BalanceCard
import com.example.stipukha.ui.feature_add.components.BudgetProtectionInfo
import com.example.stipukha.ui.feature_add.components.DangerZoneSection
import com.example.stipukha.ui.feature_add.components.EditBudgetDialog
import com.example.stipukha.ui.feature_add.components.PeriodCard
import com.example.stipukha.ui.feature_add.mvi.AddIntent
import com.example.stipukha.ui.navigation.ScreenMain


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddScreen(
    onSelectedIndexChange: (Int) -> Unit,
    backStack: SnapshotStateList<NavKey>,
    viewModel: AddViewModel
) {
    val state by viewModel.state.collectAsState()

    BackHandler(true) {
        backStack.clear()
        backStack.add(ScreenMain)
        onSelectedIndexChange(1)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            BalanceCard(
                balance = state.balance, 
                initialAmount = state.initialAmount,
                onEditClick = { viewModel.handleIntent(AddIntent.ShowEditDialog(true)) }
            )
        }

        item {
            PeriodCard(
                endDateTimestamp = state.endDate,
                onEditClick = { viewModel.handleIntent(AddIntent.ShowEditDialog(true)) }
            )
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
            DangerZoneSection(onResetClick = {
                viewModel.handleIntent(AddIntent.ResetBudget)
                backStack.clear()
                backStack.add(ScreenMain)
                onSelectedIndexChange(1)
            })
        }
    }

    if (state.showEditDialog) {
        EditBudgetDialog(
            initialAmountCents = state.initialAmount,
            currentEndDateTimestamp = state.endDate,
            onDismiss = { viewModel.handleIntent(AddIntent.ShowEditDialog(false)) },
            onConfirm = { amount, date ->
                viewModel.handleIntent(AddIntent.UpdateBudget(amount, date))
            }
        )
    }
}
