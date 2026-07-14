package com.example.stipukha.domain.usecase

import com.example.stipukha.data.repository.BudgetRepository
import com.example.stipukha.domain.model.BudgetInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.TimeUnit

class CalculateDailyLimitUseCase(
    private val repository: BudgetRepository
) {
    operator fun invoke(
        budgetId: Long,
        initialAmountInCents: Long,
        endDateTimestamp: Long
    ): Flow<BudgetInfo> {

        return repository.getTransactionsByBudget(budgetId).map { transactions ->
            val remainingDays = calculateRemainingDays(endDateTimestamp)

            val sortedTransactions = transactions.sortedBy { it.timestamp }

            var currentBalance = initialAmountInCents

            for (transaction in sortedTransactions) {
                when (transaction.transactionType) {
                    "CORRECTION" -> {
                        currentBalance = transaction.amountInCents
                    }
                    "EXPENSE" -> {
                        currentBalance -= transaction.amountInCents
                    }
                    "INCOME" -> {
                        currentBalance += transaction.amountInCents
                    }
                }
            }

            val dailyLimit = if (remainingDays > 0 && currentBalance > 0) {
                currentBalance / remainingDays
            } else {
                0L
            }

            BudgetInfo(
                budgetId = budgetId,
                totalAmountInCents = initialAmountInCents,
                dailyLimitInCents = dailyLimit,
                remainingDays = remainingDays,
                currentBalanceInCents = currentBalance
            )
        }
    }

    private fun calculateRemainingDays(endDateTimestamp: Long): Int {
        val currentTime = System.currentTimeMillis()
        val diffInMs = endDateTimestamp - currentTime
        if (diffInMs <= 0) return 0

        return (TimeUnit.MILLISECONDS.toDays(diffInMs) + 1).toInt()
    }
}
