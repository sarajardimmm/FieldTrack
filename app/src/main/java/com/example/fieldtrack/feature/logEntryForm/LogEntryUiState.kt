package com.example.fieldtrack.feature.logEntryForm

import java.time.LocalDate

data class LogEntryUiState(
    val zoneName: String = "",
    val productName: String? = "",
    val appliedAt: LocalDate = LocalDate.now(),
    val quantity: String? = "",
    val reapplyDays: String? = "",
    val notes: String? = "",
    val reapplyDaysError: String? = null,
    val isSaving: Boolean = false
)