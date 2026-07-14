package com.example.stipukha.domain.usecase

import com.example.stipukha.data.repository.BudgetRepository
import com.example.stipukha.domain.model.BudgetInfo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import java.util.concurrent.TimeUnit

class GetMainStateUseCase(
    private val repository: BudgetRepository
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(): Flow<MainScreenData> {
        return repository.getBudget().flatMapLatest { budget ->
            if (budget == null || !budget.isOnboardingCompleted) {
                flowOf(MainScreenData(isOnboardingRequired = true))
            } else {
                repository.getTransactionsByBudget(budget.id).map { transactions ->
                    val remainingDays = calculateRemainingDays(budget.endDateTimestamp)
                    val sortedTransactions = transactions.sortedBy { it.timestamp }
                    var currentBalance = budget.initialAmountInCents

                    for (transaction in sortedTransactions) {
                        when (transaction.transactionType) {
                            "CORRECTION" -> currentBalance = transaction.amountInCents
                            "EXPENSE" -> currentBalance -= transaction.amountInCents
                            "INCOME" -> currentBalance += transaction.amountInCents
                        }
                    }

                    val dailyLimit = if (remainingDays > 0 && currentBalance > 0) {
                        currentBalance / remainingDays
                    } else {
                        0L
                    }

                    MainScreenData(
                        isOnboardingRequired = false,
                        budgetInfo = BudgetInfo(
                            budgetId = budget.id,
                            totalAmountInCents = budget.initialAmountInCents,
                            dailyLimitInCents = dailyLimit,
                            remainingDays = remainingDays,
                            currentBalanceInCents = currentBalance
                        )
                    )
                }
            }
        }
    }

    private fun calculateRemainingDays(endDateTimestamp: Long): Int {
        val currentTime = System.currentTimeMillis()
        val diffInMs = endDateTimestamp - currentTime
        if (diffInMs <= 0) return 0
        return (TimeUnit.MILLISECONDS.toDays(diffInMs) + 1).toInt()
    }
}

data class MainScreenData(
    val isOnboardingRequired: Boolean,
    val budgetInfo: BudgetInfo? = null
)
