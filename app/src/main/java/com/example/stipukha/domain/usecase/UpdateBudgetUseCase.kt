package com.example.stipukha.domain.usecase

import com.example.stipukha.data.local.entity.BudgetEntity
import com.example.stipukha.data.repository.BudgetRepository

class UpdateBudgetUseCase(
    private val repository: BudgetRepository
) {
    suspend operator fun invoke(amountInCents: Long, endDateTimestamp: Long) {
        val budget = BudgetEntity(
            initialAmountInCents = amountInCents,
            endDateTimestamp = endDateTimestamp,
            isOnboardingCompleted = true
        )
        repository.saveBudget(budget)
    }
}
