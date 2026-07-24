package com.example.stipukha.ui.feature_stats.mvi

sealed class StatsIntent {
    data class SelectPeriod(val period: String) : StatsIntent()
}


