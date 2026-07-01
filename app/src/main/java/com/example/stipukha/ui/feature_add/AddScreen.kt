package com.example.stipukha.ui.feature_add

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavKey
import com.example.stipukha.ui.navigation.ScreenMain

@Composable
fun AddScreen(onSelectedIndexChange: (Int) -> Unit, backStack: SnapshotStateList<NavKey>) {
    BackHandler(true) {
        backStack.add(ScreenMain)
        onSelectedIndexChange(1)

    }

}