package com.example.fieldtrack.feature.logentry.history

import com.example.fieldtrack.data.db.model.LogEntry
import java.time.LocalDate


data class LogEntryListUiState(
    val pendingReapplies: List<PendingReapply> = emptyList(),
    val history: List<LogEntry> = emptyList(),
    val isLoading: Boolean = true
)

data class PendingReapply(
    val logEntry: LogEntry,
    val dueDate: LocalDate,
    val daysRemaining: Long,
    val isOverdue: Boolean
)