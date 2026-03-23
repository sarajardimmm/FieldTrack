package com.example.fieldtrack.feature.logentryhistory

import java.time.LocalDate

data class LogEntryFormData(
    val zoneName: String,
    val productName: String,
    val appliedAt: LocalDate,
    val reapplyDays: String,
    val quantity: String,
    val notes: String,
)