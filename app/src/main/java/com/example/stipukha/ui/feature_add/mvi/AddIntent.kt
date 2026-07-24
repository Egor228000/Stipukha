package com.example.stipukha.ui.feature_add.mvi

sealed class AddIntent {
    object ResetBudget : AddIntent()
    data class UpdateBalance(val newBalance: Long) : AddIntent()
    data class UpdateBudget(val amountInCents: Long, val endDateTimestamp: Long) : AddIntent()
    data class ShowEditDialog(val show: Boolean) : AddIntent()
}


