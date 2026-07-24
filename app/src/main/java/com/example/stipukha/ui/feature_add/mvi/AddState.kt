package com.example.stipukha.ui.feature_add.mvi

data class AddState(
    val balance: Long = 0,
    val initialAmount: Long = 0,
    val startDate: Long = 0,
    val endDate: Long = 0,
    val showEditDialog: Boolean = false,
    val isLoading: Boolean = true
)


