package com.example.stipukha.domain.usecase

import com.example.stipukha.data.local.entity.BudgetEntity
import com.example.stipukha.data.repository.BudgetRepository

class SaveOnboardingUseCase(
    private val repository: BudgetRepository
) {
    suspend operator fun invoke(initialAmountInCents: Long, endDateTimestamp: Long) {
        val budget = BudgetEntity(
            initialAmountInCents = initialAmountInCents,
            endDateTimestamp = endDateTimestamp,
            isOnboardingCompleted = true
        )
        repository.saveBudget(budget)
    }
}
