package com.example.stipukha.ui.feature_main.mvi

import com.example.stipukha.R

data class MainState(
    val balance: Long = 0,
    val balanceAll: Long = 0,
    val days: Int = 0,
    val dailyLimit: Long = 0,
    val availableToday: Long = 0,
    val inputAmount: String = "0",
    val selectedCategory: Int = R.string.eat,
    val isOnboardingRequired: Boolean = false,
    val isLoading: Boolean = true
)
