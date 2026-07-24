package com.example.stipukha

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavKey
import com.example.stipukha.ui.navigation.BottomBarNavigation
import com.example.stipukha.ui.navigation.ScreenMain
import com.example.stipukha.ui.theme.StipukhaTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val backStack = remember { mutableStateListOf<NavKey>(ScreenMain) }
            StipukhaTheme() {
                BottomBarNavigation(backStack)
            }
        }
    }
}

