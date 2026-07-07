package com.example.stipukha.ui.feature_main.mvi

import java.time.LocalDate

sealed class MainIntent {
    data class UpdateInputAmount(val amount: String) : MainIntent()
    data class SelectCategory(val categoryResId: Int) : MainIntent()
    object AddExpense : MainIntent()
    object ReplenishBalance : MainIntent()
    data class CompleteOnboarding(val initialAmount: Long, val endDate: LocalDate) : MainIntent()
}
