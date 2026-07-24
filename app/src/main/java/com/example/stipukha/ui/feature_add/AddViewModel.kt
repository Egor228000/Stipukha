package com.example.stipukha.ui.feature_add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stipukha.domain.usecase.GetMainStateUseCase
import com.example.stipukha.domain.usecase.ResetBudgetUseCase
import com.example.stipukha.domain.usecase.UpdateBudgetUseCase
import com.example.stipukha.ui.feature_add.mvi.AddIntent
import com.example.stipukha.ui.feature_add.mvi.AddState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddViewModel(
    private val getMainStateUseCase: GetMainStateUseCase,
    private val resetBudgetUseCase: ResetBudgetUseCase,
    private val updateBudgetUseCase: UpdateBudgetUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AddState())
    val state: StateFlow<AddState> = _state.asStateFlow()
    
    private var rawInitialAmount: Long = 0

    init {
        observeState()
    }

    private fun observeState() {
        viewModelScope.launch {
            getMainStateUseCase().collect { data ->
                _state.update {
                    rawInitialAmount = data.budgetInfo?.totalAmountInCents ?: 0L
                    it.copy(
                        balance = data.budgetInfo?.currentBalanceInCents ?: 0L,
                        initialAmount = data.budgetInfo?.totalAmountInCents ?: 0L,
                        endDate = data.budgetInfo?.endDateTimestamp ?: 0L,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun handleIntent(intent: AddIntent) {
        when (intent) {
            is AddIntent.ResetBudget -> {
                viewModelScope.launch {
                    resetBudgetUseCase()
                }
            }
            is AddIntent.UpdateBalance -> {
            }
            is AddIntent.UpdateBudget -> {
                viewModelScope.launch {
                    updateBudgetUseCase(intent.amountInCents, intent.endDateTimestamp)
                    _state.update { it.copy(showEditDialog = false) }
                }
            }
            is AddIntent.ShowEditDialog -> {
                _state.update { it.copy(showEditDialog = intent.show) }
            }
        }
    }
}
