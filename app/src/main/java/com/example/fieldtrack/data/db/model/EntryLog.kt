package com.example.fieldtrack.data.db.model

import java.time.LocalDate

data class LogEntry(
    val id: Long,
    val zoneName: String,
    val productName: String,
    val appliedAt: LocalDate,
    val reapplyDays: Int?,
    val quantity: String?,
    val notes: String?
)