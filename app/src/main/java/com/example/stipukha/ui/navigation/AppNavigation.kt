package com.example.stipukha.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.stipukha.ui.feature_main.MainScreen
import com.example.stipukha.ui.feature_stats.StatsScreen
import com.example.stipukha.ui.feature_add.AddScreen
import kotlinx.serialization.Serializable


@Serializable
data object ScreenMain: NavKey

@Serializable
data object ScreenStats: NavKey


@Serializable
data object ScreenAdd: NavKey




@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun NavDisplayNavigation(
    backStack: SnapshotStateList<NavKey>,
    paddingValues: PaddingValues,
    selectedIndex: Int,
    onSelectedIndexChange: (Int) -> Unit
) {
    NavDisplay(
        modifier = Modifier.padding(paddingValues).padding(16.dp),
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        backStack = backStack,
        entryProvider = entryProvider<NavKey> {
            entry<ScreenMain> {
                MainScreen()
            }
            entry<ScreenStats> {
                StatsScreen()
            }
            entry<ScreenAdd> {
                AddScreen(onSelectedIndexChange, backStack)
            }

        },
    )

}