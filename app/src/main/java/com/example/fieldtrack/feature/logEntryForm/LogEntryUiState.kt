package com.example.fieldtrack.feature.logEntryForm

import java.time.LocalDate

data class LogEntryUiState(
    val zoneName: String? = "",
    val zoneNameError: String? = null,
    val productName: String? = "",
    val productNameError: String? = null,
    val appliedAt: LocalDate = LocalDate.now(),
    val quantity: String? = "",
    val reapplyDays: String? = "",
    val notes: String? = "",
    val isSaving: Boolean = false,
    val genericError: String? = null
)