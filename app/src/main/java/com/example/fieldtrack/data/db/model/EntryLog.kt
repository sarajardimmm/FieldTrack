package com.example.fieldtrack.feature.logentryhistory.model

import java.time.LocalDate

data class LogEntryDisplay(
    val id: Long,
    val zoneName: String,
    val productName: String,
    val appliedAt: LocalDate,
    val reapplyDays: Int?,
    val quantity: String?,
    val notes: String?
)