package com.example.stipukha.domain.usecase

import com.example.stipukha.data.repository.BudgetRepository

class ResetBudgetUseCase(
    private val repository: BudgetRepository
) {
    suspend operator fun invoke() {
        repository.clearTransactionsByBudget(1)
        repository.setOnboardingCompleted(false)
    }
}
