package com.example.stipukha.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.stipukha.data.local.db.AppDatabase
import com.example.stipukha.data.repository.BudgetRepositoryImpl
import com.example.stipukha.domain.usecase.AddTransactionUseCase
import com.example.stipukha.domain.usecase.GetMainStateUseCase
import com.example.stipukha.domain.usecase.SaveOnboardingUseCase
import com.example.stipukha.domain.usecase.GetTransactionsUseCase
import com.example.stipukha.domain.usecase.ResetBudgetUseCase
import com.example.stipukha.domain.usecase.UpdateBudgetUseCase
import com.example.stipukha.ui.feature_main.MainScreen
import com.example.stipukha.ui.feature_main.MainViewModel
import com.example.stipukha.ui.feature_stats.StatsScreen
import com.example.stipukha.ui.feature_stats.StatsViewModel
import com.example.stipukha.ui.feature_add.AddScreen
import com.example.stipukha.ui.feature_add.AddViewModel
import kotlinx.serialization.Serializable


@Serializable
data object ScreenMain: NavKey

@Serializable
data object ScreenStats: NavKey


@Serializable
data object ScreenAdd: NavKey




@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavDisplayNavigation(
    backStack: SnapshotStateList<NavKey>,
    paddingValues: PaddingValues,
    selectedIndex: Int,
    onSelectedIndexChange: (Int) -> Unit
) {
    val context = LocalContext.current
    val database = AppDatabase.getInstance(context)
    val repository = BudgetRepositoryImpl(database.transactionDao(), database.budgetDao())
    
    val getMainStateUseCase = GetMainStateUseCase(repository)
    val addTransactionUseCase = AddTransactionUseCase(repository)
    val saveOnboardingUseCase = SaveOnboardingUseCase(repository)
    val getTransactionsUseCase = GetTransactionsUseCase(repository)
    val resetBudgetUseCase = ResetBudgetUseCase(repository)
    val updateBudgetUseCase = UpdateBudgetUseCase(repository)

    NavDisplay(
        modifier = Modifier.padding(paddingValues).padding(16.dp),
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        backStack = backStack,
        entryProvider = entryProvider<NavKey> {
            entry<ScreenMain> {
                val viewModel: MainViewModel = viewModel(
                    factory = object : ViewModelProvider.Factory {
                        @Suppress("UNCHECKED_CAST")
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return MainViewModel(
                                getMainStateUseCase,
                                addTransactionUseCase,
                                saveOnboardingUseCase
                            ) as T
                        }
                    }
                )
                MainScreen(viewModel)
            }
            entry<ScreenStats> {
                val viewModel: StatsViewModel = viewModel(
                    factory = object : ViewModelProvider.Factory {
                        @Suppress("UNCHECKED_CAST")
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return StatsViewModel(getTransactionsUseCase) as T
                        }
                    }
                )
                StatsScreen(viewModel)
            }
            entry<ScreenAdd> {
                val viewModel: AddViewModel = viewModel(
                    factory = object : ViewModelProvider.Factory {
                        @Suppress("UNCHECKED_CAST")
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return AddViewModel(getMainStateUseCase, resetBudgetUseCase, updateBudgetUseCase) as T
                        }
                    }
                )
                AddScreen(onSelectedIndexChange, backStack, viewModel)
            }

        },
    )

}
