package com.example.stipukha.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.stipukha.data.repository.BudgetRepository
import com.example.stipukha.domain.model.BudgetInfo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit

class GetMainStateUseCase(
    private val repository: BudgetRepository
) {
    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(): Flow<MainScreenData> {
        return repository.getBudget().flatMapLatest { budget ->
            if (budget == null || !budget.isOnboardingCompleted) {
                flowOf(MainScreenData(isOnboardingRequired = true))
            } else {
                repository.getTransactionsByBudget(budget.id).map { transactions ->
                    val today = LocalDate.now()
                    val endDate = Instant.ofEpochMilli(budget.endDateTimestamp)
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate()
                    
                    val remainingDays = (ChronoUnit.DAYS.between(today, endDate) + 1).coerceAtLeast(0).toInt()
                    
                    val sortedTransactions = transactions.sortedBy { it.timestamp }
                    var currentBalance = budget.initialAmountInCents

                    var spentToday = 0L
                    var totalIncome = 0L

                    for (transaction in sortedTransactions) {
                        val transactionDate = Instant.ofEpochMilli(transaction.timestamp)
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()

                        val isToday = transactionDate.isEqual(today)

                        when (transaction.transactionType) {
                            "CORRECTION" -> {
                                currentBalance = transaction.amountInCents
                                if (isToday) {
                                    spentToday = 0
                                }
                            }
                            "EXPENSE" -> {
                                currentBalance -= transaction.amountInCents
                                if (isToday) spentToday += transaction.amountInCents
                            }
                            "INCOME" -> {
                                currentBalance += transaction.amountInCents
                                totalIncome += transaction.amountInCents
                            }
                        }
                    }

                    val poolForTodayAndFuture = currentBalance + spentToday
                    
                    val stablePlannedDailyLimit = if (remainingDays > 0 && poolForTodayAndFuture > 0) {
                        poolForTodayAndFuture / remainingDays
                    } else {
                        0L
                    }

                    val availableToday = (stablePlannedDailyLimit - spentToday).coerceAtLeast(0)

                    MainScreenData(
                        isOnboardingRequired = false,
                        budgetInfo = BudgetInfo(
                            budgetId = budget.id,
                            totalAmountInCents = budget.initialAmountInCents + totalIncome,
                            dailyLimitInCents = stablePlannedDailyLimit,
                            availableTodayInCents = availableToday,
                            remainingDays = remainingDays,
                            currentBalanceInCents = currentBalance,
                            endDateTimestamp = budget.endDateTimestamp
                        )
                    )
                }
            }
        }
    }
}

data class MainScreenData(
    val isOnboardingRequired: Boolean,
    val budgetInfo: BudgetInfo? = null
)
