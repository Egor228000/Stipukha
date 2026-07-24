package com.example.stipukha.ui.feature_main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stipukha.domain.usecase.AddTransactionUseCase
import com.example.stipukha.domain.usecase.GetMainStateUseCase
import com.example.stipukha.domain.usecase.SaveOnboardingUseCase
import com.example.stipukha.ui.feature_main.mvi.MainIntent
import com.example.stipukha.ui.feature_main.mvi.MainState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
class MainViewModel(
    private val getMainStateUseCase: GetMainStateUseCase,
    private val addTransactionUseCase: AddTransactionUseCase,
    private val saveOnboardingUseCase: SaveOnboardingUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state.asStateFlow()

    init {
        observeState()
    }

    private fun observeState() {
        viewModelScope.launch {
            getMainStateUseCase().collect { data ->
                _state.update { 
                    it.copy(
                        isOnboardingRequired = data.isOnboardingRequired,
                        balance = data.budgetInfo?.currentBalanceInCents ?: 0L,
                        balanceAll = data.budgetInfo?.totalAmountInCents ?: 0L,
                        days = data.budgetInfo?.remainingDays ?: 0,
                        dailyLimit = data.budgetInfo?.dailyLimitInCents ?: 0L,
                        availableToday = data.budgetInfo?.availableTodayInCents ?: 0L,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun handleIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.UpdateInputAmount -> {
                _state.update { it.copy(inputAmount = intent.amount) }
            }
            is MainIntent.SelectCategory -> {
                _state.update { it.copy(selectedCategory = intent.categoryResId) }
            }
            is MainIntent.AddExpense -> {
                executeTransaction(type = "EXPENSE")
            }
            is MainIntent.ReplenishBalance -> {
                executeTransaction(type = "INCOME")
            }
            is MainIntent.CompleteOnboarding -> {
                viewModelScope.launch {
                    val timestamp = intent.endDate.atTime(LocalTime.MAX)
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
                        .toEpochMilli()
                    saveOnboardingUseCase(intent.initialAmount, timestamp)
                }
            }
        }
    }

    private fun executeTransaction(type: String) {
        val amountStr = _state.value.inputAmount
        val amount = amountStr.toDoubleOrNull() ?: 0.0
        if (amount <= 0) return

        viewModelScope.launch {
            val amountInCents = (amount * 100).toLong()
            addTransactionUseCase(
                budgetId = 1,
                amountInCents = amountInCents,
                category = _state.value.selectedCategory.toString(),
                transactionType = type
            )
            _state.update { it.copy(inputAmount = "0") }
        }
    }
}
